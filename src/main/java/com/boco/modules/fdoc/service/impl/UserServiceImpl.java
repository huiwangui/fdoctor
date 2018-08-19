package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.exception.IMException;
import com.boco.common.im.IMUtils;
import com.boco.modules.fdoc.dao.PersonInformationDao;
import com.boco.modules.fdoc.dao.UserDao;
import com.boco.modules.fdoc.dao.UserReportDao;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.model.UserReportEntity;
import com.boco.modules.fdoc.service.UserService;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.UserParamVo;
import com.boco.modules.fdoc.vo.UserReportParamVo;
import com.boco.modules.fdoc.vo.UserReportVo;
import com.boco.modules.fdoc.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	UserDao userDao;
	@Resource
	PersonInformationDao personDao;
	@Resource
	UserReportDao userReportDao;

	@Override
	@Transactional
	public String register(UserEntity entity) throws IMException {
		//判断重复
		UserEntity user = userDao.getUserByMobile(entity.getMobile());
		if (user != null) {
			return BusinessConstants.MOBILE_REPEAT;
		}
		String uid = UUID.randomUUID().toString();
		//注册操作
		entity.setUid(uid);//设置UID
		entity.setCreateTime(new Date());	//设置创建时间
		entity.setAuthenFlag(BusinessConstants.AUTHEN_FLAG_NO);	//设置实名认证为未认证
		entity.setDelFlag(BusinessConstants.DEL_FLAG_NO);	//设置删除标识为未删除
		entity.setStatus(BusinessConstants.USER_STATUS_NORMAL);	//设置用户状态为正常
		
		userDao.insert(entity);
		
		//注册im账号
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("userName", uid);
		userMap.put("password",entity.getPassword());
		Integer flag = IMUtils.userRegister(userMap);
		if (flag == ApiStatusEnum.FAIL.getKey()) {
			// 抛出IM注册异常，回滚注册
			throw new IMException();
		}
		return BusinessConstants.REGISTER_SUCCESS;
	}

	@Override
	@Transactional
	public Integer updateUser(UserEntity entity) {
		entity.setUpdateTime(new Date());
		int returnInt = userDao.update(entity);
		// 修改IM账号
		UserVo vo = new UserVo();
		vo.setNickname(entity.getNickname());
		vo.setImg(entity.getImg());
		vo.setPassword(entity.getPassword());
		vo.setUid(entity.getUid());
		//修改IM昵称为真实姓名
		UserVo userDetail = userDao.getUserDetail(entity.getUid());
		vo.setPersonName(userDetail.getPersonName());
		
		Integer changeUser = IMUtils.changeUser(vo);
		if (changeUser == ApiStatusEnum.FAIL.getKey()) {
			throw new IMException();
		}
		return returnInt;
	}

	@Override
	public UserEntity getUserByMobile(String mobile) {
		return userDao.getUserByMobile(mobile);
	}

	@Override
	public UserEntity verifyUser(UserEntity entity) {
		return userDao.verifyUser(entity);
	}

	@Override
	public String updateMobile(UserEntity entity) {
		//判断重复
		UserEntity user = userDao.getOtherUserByMobile(entity);
		if (user != null) {
			return BusinessConstants.MOBILE_REPEAT;
		}else {
			//修改用户
			userDao.update(entity);
			return BusinessConstants.REGISTER_SUCCESS;
		}
	}

	@Override
	public UserEntity getUserByUid(String uid) {
		return userDao.getUserByUid(uid);
	}

	@Override
	public String authen(UserVo vo) {
		PersonInformationEntity personInfo = personDao.getPersonInfoByIdcardAndName(vo.getIdCard(), vo.getPersonName());
		// 若居民未找到，返回
		if (personInfo == null) {
			return BusinessConstants.PERSON_NOT_FOUND;
		}else {
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
		}
	}

	@Override
	public UserVo getUserDetail(String uid) {
		return userDao.getUserDetail(uid);
	}

	@Override
	public String getAccountsByPersonId(String personId) {
		return userDao.getAccountsByPersonId(personId);
	}

	@Override
	public Integer updateUserReport(UserReportEntity entity) { 
		return  userReportDao.updateUserReport(entity);
	}

	@Override
	public UserReportVo getUserReportByUid(String uid) {	
		return userReportDao.getUserReportByUid(uid);
	}

	@Override
	public Integer insertUserReport(UserReportEntity entity) { 
		return  userReportDao.insertUserReport(entity);
	}

	@Override
	public UserReportVo getDoctorIdByUsername(String userName) {
		 
		return userReportDao.getDoctorIdByUsername(userName);
	}

	@Override
	public UserReportVo getInformationByUid(String uid) {
		return userReportDao.getInformationByUid(uid);
	}

	@Override
	public Integer insertUserReportByDoc(UserReportEntity entity) {
		 
		return userReportDao.insertUserReportByDoc(entity);
	}

	@Override
	public UserReportVo getPersonImgByUid(String uid) {
		 
		return userReportDao.getPersonImgByUid(uid);
	}

	@Override
	public UserReportVo getdoctorIdByUid(String uid) {
		 
		return userReportDao.getdoctorIdByUid(uid);
	}

	@Override
	public List<UserReportVo> getAllUserReportByUid(UserReportVo userReportVo) {
		 
		return userReportDao.getAllUserReportByUid(userReportVo);
	}

	@Override
	public UserReportVo getDoctorNameBydoctorId(String doctorId) {
		 
		return userReportDao.getDoctorNameBydoctorId(doctorId);
	}

	@Override
	public UserReportVo getPersonNameByPersonId(String personId) {
		return userReportDao.getPersonNameByPersonId(personId);
	}

	@Override
	public List<UserReportVo> getAllUserReportByPersonId(UserReportParamVo vo) {
		return userReportDao.getAllUserReportByPersonId(vo);
	}

	@Override
	public UserReportVo getSingleUserReportByUid(String uid) {
	 
		return userReportDao.getSingleUserReportByUid(uid);
	}

	@Override
	public UserReportVo getSingleUserReportByPersonId(UserReportParamVo vo) {
		 
		return userReportDao.getSingleUserReportByPersonId(vo);
	}

	@Override
	public List<UserReportVo> getCountByUid(String uid) {
		return userReportDao.getCountByUid(uid);
	}

	@Override
	public List<UserReportVo> getCountByPersonId(UserParamVo vo) {
		return userReportDao.getCountByPersonId(vo);
	}

	@Override
	public Integer insertUserReportByUser(UserReportEntity entity) {
		 
		return userReportDao.insertUserReportByUser(entity);
	}

	@Override
	public UserReportVo getdoctorIdByPersonId(String personId) {
		 
		return userReportDao.getdoctorIdByPersonId(personId);
	}

}
