package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.constants.BusinessConstants;
import com.boco.modules.fdoc.dao.BookingDao;
import com.boco.modules.fdoc.dao.DoctorCommentDao;
import com.boco.modules.fdoc.dao.UserDao;
import com.boco.modules.fdoc.model.BookingEntity;
import com.boco.modules.fdoc.model.DoctorCommentEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.service.DoctorCommentService;


@Service
public class DoctorCommentServiceImpl implements DoctorCommentService{
	
	@Resource
	DoctorCommentDao doctorCommentDao;
	@Resource
	UserDao userDao;
	@Resource
	BookingDao bookingDao;
	
	@Override
	public int add(DoctorCommentEntity doctorCommentEntity) {
		// 设置nickName
		UserEntity user = userDao.get(doctorCommentEntity.getUid());
		if (user != null) {
			doctorCommentEntity.setNickname(user.getNickname());
			doctorCommentEntity.setImg(user.getImg());
		}
		int returnNum = doctorCommentDao.insert(doctorCommentEntity);
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setId(doctorCommentEntity.getBookingId());
		bookingEntity.setPayStatus(BusinessConstants.PAY_STATUS_HASCOMMENT); //此订单设置为已评论
		bookingEntity.setSeeDocStatus(BusinessConstants.SEE_DOC_STATUS_HASDOC); //此订单设置为已就诊
		bookingDao.updateStatus(bookingEntity);
		return returnNum;
	}

	@Override
	public int grade(DoctorCommentEntity doctorCommentEntity) {
		return doctorCommentDao.grade(doctorCommentEntity);
		
	}

	@Override
	public int delete(String id) {
		return doctorCommentDao.delete(id);
	}

	@Override
	public List<DoctorCommentEntity> getByDocId(String id) {
		return doctorCommentDao.getByDocId(id);
	}

	@Override
	public int reply(DoctorCommentEntity doctorCommentEntity) {
		// 设置评论人和回复人的nickname
		UserEntity user = userDao.get(doctorCommentEntity.getUid());
		if (user != null) {
			doctorCommentEntity.setNickname(user.getNickname());
			doctorCommentEntity.setImg(user.getImg());
		}
		UserEntity rUser = userDao.get(doctorCommentEntity.getrUid());
		if (rUser != null) {
			doctorCommentEntity.setRNickname(rUser.getNickname());
			doctorCommentEntity.setrImg(rUser.getImg());
		}
		return doctorCommentDao.insert(doctorCommentEntity);
	}
	

}
