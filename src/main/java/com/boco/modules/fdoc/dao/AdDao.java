package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.AdEntity;

/**
 * 
 * @author sunche
 * @Time 2016年8月3日下午2:15:48
 */
@MyBatisDao
public interface AdDao extends CrudDao<AdEntity>{
	/**
	 * 查询广告列表
	 * @return
	 */
	public List<AdEntity> findAdList();
}
