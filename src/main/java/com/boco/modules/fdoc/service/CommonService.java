package com.boco.modules.fdoc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.modules.fdoc.dao.SmsCodeDao;
import com.boco.modules.fdoc.dao.UserDao;
import com.boco.modules.fdoc.model.SmsCodeEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.vo.UserVo;

/**
 * 公共service
 * 
 * @author sufj
 *
 */
@Service
public class CommonService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private SmsCodeDao smsCodeDao;

	/**
	 * 用户登录
	 * 
	 * @param userVo
	 * @return
	 */
	public BaseJsonVo login(UserVo userVo) {
//		String mobile = userVo.getMobile();// 手机号
//		String code = userVo.getCode();// 验证码
//
////		String appstore_mobile = PropertiesUtils.getValue(BusinessConstants.APPSTORE_MOBILE);
////		String appstore_code = PropertiesUtils.getValue(BusinessConstants.APPSTORE_CODE);
//
//		BaseJsonVo baseJsonVo = null;
//		/**验证阶段，暂未实现
//		if (appstore_mobile.equals(mobile)) {// 测试账号，不验证
//			if (!appstore_code.equals(code)) {
//				baseJsonVo = BaseJsonVo.error(ApiStatusEnum.CODE_ERROR);
//			}
//		} else {
//			baseJsonVo = validPhoneCode(mobile, code);
//		}
//		if (baseJsonVo != null) {
//			return baseJsonVo;
//		}
//		*/
//		UserEntity userEntity = new UserEntity();
//		userEntity.setMobile(mobile);
//		userEntity.setPassword(userVo.getPassword());
//		if (userVo.getSignUser() != null) {
//			userEntity.setSignUser(userVo.getSignUser());
//		}
//		
//		// 对用户实体重新赋值
//		userEntity = userDao.findByMobileAndPsw(userEntity);
//		// 判断是否查询到用户
//		if (StringUtils.isEmpty(userEntity)) {
//			return BaseJsonVo.empty(ApiStatusEnum.LOGIN_ERROR.getKey(), ApiStatusEnum.LOGIN_ERROR.getValue());
//		}
//		// 类移植
//		BeanUtils.copyProperties(userEntity, userVo);
//		// 存入缓存
//		// HttpSessionUtils.setSessionUser(userVo);
//		UserVo vo = userDao.getUserDetail(userEntity.getUid());
		return BaseJsonVo.success(null);
	}

	
	/**
	 * 发送验证码
	 * @param phone
	 * @return BaseJsonVo.success
	 * @author sufj
	 * @since 2016-07-28 15:09:48
	 */
	public BaseJsonVo sendPhoneCode(String phone) {

//		String smsRandom = SmsRandom.createRandom();// 获取随机码
//		//1- 保存短信日志
//		Date nowDate = DateUtils.getDate();
//		SmsCodeEntity entity = new SmsCodeEntity();
//		entity.setBusiId(BusinessConstants.SMS_CODE_BUSI);
//		entity.setBusiTypeId(BusinessConstants.SMS_CODE_BUSI);
//		entity.setMobile(phone);
//		entity.setMessage(smsRandom);
//		entity.setSendTime(nowDate);
//		smsCodeDao.insert(entity);
//		//2- 构造验证码短信
//		SmsVo smsVo = new SmsVo();
//		Object[] objs = new Object[] { PropertiesUtils.getValue(BusinessConstants.YP_SMS_COMPANY), smsRandom };
//		String content = PropertiesUtils.getValue(BusinessConstants.YP_SMS_TEMPLATE, objs);
//		smsVo.setPhone(phone);
//		smsVo.setContent(content);
//		smsVo.setSendTime(nowDate);
//		SmsQueueList.sharedQueue.add(smsVo);// 加入短信队列
//		//3- 返回结果
		return BaseJsonVo.success("send successful!");
	}
	/**
	 * 验证用户手机验证码
	 * 
	 * @param mobile
	 * @param code
	 * @return
	 */
	public BaseJsonVo validPhoneCode(String mobile, String code) {
		ApiStatusEnum enums = null;
		//1- 过期时间 5分(min)=300000毫秒(ms)
		String sms_code_timeout = PropertiesUtils.getValue(BusinessConstants.SMS_CODE_TIMEOUT);
		Integer number = Integer.parseInt(sms_code_timeout);
		SmsCodeEntity log = smsCodeDao.validatePhoneCode(mobile, code);
		
		if (log == null) {
			enums = ApiStatusEnum.CODE_ERROR;//验证码错误
		} else {
			Date sendTime = log.getSendTime();//发送时间
			Date nowDate = DateUtils.getDate();//系统当前时间
			if (nowDate.getTime() - sendTime.getTime() > number ) {// 相差小于等于5分钟
				enums = ApiStatusEnum.CODE_VALID;//验证码无效
			}
		}
		BaseJsonVo baseJsonVo = null;
		if (enums != null) {
			baseJsonVo = BaseJsonVo.error(enums);
		}
		return baseJsonVo;
	}

	/**
	 * 修改用户绑定手机号
	 * 
	 * @param userVo
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BaseJsonVo bindPhoneNumber(UserVo userVo) {
		
//		BaseJsonVo baseJsonVo = validPhoneCode(userVo.getMobile(), userVo.getCode());
//		if (baseJsonVo != null) {
//			return baseJsonVo;
//		}
//		// 验证码验证通过
//		UserEntity userEntity = userDao.get(userVo.getUid());
//		if (userEntity == null) {
//			return BaseJsonVo.error(ApiStatusEnum.USER_EMPTY);
//		}
//		userEntity.setUpdateTime(DateUtils.getDate());
//		userEntity.setMobile(userVo.getMobile());
//
//		// 更新用户绑定手机号
//		userDao.update(userEntity);
//
//		BeanUtils.copyProperties(userEntity, userVo);

		return BaseJsonVo.success(null);
	}

	/**
	 * 手机验证码校验
	 * 
	 * @param userVo
	 * @return
	 */
	public BaseJsonVo validPhoneCode(UserVo userVo) {
//		BaseJsonVo baseJsonVo = validPhoneCode(userVo.getMobile(), userVo.getCode());
//		if (baseJsonVo == null) {
//			baseJsonVo = BaseJsonVo.success("");
//		}
		return null;
	}

	/**
	 * 判断用户是否登陆（1：微信用户，2：手机用户）
	 * 
	 * @param loginType
	 * @return true:已登录 false:未登录
	 */
	protected boolean isLogined(int userType) {
		// TODO
		return true;
	}

	/**
	 * 查询手机号是否已注册
	 * 
	 * @param mobile
	 * @return
	 */
	private boolean isPhoneExist(String mobile) {
		UserEntity entity = new UserEntity();
		entity.setMobile(mobile);
		entity = userDao.getByEntity(entity);
		// 如果用户ID存在，返回用户已存在
		if (entity!= null && !StringUtils.isEmpty(entity.getUid())) {
			return true;
		}
		return false;
	}

//	/**
//	 * 手机用户注册
//	 * 
//	 * @param vo
//	 * @return
//	 */
//	public BaseJsonVo register(UserVo vo) throws BeanCreationException{
//		// 1-验证码检查
////		BaseJsonVo baseJsonVo = validPhoneCode(vo.getMobile(), vo.getCode());
////		if (baseJsonVo != null) {// null 标识验证通过
////			return baseJsonVo;
////		}
//		// 2-手机号是否已注册
//		if (isPhoneExist(vo.getMobile())) {
//			return BaseJsonVo.empty(ApiStatusEnum.PHONE_EXISTS.getKey(), ApiStatusEnum.PHONE_EXISTS.getValue());
//		}
//
//		UserEntity entity = new UserEntity();
//		entity.setMobile(vo.getMobile());
//		entity.setPassword(vo.getPassword());
//		entity.setType(vo.getType());
//		entity.setSignUser(vo.getSignUser());
//		entity.setUid(UUID.randomUUID().toString());//生成Uid
//		entity.setAddTime(new Date());
//		entity.setDelFlag("0");
//		entity.setStatus(UserStateEnum.NORMAL.getKey().toString());
//		// 3-插入用户表
//		userDao.insert(entity);
//
//		// 4-返回实体
//		BeanUtils.copyProperties(entity, vo);
//		// 5-存入缓存
//		// HttpSessionUtils.setSessionUser(vo);
//		return BaseJsonVo.success(entity);
//	}
	
}
