package com.boco.modules.fdoc.dao;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.ApiCallerEntity;

/**
 * 对外接口调用方dao
 * @author q
 *
 */
@MyBatisDao
public interface ApiCallerDao extends CrudDao<ApiCallerEntity>{
	/**
	 * 获取对外接口调用方
	 * @param entity
	 * @return
	 */
	public ApiCallerEntity getApiCaller(ApiCallerEntity entity);
}
