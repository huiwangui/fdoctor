package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.ReferralOrgDao;
import com.boco.modules.fdoc.model.ReferralOrgEntity;
import com.boco.modules.fdoc.service.ReferralOrgService;

@Service
public class ReferralOrgServiceImpl implements ReferralOrgService{
	
	@Resource
	ReferralOrgDao referralOrgDao;

	@Override
	public List<ReferralOrgEntity> getReferralOrgList(ReferralOrgEntity entity) {
		return referralOrgDao.getReferralOrgList(entity);
	}

	@Override
	public Integer getReferralOrgCount(ReferralOrgEntity entity) {
		return referralOrgDao.getReferralOrgCount(entity);
	}

}
