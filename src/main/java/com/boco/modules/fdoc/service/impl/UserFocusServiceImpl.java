package com.boco.modules.fdoc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.DoctorDao;
import com.boco.modules.fdoc.dao.HospitalDao;
import com.boco.modules.fdoc.dao.UserFocusDao;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.model.UserFocusEntity;
import com.boco.modules.fdoc.service.UserFocusService;

@Service
public class UserFocusServiceImpl implements UserFocusService{

	@Resource
	UserFocusDao userFocusDao;
	@Resource
	DoctorDao doctorDao;
	@Resource
	HospitalDao hospitalDao;
	
	// 用户关注医生/医院
	/**
	 * 
	 * @Override
	public int userFollow(UserFocusEntity userFocusEntity) {
		userFocusEntity.setStatus("1");
		if ("1".equals(userFocusEntity.getType())) {   //类型为1  说明为对医院的关注
			HospitalEntity hospitalEntity = hospitalDao.get(userFocusEntity.getObjId());
			if (hospitalEntity != null) {
				userFocusEntity.setObjName(hospitalEntity.getName());
			}
		}else if("2".equals(userFocusEntity.getType())){ //类型为2  说明为对医生的关注
			DoctorEntity doctorEntity = doctorDao.getDetail(userFocusEntity.getObjId());
			if (doctorEntity != null) {
				userFocusEntity.setObjName(doctorEntity.getName());
			}
		}
		int count = userFocusDao.getCount(userFocusEntity);
		if (count == 0) {  //如果查询出为0，则表示之前没有关注过，新增一条关注
			return userFocusDao.insert(userFocusEntity);
		}else {  //结果不为0 :曾经关注过并取消，修改状态为“已关注”
			userFocusEntity.setCancelTime(null);
			return userFocusDao.changeStatus(userFocusEntity);
		}
	}


	// 用户取消关注
	@Override
	public int userFocus(UserFocusEntity userFocusEntity) {
		userFocusEntity.setStatus("2");
		userFocusEntity.setCancelTime(new Date());
		return userFocusDao.changeStatus(userFocusEntity);
	}


	@Override
	public int getCount(UserFocusEntity userFocusEntity) {
		
		return userFocusDao.getCount(userFocusEntity);
	}
	 * 
	 */
	

}
