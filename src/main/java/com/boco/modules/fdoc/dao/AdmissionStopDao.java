package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.AdmissionStopEntity;

/**
 * 
 * @author sunche
 * @Time 2016年8月3日下午3:05:09
 */

@MyBatisDao

public interface AdmissionStopDao extends CrudDao<AdmissionStopEntity>{
	/**
	 * 获取停诊列表
	 * @return
	 */
	public List<AdmissionStopEntity> getStopList();
	/**
	 * 获取停诊详情
	 * @param id
	 * @return
	 */
	public AdmissionStopEntity getStopDetail(int id);
}
