package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.exception.IMException;
import com.boco.common.im.IMUtils;
import com.boco.common.utils.IdCardUtils;
import com.boco.modules.fdoc.dao.PersonInformationDao;
import com.boco.modules.fdoc.dao.UserDao;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.service.UserServiceChange;
import com.boco.modules.fdoc.vo.UserVo;


@Service
public class UserServiceChangeImpl implements UserServiceChange{
	
	@Resource
	UserDao userDao;
	@Resource
	PersonInformationDao personDao;
	

	@Override
	@Transactional
	public String authen(UserVo vo) {
		PersonInformationEntity personInfo=	personDao.getPersonInfoByIdcardOnly(vo.getIdCard());
		// 若居民未找到，返回
		if (personInfo == null) {
			//则新增personId和familyId
			String personId=UUID.randomUUID().toString().replaceAll("-", "");
			String familyId=UUID.randomUUID().toString().replaceAll("-", "");
			
			PersonInformationEntity personEntity=new PersonInformationEntity();
			personEntity.setPersonId(personId);
			personEntity.setFamilyId(familyId);
			personEntity.setIdCard(vo.getIdCard());
			personEntity.setSex(IdCardUtils.getGenderByIdCard(vo.getIdCard()));
			personEntity.setDateOfBirth(IdCardUtils.getBirthByIdCard(vo.getIdCard()));
			
			personEntity.setPhoneNumber(vo.getMobile());
			personEntity.setPersonName(vo.getPersonName());
			personEntity.setIfSign(Integer.parseInt(BusinessConstants.NO));//设置为未签约
			
			personDao.insert(personEntity);
			//绑定关系
			UserEntity entity =new UserEntity();
			entity.setUid(vo.getUid());	
			entity.setPersonId(personId);	//设置居民ID
			entity.setAuthenFlag(BusinessConstants.AUTHEN_FLAG_YES);	//设置认证标志
			entity.setUpdateTime(new Date());
			//执行修改操作
			int num=userDao.update(entity);
			if(num!=1){
				return BusinessConstants.FAIL;
			}
			// 修改IM账号
			Integer changeUser = IMUtils.changeUser(vo);
			if (changeUser == ApiStatusEnum.FAIL.getKey()) {
				throw new IMException();
			}
			return BusinessConstants.SUCCESS;
			
		}else {
			if(personInfo.getPersonName().equals(vo.getPersonName())){//姓名相同
				UserEntity entity = userDao.getUserByPersonId(personInfo.getPersonId());
				if (entity != null) {
					//该居民已绑定了其他账号，无法重复绑定
					return BusinessConstants.AUTHEN_RESULT_REPEAT;
				}else {
					entity = new UserEntity();
					entity.setUid(vo.getUid());	
					entity.setPersonId(personInfo.getPersonId());	//设置居民ID
					entity.setAuthenFlag(BusinessConstants.AUTHEN_FLAG_YES);	//设置认证标志
					entity.setUpdateTime(new Date());
					//执行修改操作
					userDao.update(entity);
					
					// 修改IM账号
					Integer changeUser = IMUtils.changeUser(vo);
					if (changeUser == ApiStatusEnum.FAIL.getKey()) {
						throw new IMException();
					}
					return BusinessConstants.SUCCESS;
				}
			}else{
				return  "NOTSAMENAME"+"-"+personInfo.getPersonName();
			}
		}
	}

}
