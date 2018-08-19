package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.common.exception.IMException;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.model.UserReportEntity;
import com.boco.modules.fdoc.vo.UserParamVo;
import com.boco.modules.fdoc.vo.UserReportParamVo;
import com.boco.modules.fdoc.vo.UserReportVo;
import com.boco.modules.fdoc.vo.UserVo;


/**
 * 用户管理
 * @author sufj
 */
public interface UserService {
	/**
	 * 查询出居民最近上传图片记录 居民端
	 * @param uid
	 * @return
	 */
	public UserReportVo getSingleUserReportByUid(String uid);
	/**
	 * 查询出居民最近上传图片记录 医生端
	 * @param vo
	 * @return
	 */
	public UserReportVo getSingleUserReportByPersonId(UserReportParamVo vo);
	/**
	 * 根据医生docId查询出医生名称 
	 * @param doctorId
	 * @return
	 */
	public UserReportVo getDoctorNameBydoctorId(String doctorId);
	/**
	 * 根据居民personId查询出居民名称 
	 * @param personId
	 * @return
	 */
	public  UserReportVo getPersonNameByPersonId(String personId);
	/**
	 * 查询出居民所有上传的图片记录 居民端
	 * @param uid
	 * @return
	 */
	public List<UserReportVo> getAllUserReportByUid(UserReportVo userReportVo);
	/**
	 * 查询出居民所有上传的图片记录 医生端
	 * @param uid
	 * @return
	 */
	public List<UserReportVo> getAllUserReportByPersonId(UserReportParamVo vo);
	/**
	 * 
	 * register:(注册). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public String register(UserEntity entity)  throws IMException;
	/**
	 * 
	 * updateUser:(修改个人信息). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Integer updateUser(UserEntity entity);
	/**
	 * 
	 * getUserByMobile:(根据手机号获取用户). <br/>
	 * @author q
	 * @param mobile
	 * @return
	 */
	public UserEntity getUserByMobile(String mobile);
	/**
	 * 
	 * verifyUser:(登录验证). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public UserEntity verifyUser(UserEntity entity);
	/**
	 * 
	 * updateMobile:(修改手机号). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public String updateMobile(UserEntity entity);
	/**
	 * 
	 * getUserByMobile:(根据uid获取用户). <br/>
	 * @author q
	 * @param mobile
	 * @return
	 */
	public UserEntity getUserByUid(String uid);
	/**
	 * 
	 * authen:(实名认证). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public String authen(UserVo vo);
	/**
	 * 
	 * getUserDetail:(获取用户详情). <br/>
	 * @author q
	 * @return
	 */
	public UserVo getUserDetail(String uid);
	/**
	 * 
	 * getAccountsByPersonId:(获取居民账号，用于推送). <br/>
	 * @author q
	 * @param personId
	 * @return
	 */
	public String getAccountsByPersonId(String personId);
	/**
	 * 修改个人报告信息
	 * @param entity
	 * @return
	 */
	public Integer updateUserReport(UserReportEntity entity);
	/**
	 * 根据uid获取用户报告信息
	 * @param uid
	 * @return
	 */
	public UserReportVo getUserReportByUid(String uid);
	/**
	 * 增加居民上传记录
	 * @param entity
	 * @return
	 */
	public Integer insertUserReport(UserReportEntity entity);
	public UserReportVo getDoctorIdByUsername(String userName);
	public UserReportVo getInformationByUid(String uid);
	public Integer insertUserReportByDoc(UserReportEntity entity);
	/**
	 * 根据居民id查询此人上传图片的记录 
	 * @param uid
	 * @return
	 */
	public UserReportVo getPersonImgByUid(String uid);
	/**
	 * 查询出居民签约医生的docid 居民端
	 * @param uid
	 * @return
	 */
	public UserReportVo getdoctorIdByUid(String uid);
	public List<UserReportVo> getCountByUid(String uid);
	public List<UserReportVo> getCountByPersonId(UserParamVo vo);
	public Integer insertUserReportByUser(UserReportEntity entity);
	public UserReportVo getdoctorIdByPersonId(String personId);
}
