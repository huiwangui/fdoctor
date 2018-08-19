package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.DoctorCommentEntity;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.QuickBookingVo;
/**
 * 
 * @author sunche
 * @Time 2016年8月2日下午3:10:55
 */
@MyBatisDao
public interface DoctorUserDao extends CrudDao<DoctorUserEntity>{
	/**
	 * 
	 * verifyUser:(通过用户名和密码验证登录). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public DoctorUserEntity verifyUser(DoctorUserEntity entity);
	
	/**
	 * 
	 * verifyUser:(通过用户名查找用户). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public DoctorUserEntity getUserByUsername(String userName);
	/**
	 * 
	 * getDoctorByUsername:(通过用户名获取医生). <br/>
	 * @author q
	 * @param userName
	 * @return
	 */
	public DoctorDetailVo getDoctorByUsername(String userName);
	/**
	 * 
	 * getAccountsByTeamId:(获取医生团队账号列表，用于推送). <br/>
	 * @author q
	 * @param teamId
	 * @return
	 */
	public List<String> getAccountsByTeamId(String teamId);
	/**
	 * 
	 * getTeamidByUsername:(通过用户名获取团队Id). <br/>
	 * @author lzz
	 * @param userName
	 * @return
	 */
	public String getTeamidByUsername(String userName);
}
