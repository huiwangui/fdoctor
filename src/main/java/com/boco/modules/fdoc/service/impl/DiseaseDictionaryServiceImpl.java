package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.DiseaseDictionaryDao;
import com.boco.modules.fdoc.model.DiseaseDictionaryEntity;
import com.boco.modules.fdoc.service.DiseaseDictionaryService;

@Service
public class DiseaseDictionaryServiceImpl implements DiseaseDictionaryService{
	
	@Resource
	DiseaseDictionaryDao diseaseDao;

	@Override
	public List<DiseaseDictionaryEntity> getAllDictionary() {
		return diseaseDao.getAllDictionary();
	}

	@Override
	public DiseaseDictionaryEntity getDictionaryById(String diseaseId) {
		return diseaseDao.getDictionaryById(diseaseId);
	}

}
