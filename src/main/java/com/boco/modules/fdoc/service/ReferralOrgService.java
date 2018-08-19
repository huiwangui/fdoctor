package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.ReferralOrgEntity;

public interface ReferralOrgService {
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
