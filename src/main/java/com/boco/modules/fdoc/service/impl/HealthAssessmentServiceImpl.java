package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.constants.BusinessConstants;
import com.boco.modules.fdoc.dao.HealthAssessmentDao;
import com.boco.modules.fdoc.model.HealthAssessmentEntity;
import com.boco.modules.fdoc.service.HealthAssesmentService;
import com.boco.modules.fdoc.vo.HealthAssessmentVo;

@Service
public class HealthAssessmentServiceImpl implements HealthAssesmentService{
	
	@Resource
	HealthAssessmentDao assessmentDao;

	@Override
	public Integer addHealthAssesment(HealthAssessmentEntity entity) {
		entity.setCreateTime(new Date());
		assessmentDao.insert(entity);
		return entity.getId();
	}

	@Override
	public Integer updateHealthAssesment(HealthAssessmentEntity entity) {
		entity.setAssessmentTime(new Date());	//设置评估时间为当前时间
		entity.setStatus(BusinessConstants.HEALTH_ASSESSMENT_STATUS_COMPLETED);	//设置状态为已评估
		return assessmentDao.update(entity);
	}

	@Override
	public List<HealthAssessmentVo> getHealthAssessmentList(
			HealthAssessmentVo vo) {
		return assessmentDao.getHealthAssessmentList(vo);
	}

	@Override
	public Integer getHealthAssessmentCount(HealthAssessmentVo vo) {
		return assessmentDao.getHealthAssessmentCount(vo);
	}

	@Override
	public List<HealthAssessmentVo> getHealthAssessmentListByUser(
			HealthAssessmentVo vo) {
		return assessmentDao.getHealthAssessmentListByUser(vo);
	}

	@Override
	public HealthAssessmentVo getHealthAssessmentDetail(Integer id) {
		return assessmentDao.getHealthAssessmentDetail(id);
	}

}
