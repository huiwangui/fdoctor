package com.boco.modules.fdoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.ApiCallerDao;
import com.boco.modules.fdoc.model.ApiCallerEntity;
import com.boco.modules.fdoc.service.ApiCallerService;

@Service
public class ApiCallerServiceImpl implements ApiCallerService{
	
	@Resource
	ApiCallerDao apiCallerDao;
	
	@Override
	public ApiCallerEntity getApiCaller(ApiCallerEntity entity) {
		return apiCallerDao.getApiCaller(entity);
	}

}
