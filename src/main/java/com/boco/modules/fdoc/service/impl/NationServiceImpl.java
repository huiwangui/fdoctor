package com.boco.modules.fdoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.NationDao;
import com.boco.modules.fdoc.model.NationEntity;
import com.boco.modules.fdoc.service.NationService;

@Service
public class NationServiceImpl implements NationService{
	
	@Resource
	NationDao nationDao;

	@Override
	public NationEntity getNationByCode(String nationCode) {
		return nationDao.getNationByCode(nationCode);
	}

}
