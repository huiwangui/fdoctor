package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.UserReportEntity;
import com.boco.modules.fdoc.vo.UserParamVo;
import com.boco.modules.fdoc.vo.UserReportParamVo;
import com.boco.modules.fdoc.vo.UserReportVo;
@MyBatisDao
public interface UserReportDao  extends CrudDao<UserReportEntity> {
	 
	public Integer insertUserReportByUser(UserReportEntity vo);
	public List<UserReportVo> getCountByUid(String uid);
	public List<UserReportVo> getCountByPersonId(UserParamVo vo);
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
	 * @param personId
	 * @return
	 */
	public List<UserReportVo> getAllUserReportByPersonId(UserReportParamVo vo);
	/**
	 * 更新居民报告信息(居民端)
	 * @param vo
	 * @return
	 */
	public Integer updateUserReport(UserReportEntity vo);
	/**
	 * 根据uid查询居民报告信息
	 * @param uid
	 * @return
	 */
	public UserReportVo getUserReportByUid(String uid);
	/**
	 * 增加居民报告
	 * @param vo
	 * @return
	 */
	public Integer insertUserReport(UserReportEntity vo);
	/**
	 * 
	 * @param username
	 * @return
	 */
	public UserReportVo getDoctorIdByUsername(String username);
	public UserReportVo getInformationByUid(String uid);
	/**
	 * 医生端
	 * @param entity
	 * @return
	 */
	public Integer insertUserReportByDoc(UserReportEntity entity);
	/**
	 * 根据居民id查询此人上传图片的记录  医生端 
	 * @param uid
	 * @return
	 */
	public UserReportVo getPersonImgByUid(String uid);
	/**
	 * 根据居民id查询出与他签约医生 的docid 居民端
	 * @param uid
	 * @return
	 */
	public UserReportVo getdoctorIdByUid(String uid);
	public UserReportVo getdoctorIdByPersonId(String personId);
}
