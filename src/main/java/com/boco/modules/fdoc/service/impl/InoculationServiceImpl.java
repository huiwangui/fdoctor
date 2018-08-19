package com.boco.modules.fdoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.DoctorDao;
import com.boco.modules.fdoc.dao.HospitalDao;
import com.boco.modules.fdoc.dao.InoculationDao;
import com.boco.modules.fdoc.dao.UserDao;
import com.boco.modules.fdoc.dao.VaccineDao;
import com.boco.modules.fdoc.dao.VaccineSourceDao;
import com.boco.modules.fdoc.service.InoculationService;

@Service
public class InoculationServiceImpl implements InoculationService{
	@Resource
	InoculationDao inoculationDao;
	@Resource
	UserDao userDao;
	@Resource
	DoctorDao docDao;
	@Resource
	VaccineSourceDao vaccineSourceDao;
	@Resource
	VaccineDao vaccineDao;
	@Resource
	HospitalDao hospitalDao;
	

//	@Override
//	public String vaccinate(InoculationVo vo) {
//		Integer signed = famdocDao.isSigned(vo.getIdCard());
//		if (signed == 0) {
//			return "notSign";	//当前用户未签约
//		}
//		vo.setCreateTime(new Date());
//		vo.setStatus(BusinessConstants.INO_STATUS_NOTINO);  //设置状态为未接种
//		vo.setPayStatus(BusinessConstants.PAY_STATUS_HASNOTPAY);
//		/**
//		 * 检测重复预约
//		 */
//		VaccineSourceEntity vaccineSourceEntity = vaccineSourceDao.get(vo.getVaccineSourceId());
//		vo.setInoDate(vaccineSourceEntity.getInoDate().getTime()/1000);
//		if (inoculationDao.getNum(vo) > 0) {
//			return "reIno";  //重复预约
//		}
//		/**
//		 * 查询是否有号，预约号量-1，并发控制
//		 */
//		int flag = 0;
//		while(flag == 0){
//			if (vaccineSourceEntity.getVaccineNum() == 0) {
//				return "no number"; // 没号了，返回
//			}else {
//				vaccineSourceEntity.setVaccineNum(vaccineSourceEntity.getVaccineNum()-1); //号量-1
//				int updateNum = vaccineSourceDao.update(vaccineSourceEntity);
//				if (updateNum == 1) {   //修改行数为1，说明版本号对应，中间无其他人修改
//					flag=1;
//				}
//			}
//		}
//		
//		inoculationDao.vaccinate(vo);
//		int id = vo.getId();
//		//拼接订单号：当前日期+4位随机数+主键ID
//		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
//		String dateString = myFmt.format(new Date());
//		Random ne = new Random();
//		int randomString = ne.nextInt(9999-1000+1)+1000;
//		String orderNum = dateString+randomString+id;
//		
//		inoculationDao.updateNum(orderNum, id);
//		return orderNum;
//	}
//
//	@Override
//	public InoculationVo getInoNum(String docUid) {
//		InoculationVo vo = new InoculationVo();
//		vo.setInoNum(inoculationDao.getInoNum(docUid,BusinessConstants.INO_STATUS_HASINO));  //已接种人数
//		vo.setNotInoNum(inoculationDao.getInoNum(docUid,BusinessConstants.INO_STATUS_NOTINO)); //未接种人数
//		return vo;
//	}
//
//	@Override
//	public List<InoculationVo> getInoList(String docUid) {
//		DoctorEntity doc = docDao.getDocByDocUid(docUid);
//		if (doc != null) {
//			InoculationVo vo = new InoculationVo();
//			vo.setDocId(doc.getId());
//			vo.setStatus(BusinessConstants.INO_STATUS_HASINO);
//			return inoculationDao.getInoList(vo);
//		}else {
//			return null;
//		}
//	}
//
//	@Override
//	public List<InoculationVo> getNotInoList(String docUid) {
//		DoctorEntity doc = docDao.getDocByDocUid(docUid);
//		if (doc != null) {
//			InoculationVo vo = new InoculationVo();
//			vo.setDocId(doc.getId());
//			vo.setStatus(BusinessConstants.INO_STATUS_NOTINO);
//			return inoculationDao.getInoList(vo);
//		}else {
//			return null;
//		}
//	}
//
//	@Override
//	public int addVaccineSource(VaccineSourceVo vo) {
//		System.out.println(vo.getDocUid());
//		DoctorEntity doc = docDao.getDocByDocUid(vo.getDocUid());
//		VaccineSourceEntity entity = new VaccineSourceEntity();
//		entity.setDocId(doc.getId());
//		entity.setInoDate(new Date(vo.getTimeStamp()));
//		entity.setVaccineNum(vo.getVaccineNum());
//		VaccineEntity vaccine = vaccineDao.getEntityByDetail(vo.getVaccineDetailId());
//		entity.setVaccineId(vaccine.getId());
//		entity.setCreateTime(new Date());
//		entity.setVersion(1);  //设置版本号为1
//		entity.setStatus(BusinessConstants.STATUS_PUNISHED);  //设置状态为已发布
//		entity.setDelFlag(BusinessConstants.DEL_FLAG_NO); //设置删除标识为未删除
//		/**
//		 * 判断重复：若同一个医生在同一天再次放同一个疫苗的号，则用新的号源数量替换之前的数量
//		 */
//		VaccineSourceEntity reEntity = vaccineSourceDao.getSourceByDocAndDate(entity);
//		int returnInt = 0;
//		if (reEntity != null) {
//			reEntity.setVaccineNum(entity.getVaccineNum());
//			returnInt = vaccineSourceDao.updateVaccineNum(reEntity);
//		}else {
//			returnInt = vaccineSourceDao.insert(entity);
//		}
//		return returnInt;
//	}
//
//	@Override
//	public List<HospitalEntity> getInoHospList(String city,int detailId) {
//		return hospitalDao.getInoHospList(city,detailId);
//	}
//
//	@Override
//	public List<DoctorEntity> getInoDoctorList(int hospitalId,int detailId) {
//		return docDao.getInoDoctor(hospitalId,detailId);
//	}
//	/**
//	 * 获取某医生一周之内的放号记录，若无放号记录，则用0填充当天
//	 */
//	@Override
//	public List<VaccineSourceVo> getInoNumListInWeek(VaccineSourceVo vo) {
//		List<VaccineSourceVo> returnList = new ArrayList<VaccineSourceVo>();
//		
//		List<Date> lDate = new ArrayList<Date>();
//		Date dBegin = vo.getBeginDate();
//		Date dEnd = vo.getEndDate();
//		lDate.add(dBegin);
//		Calendar calBegin = Calendar.getInstance();
//		Map<Integer,Long> map = new HashMap<Integer, Long>();
//		// 使用给定的 Date 设置此 Calendar 的时间
//		calBegin.setTime(dBegin);
//		Calendar calEnd = Calendar.getInstance();
//		// 使用给定的 Date 设置此 Calendar 的时间
//		calEnd.setTime(dEnd);
//		// 测试此日期是否在指定日期之后
//		while (dEnd.after(calBegin.getTime())) {
//			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
//			calBegin.add(Calendar.DAY_OF_MONTH, 1);
//			lDate.add(calBegin.getTime());
//		}
//		for (Date object : lDate) {
//			int[] weeks = {7, 1, 2, 3, 4, 5, 6};
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(object);
//			int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
//			if (week_index < 0) {
//				week_index = 0;
//			}
//			map.put(weeks[week_index], object.getTime());
//		}
//		
//		for (int i = 1; i <= 7; i++) {
//			vo.setWeekDay(i);
//			VaccineSourceVo listItem = vaccineSourceDao.getByWeekDay(vo);
//			if (listItem == null) {
//				listItem = new VaccineSourceVo();
//				listItem.setWeekDay(i);
//				listItem.setTimeStamp(map.get(i));
//				listItem.setVaccineNum(0);
//			}
//			//  封装返回对象
//			VaccineSourceVo returnItem = new VaccineSourceVo();
//			returnItem.setId(listItem.getId());
//			returnItem.setNumber(listItem.getVaccineNum());
//			returnItem.setWeekDate(listItem.getWeekDay());
//			returnItem.setWorkDate(listItem.getTimeStamp());
//			returnList.add(returnItem);
//		}
//		return returnList;
//	}
//
//	@Override
//	public List<VaccineSourceVo> getByStatus(String uid, String status) {
//		return vaccineSourceDao.getByStatus(uid, status);
//	}
//
//	@Override
//	public int clear(String docUid) {
//		return vaccineSourceDao.clear(docUid);
//	}
//
//	@Override
//	public List<VaccineSourceVo> getSourceList(Integer detailId) {
//		return vaccineSourceDao.getSourceList(detailId);
//	}
//
//	@Override
//	public List<InoculationVo> getUserBookingInoList(String uid) {
//		InoculationVo vo = new InoculationVo();
//		vo.setUid(uid);
//		return inoculationDao.getInoList(vo);
//	}
//
//	@Override
//	public int updateBookingStatus(Integer id, String status) {
//		return inoculationDao.updateBookingStatus(id, status);
//	}

}
