package com.boco.modules.fdoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.DoctorDao;
import com.boco.modules.fdoc.dao.HealthCheckDao;
import com.boco.modules.fdoc.dao.HealthCheckSourceDao;
import com.boco.modules.fdoc.dao.UserDao;
import com.boco.modules.fdoc.service.HealthCheckService;

@Service
public class HealthCheckServiceImpl implements HealthCheckService{

	@Resource
	DoctorDao doctorDao;
	@Resource
	HealthCheckSourceDao sourceDao;
	@Resource
	HealthCheckDao checkDao;
	@Resource
	UserDao userDao;
	/**
	 * 
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @Override
	public int addHealthCheckSource(HealthCheckSourceVo vo) {
		DoctorEntity doctor = doctorDao.getDocByDocUid(vo.getDocUid());
		if (doctor == null) {
			return -1;          //传入的医生uid错误
		}
		vo.setDocId(doctor.getId());  //设置医生ID
		vo.setCheckDate(new Date(vo.getTimeStamp()));   //设置体检日期
		List<HealthCheckSourceEntity> list = sourceDao.getSourceByDocAndDate(vo);
		
		//设置用于修改、新增操作的entity
		HealthCheckSourceEntity entity = new HealthCheckSourceEntity();
		entity.setPayType(vo.getPayType());
		entity.setCheckType(vo.getCheckType());
		entity.setCheckDate(vo.getCheckDate());
		entity.setDocId(vo.getDocId());
		entity.setSourceNum(vo.getSourceNum());
		entity.setCreateTime(new Date());
		entity.setVersion(1);
		entity.setStatus(BusinessConstants.STATUS_PUNISHED);
		entity.setDelFlag(BusinessConstants.DEL_FLAG_NO);
		
		if (list == null || list.size() == 0) {   //list为空，说明该医生在当天没有放过此类型的号，新增记录
			return sourceDao.insert(entity);
		}else {										//list不为空，说明该医生当天已放过此类号，更新号源数量
			return sourceDao.updateSourceNum(entity);
		}
	}

	@Override
	public List<HealthCheckSourceVo> getByStatus(HealthCheckSourceVo vo) {
		List<HealthCheckSourceVo> list = sourceDao.getByStatus(vo);
		return list;
	}

	@Override
	public int clear(HealthCheckSourceVo vo) {
		DoctorEntity doctor = doctorDao.getDocByDocUid(vo.getDocUid());
		if (doctor == null) {
			return -1;          //传入的医生uid错误
		}
		HealthCheckSourceEntity entity = new HealthCheckSourceEntity();
		entity.setDocId(doctor.getId());
		entity.setCheckType(vo.getCheckType());
		return sourceDao.clear(entity);
	}

	@Override
	public List<HealthCheckVo> getHealthCheckList(HealthCheckVo vo) {
		return checkDao.getHealthCheckList(vo);
	}

	@Override
	public List<HealthCheckSourceVo> getBookingHealthCheck(HealthCheckSourceVo vo) {
		vo.setEndDate(new Date(System.currentTimeMillis()+1000*60*60*24*7));  //查询截至日期为一周之后的当天
		vo.setStatus(BusinessConstants.STATUS_PUNISHED);  //设置查询号源状态为已发布
		return sourceDao.getBookingHealthCheck(vo);
	}

	@Override
	public String bookingHealthCheck(HealthCheckVo vo) {
		Integer signed = famdocDao.isSigned(vo.getIdCard());
		if (signed == 0) {
			return "notSign";	//当前用户未签约
		}
		vo.setCheckDate(new Date(vo.getTimeStamp()));
		
		HealthCheckVo selectVo = new HealthCheckVo();
		selectVo.setIdCard(vo.getIdCard());
		selectVo.setCheckType(vo.getCheckType());
		selectVo.setCheckDate(vo.getCheckDate());
		selectVo.setStatus("'"+BusinessConstants.CHECK_STATUS_NOTCHECKED + "','" + BusinessConstants.CHECK_STATUS_HASCHECKED+"'");
		
		List<HealthCheckVo> list = checkDao.getHealthCheckList(selectVo);
		if (list != null && list.size() > 0) {   //查询到的list不为空，说明重复预约
			return "reBooking";
		}
		HealthCheckSourceEntity source = sourceDao.get(vo.getSourceId());
		if (source == null) {
			return "no source";   //为查询到号源
		}
		
		int flag = 0;
		while(flag == 0){
			if (source.getSourceNum() == 0) {
				return "no number"; // 没号了，返回
			}else {
				source.setSourceNum(source.getSourceNum()-1); //号量-1
				int updateNum = sourceDao.update(source);
				if (updateNum == 1) {   //修改行数为1，说明版本号对应，中间无其他人修改
					flag=1;
				}
			}
		}

		HealthCheckEntity entity = new HealthCheckEntity();
		entity.setIdCard(vo.getIdCard());
		entity.setPayType(vo.getPayType());
		entity.setCheckType(vo.getCheckType());
		entity.setDocId(vo.getDocId());
		entity.setHospId(vo.getHospId());
		entity.setCheckDate(vo.getCheckDate());
		entity.setCreateTime(new Date());
		entity.setStatus(BusinessConstants.CHECK_STATUS_NOTCHECKED);
		entity.setUid(vo.getUid());
		if (BusinessConstants.SOURCE_PAYTYPE_SELFPAY.equals(source.getPayType())) {
			entity.setPayStatus(BusinessConstants.PAY_STATUS_HASNOTPAY);//号源类型为自费：设置为未付款
		}else if (BusinessConstants.SOURCE_PAYTYPE_FREE.equals(source.getPayType())) {
			entity.setPayStatus(BusinessConstants.PAY_STATUS_HASPAYED);//号源类型为免费：设置为已付款
		}
		
		checkDao.insert(entity);
		int id = entity.getId();
		
		//拼接订单号：当前日期+4位随机数+主键ID
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
		String dateString = myFmt.format(new Date());
		Random ne = new Random();
		int randomString = ne.nextInt(9999-1000+1)+1000;
		String orderNum = dateString+randomString+id;
		entity.setHealthcheckNum(orderNum);
		checkDao.updateNum(entity);
		
		//封装返回对象
		return String.valueOf(entity.getId());
	}

	@Override
	public HealthCheckVo getChecked(String id) {
		HealthCheckEntity entity = new HealthCheckEntity();
		entity.setId(Integer.parseInt(id));
		
		return checkDao.getBookedHealthCheck(entity);
	}

	@Override
	public HealthCheckVo getHealthCheckPeopleNum(HealthCheckVo vo) {
		vo.setStatus(BusinessConstants.CHECK_STATUS_NOTCHECKED);   //设置查询条件为未体检
		List<HealthCheckVo> list = checkDao.getHealthCheckList(vo);
		HealthCheckVo returnVo = new HealthCheckVo();
		if (list != null && list.size() != 0) {
			returnVo.setNotCheckNum(list.size());
		}else {
			returnVo.setNotCheckNum(0);
		}
		vo.setStatus(BusinessConstants.CHECK_STATUS_HASCHECKED);    //设置查询条件为已体检
		list = checkDao.getHealthCheckList(vo);
		if (list != null && list.size() != 0) {
			returnVo.setCheckedNum(list.size());
		}else {
			returnVo.setCheckedNum(0);
		}
		return returnVo;
	}

	@Override
	public List<ResidentVo> getResidentByType(HealthCheckVo vo) {
		return checkDao.getResidentByType(vo);
	}

	@Override
	public int updateBookingStatus(Integer id, String status) {
		return checkDao.updateBookingStatus(id, status);
	}
	 */
	

}
