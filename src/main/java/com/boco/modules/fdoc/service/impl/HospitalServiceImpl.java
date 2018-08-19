package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.dao.HospitalDao;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.service.HospitalService;
import com.boco.modules.fdoc.vo.HospitalVo;
import com.boco.modules.fdoc.vo.QuickBookingVo;

@Service
public class HospitalServiceImpl implements HospitalService{
	
	@Resource
	HospitalDao hospitalDao;

	@Override
	public List<HospitalVo> getHospitalListWithTeamNum(HospitalVo vo) {
		return hospitalDao.getHospitalListWithTeamNum(vo);
	}

	@Override
	public List<HospitalVo> getTeamInHospital(String orgId) {
		return hospitalDao.getTeamInHospital(orgId);
	}

	@Override
	public HospitalEntity getHospitalByUid(String uid) {
		 
		return hospitalDao.getHospitalByUid(uid);
	}

	@Override
	public HospitalEntity getHospitalInfo(String orgId) {
		return hospitalDao.getHospitalInfo(orgId);
	}
	
}
