package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.exception.IMException;
import com.boco.common.im.IMUtils;
import com.boco.modules.fdoc.dao.DoctorDao;
import com.boco.modules.fdoc.dao.DoctorUserDao;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;

@Service
public class DocUserServiceImpl implements DocUserService{
	
	@Resource
	DoctorUserDao docUserDao;
	@Resource
	DoctorDao docDao;

	@Override
	public DoctorUserEntity verifyUser(DoctorUserEntity entity) {
		return docUserDao.verifyUser(entity);
	}

	@Override
	@Transactional
	public int updateDocUser(DoctorUserEntity userEntity, DoctorEntity docEntity) {
		int returnInt = 0;
		Date updateTime = new Date();
		/**
		 * 如果医生用户对象不为空，则修改用户对象信息（头像、昵称等）
		 */
		if (userEntity != null) {
			userEntity.setUpdateTime(updateTime);
			returnInt = docUserDao.update(userEntity);
			Integer changeDocUser = IMUtils.changeDocUser(userEntity);
			if (changeDocUser == ApiStatusEnum.FAIL.getKey()) {
				throw new IMException();
			}
		}
		/**
		 * 如果医生对象不为空，则修改医生对象信息（简介、手机号）
		 */
		if (docEntity != null) {
			DoctorUserEntity userByUsername = docUserDao.getUserByUsername(userEntity.getUserName());
			docEntity.setId(userByUsername.getDoctorId());
			docEntity.setUpdateTime(updateTime);
			returnInt = docDao.update(docEntity);
		}
		return returnInt;
	}

	@Override
	public DoctorDetailVo getDoctorByUsername(String userName) {
		return docUserDao.getDoctorByUsername(userName);
	}

	@Override
	public List<String> getAccountsByTeamId(String teamId) {
		return docUserDao.getAccountsByTeamId(teamId);
	}

	@Override
	public String getTeamIdbyUsername(String userName) {
		return docUserDao.getTeamidByUsername(userName);
	}

}
