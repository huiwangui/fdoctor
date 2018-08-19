package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.dao.AdmissionStopDao;
import com.boco.modules.fdoc.model.AdmissionStopEntity;
import com.boco.modules.fdoc.service.AdmissionStopService;

@Service

public class AdmissionStopServiceImpl implements AdmissionStopService{
	
	@Resource
	AdmissionStopDao admissionStopDao;
	
	@Override
	public BaseJsonVo getStopList() {
		List<AdmissionStopEntity> list = admissionStopDao.getStopList();
		BaseJsonVo vo = new BaseJsonVo();
		vo.setData(list);
		return vo;
	}

	@Override
	public BaseJsonVo getStopDetail(int id) {
		AdmissionStopEntity entity = admissionStopDao.getStopDetail(id);
		BaseJsonVo vo = new BaseJsonVo();
		vo.setData(entity);
		return vo;
	}

}
