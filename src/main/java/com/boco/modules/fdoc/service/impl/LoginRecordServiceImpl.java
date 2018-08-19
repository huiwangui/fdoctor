package com.boco.modules.fdoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.LoginRecordDao;
import com.boco.modules.fdoc.model.LoginRecordEntity;
import com.boco.modules.fdoc.service.LoginRecordService;

@Service
public class LoginRecordServiceImpl implements LoginRecordService{
	
	@Resource
	LoginRecordDao recordDao;

	@Override
	public Integer insertLoginRecord(LoginRecordEntity lrEntity) {
		return recordDao.insertLoginRecord(lrEntity);
	}

}
