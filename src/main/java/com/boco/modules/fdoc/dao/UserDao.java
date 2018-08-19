package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.vo.UserVo;

/**
 * 用户dao
 * @author sufj
 *
 */
@MyBatisDao
public interface UserDao extends CrudDao<UserEntity>  {
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
	 * getUserByMobile:(根据uid获取用户). <br/>
	 * @author q
	 * @param mobile
	 * @return
	 */
	public UserEntity getUserByUid(String uid);
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
	 * getOtherUserByMobile:(获取除当前人以外电话为xxx的用户,用于转绑手机之前的判断). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public UserEntity getOtherUserByMobile(UserEntity entity);
	/**
	 * 
	 * getUserDetail:(获取用户详情). <br/>
	 * @author q
	 * @return
	 */
	public UserVo getUserDetail(String uid);
	/**
	 * 
	 * getUserByPersonId:(根据居民ID获取用户). <br/>
	 * @author q
	 * @param personId
	 * @return
	 */
	public UserEntity getUserByPersonId(String personId);
	/**
	 * 
	 * getAccountsByPersonId:(获取居民账号，用于推送). <br/>
	 * @author q
	 * @param personId
	 * @return
	 */
	public String getAccountsByPersonId(String personId);
}
