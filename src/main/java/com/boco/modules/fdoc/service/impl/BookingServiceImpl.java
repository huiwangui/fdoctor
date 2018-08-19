package com.boco.modules.fdoc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.dao.BookingDao;
import com.boco.modules.fdoc.dao.ScheDao;
import com.boco.modules.fdoc.model.BookingEntity;
import com.boco.modules.fdoc.model.ScheduleEntity;
import com.boco.modules.fdoc.service.BookingService;
import com.boco.modules.fdoc.vo.BookingVo;

@Service

public class BookingServiceImpl implements BookingService{

	@Resource
	BookingDao bookingDao;
	@Resource
	ScheDao scheDao;
//	@Resource
//	UserRelationsDao userRelationsDao;
	
	@Override
	public String booking(BaseJsonVo vo) {
		BookingEntity entity = (BookingEntity)vo.getData();
		entity.setCreateTime(new Date());
		entity.setPayStatus(BusinessConstants.PAY_STATUS_HASNOTPAY); //设置默认未支付
		entity.setSeeDocStatus(BusinessConstants.SEE_DOC_STATUS_HASNOTDOC); //设置默认未就诊
		int bookingNum = bookingDao.getBookingNum(entity);
		if (bookingNum > 0) {
			return "reBooking";
		}
		/**
		 * 排班表剩余挂号数-1，并发控制
		 */
		ScheduleEntity scheEntity = scheDao.get(String.valueOf(entity.getScheId()));
		int flag = 0;
		while(flag == 0){
			if (scheEntity.getNumber() == 0) {
				return "no number"; // 没号了，返回
			}else {
				scheEntity.setNumber((byte)(scheEntity.getNumber()-1));
				int updateNum = scheDao.updateNum(scheEntity);
				if (updateNum == 1) {   //修改行数为1，说明版本号对应，中间无其他人修改
					flag=1;
				}
			}
		}
		
		bookingDao.booking(entity);
		int id = entity.getId();
		//拼接订单号：当前日期+4位随机数+主键ID
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
		String dateString = myFmt.format(new Date());
		Random ne = new Random();
		int randomString = ne.nextInt(9999-1000+1)+1000;
		String orderNum = dateString+randomString+id;
		entity.setOrderNum(orderNum);
		bookingDao.updateOrderNum(entity);
		return orderNum;
	}

	@Override
	public int cancelPaidBooking(int id) {
		return bookingDao.cancelPaidBooking(id);
	}

	@Override
	public int cancelUnpaidBooking(int id) {
		return bookingDao.cancelUnpaidBooking(id);
	}

	@Override
	public List<BookingVo> getBooking(BookingVo vo) {
		if (BusinessConstants.SEE_DOC_STATUS_HASNOTDOC.equals(vo.getSeeDocStatus())) {  //未就诊状态：对应未付款、已付款
			vo.setPayStatus(BusinessConstants.PAY_STATUS_HASPAYED + "," + BusinessConstants.PAY_STATUS_HASNOTPAY);
		}else if (BusinessConstants.SEE_DOC_STATUS_HASDOC.equals(vo.getSeeDocStatus())) {  //已就诊状态：对应未评论、已评论
			vo.setPayStatus(BusinessConstants.PAY_STATUS_HASCOMMENT + ","+ BusinessConstants.PAY_STATUS_NOTCOMMENT);
		}else if (BusinessConstants.SEE_DOC_STATUS_CANCLED.equals(vo.getSeeDocStatus())) {  //已取消状态：对应正在退款、已退款、已取消
			vo.setPayStatus(BusinessConstants.PAY_STATUS_RETURNED + "," + BusinessConstants.PAY_STATUS_RETURNING + "," + BusinessConstants.PAY_STATUS_CANCLED);
		}
		
		return bookingDao.getBooking(vo);
	}

	@Override
	public int getRefund(int id) {
		int result = bookingDao.getRefund(id);
		return result;
	}

	@Override
	public String quickBooking(String period, String uid, int docId,
			Long workdate) {
		//根据时间段、工作日、医生ID获取排班信息
//		ScheduleVo vo = new ScheduleVo();
//		vo.setDocId(docId);
//		vo.setPeriod(period);
//		vo.setWorkDate(String.valueOf(workdate/1000));
//		ScheduleEntity scheEntity = scheDao.getByDocIdAndWorkTime(vo);
//		UserRelationsEntity relationsEntity = userRelationsDao.getUserSelf(uid);
//		if (relationsEntity == null) {
//			return "noUserRelations";
//		}
//		//拼接预约对象信息
//		BookingEntity bookingEntity = new BookingEntity();
//		bookingEntity.setPeriod(period);
//		bookingEntity.setPatientId(relationsEntity.getId());
//		bookingEntity.setScheId(scheEntity.getId());
//		bookingEntity.setCreateTime(new Date());
//		bookingEntity.setPayStatus(BusinessConstants.PAY_STATUS_HASNOTPAY); //设置默认未付款
//		bookingEntity.setSeeDocStatus(BusinessConstants.SEE_DOC_STATUS_HASNOTDOC);//设置默认为未就诊
//		int bookingNum = bookingDao.getBookingNum(bookingEntity);
//		if (bookingNum > 0) {
//			return "reBooking";
//		}
//		/**
//		 * 排班表剩余挂号数-1，并发控制
//		 */
//		int flag = 0;
//		while(flag == 0){
//			if (scheEntity.getNumber() == 0) {
//				return "no number"; // 没号了，返回
//			}else {
//				scheEntity.setNumber((byte)(scheEntity.getNumber()-1));
//				int updateNum = scheDao.updateNum(scheEntity);
//				if (updateNum == 1) {   //修改行数为1，说明版本号对应，中间无其他人修改
//					flag=1;
//				}
//			}
//		}
//		
//		bookingDao.booking(bookingEntity);
//		int id = bookingEntity.getId();
//		//拼接订单号：当前日期+4位随机数+主键ID
//		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
//		String dateString = myFmt.format(new Date());
//		Random ne = new Random();
//		int randomString = ne.nextInt(9999-1000+1)+1000;
//		String orderNum = dateString+randomString+id;
//		bookingEntity.setOrderNum(orderNum);
//		bookingDao.updateOrderNum(bookingEntity);
		
		return "";
	}

}
