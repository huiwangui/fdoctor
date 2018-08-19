package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.ReferralOrgEntity;

@MyBatisDao
public interface ReferralOrgDao extends CrudDao<ReferralOrgEntity>{
	/**
	 * 获取双向转诊转入、转出机构列表
	 * @param entity
	 * @return
	 */
	public List<ReferralOrgEntity> getReferralOrgList(ReferralOrgEntity entity);
	
	/**
	 * 获取双向转诊转入、转出机构数量
	 * @param entity
	 * @return
	 */
	public Integer getReferralOrgCount(ReferralOrgEntity entity);
}
