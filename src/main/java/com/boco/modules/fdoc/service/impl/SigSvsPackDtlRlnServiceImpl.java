package com.boco.modules.fdoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.SigSvsPackDtlRlnDao;
import com.boco.modules.fdoc.model.SigSvsPackDtlRlnEntity;
import com.boco.modules.fdoc.service.SigSvsPackDtlRlnService;
@Service
public class SigSvsPackDtlRlnServiceImpl implements SigSvsPackDtlRlnService{
	@Resource
	SigSvsPackDtlRlnDao sigSvsPackDtlRlnDao;

	@Override
	public int insertSvsPackDtlInfo(SigSvsPackDtlRlnEntity sigSvsPackDtlRlnEntity) {
		return sigSvsPackDtlRlnDao.insertSvsPackRlnInfo(sigSvsPackDtlRlnEntity);
	}
	

}
