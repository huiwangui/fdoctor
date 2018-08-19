package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.HospitalCommentDao;
import com.boco.modules.fdoc.dao.UserDao;
import com.boco.modules.fdoc.model.HospitalCommentEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.service.HospitalCommentService;

@Service
public class HospitalCommentServiceImpl implements HospitalCommentService{
	@Resource
	HospitalCommentDao hospitalCommentDao;
	@Resource
	UserDao userDao;
	@Override
	public int add(HospitalCommentEntity hospitalCommentEntity) {
		// 设置评论人的nickname
		UserEntity user = userDao.get(hospitalCommentEntity.getUid());
		if (user != null) {
			hospitalCommentEntity.setNickname(user.getNickname());
			hospitalCommentEntity.setImg(user.getImg());
		}
		return hospitalCommentDao.insert(hospitalCommentEntity);
	}
	@Override
	public int grade(HospitalCommentEntity hospitalCommentEntity) {
		return hospitalCommentDao.grade(hospitalCommentEntity);
	}
	@Override
	public int delete(String id) {
		return hospitalCommentDao.delete(id);
	}
	@Override
	public List<HospitalCommentEntity> getByDocId(String id) {
		return hospitalCommentDao.getByDocId(id);
	}
	@Override
	public int reply(HospitalCommentEntity hospitalCommentEntity) {
		//设置评论人和回复人的nickname
		UserEntity user = userDao.get(hospitalCommentEntity.getUid());
		if (user != null) {
			hospitalCommentEntity.setNickname(user.getNickname());
			hospitalCommentEntity.setImg(user.getImg());
		}
		UserEntity rUser = userDao.get(hospitalCommentEntity.getrUid());
		if (rUser != null) {
			hospitalCommentEntity.setrNickname(rUser.getNickname());
			hospitalCommentEntity.setrImg(rUser.getImg());
		}
		return hospitalCommentDao.insert(hospitalCommentEntity);
	}
	
	

}
