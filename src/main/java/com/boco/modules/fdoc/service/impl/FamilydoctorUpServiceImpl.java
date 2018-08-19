package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.FamilydoctorUpDao;
import com.boco.modules.fdoc.service.FamilydoctorUpService;
import com.boco.modules.fdoc.vo.SignVo;
import com.boco.modules.fdoc.vo.UpPlanVo;
@Service
public class FamilydoctorUpServiceImpl implements FamilydoctorUpService {
	@Resource
	FamilydoctorUpDao  familydoctorUpDao;

	@Override
	public List<UpPlanVo> getSignedListByIdCard(UpPlanVo vo) {
		 
		return familydoctorUpDao.getSignedListByIdCard(vo);
	}

	@Override
	public List<UpPlanVo> getSignedListByFinishFlag(UpPlanVo vo) {		 
		return familydoctorUpDao.getSignedListByFinishFlag(vo);
	}

	@Override
	public List<UpPlanVo> getFinishSignedListByHealthFileCode(UpPlanVo vo) {
		// TODO Auto-generated method stub
		return familydoctorUpDao.getFinishSignedListByHealthFileCode(vo);
	}

	@Override
	public int deleteUnFinishUpPlanByHealthFileCodeAndDiseaseId(UpPlanVo vo) {
		// TODO Auto-generated method stub
		return familydoctorUpDao.deleteUnFinishUpPlanByHealthFileCodeAndDiseaseId(vo);
	}

	@Override
	public List<UpPlanVo> getunFinishSignedListBydiseaseId(UpPlanVo vo) {
		 
		return familydoctorUpDao.getunFinishSignedListBydiseaseId(vo);
	}

	@Override
	public UpPlanVo getUpPlan(UpPlanVo vo) {
		 
		return familydoctorUpDao.getUpPlan(vo);
	}

	@Override
	public Integer updateUpPlan(UpPlanVo vo) {
		 
		return familydoctorUpDao.updateUpPlan(vo);
	}

	@Override
	public int deleteUnFinishUpPlanById(UpPlanVo vo) {
		 
		return familydoctorUpDao.deleteUnFinishUpPlanById(vo);
	}

	@Override
	public UpPlanVo getunFinishSignedListById(UpPlanVo vo) {
		// TODO Auto-generated method stub
		return familydoctorUpDao.getunFinishSignedListById(vo);
	}

	@Override
	public int getSignedListCountByFinishFlag(UpPlanVo vo) {
		// TODO Auto-generated method stub
		return familydoctorUpDao.getSignedListCountByFinishFlag(vo);
	}

	@Override
	public int getFinishSignedListCountByHealthFileCode(UpPlanVo vo) {
		// TODO Auto-generated method stub
		return familydoctorUpDao.getFinishSignedListCountByHealthFileCode(vo);
	}

	@Override
	public UpPlanVo getUpplanByHealthFileCode(UpPlanVo vo) {
		// TODO Auto-generated method stub
		return familydoctorUpDao.getUpplanByHealthFileCode(vo);
	}
	
}
