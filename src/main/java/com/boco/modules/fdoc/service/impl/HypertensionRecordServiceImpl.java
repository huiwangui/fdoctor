package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.constants.BusinessConstants;
import com.boco.modules.fdoc.dao.DoctorDao;
import com.boco.modules.fdoc.dao.HealthAssessmentDao;
import com.boco.modules.fdoc.dao.HypertensionRecordDao;
import com.boco.modules.fdoc.dao.PersonInformationDao;
import com.boco.modules.fdoc.dao.SignDao;
import com.boco.modules.fdoc.model.HealthAssessmentEntity;
import com.boco.modules.fdoc.model.HypertensionRecordEntity;
import com.boco.modules.fdoc.service.HypertensionRecordService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.HypertensionRecordVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SignVo;

@Service
public class HypertensionRecordServiceImpl implements HypertensionRecordService {
	@Resource
	HypertensionRecordDao hypertensionRecordDao;
	@Resource
	HealthAssessmentDao assessmentDao;
	@Resource
	SignDao signDao;
	@Resource
	PersonInformationDao personDao;

	@Override
	public Map<String, Integer> getRecordCount(String personId) {
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		HypertensionRecordVo vo = new HypertensionRecordVo();
		vo.setPersonId(personId);
		//获取总数量
		Integer count = hypertensionRecordDao.getRecordCount(vo);
		resultMap.put("allCount", count);
		//获取正常血压数量
		vo.setDetectionResults(BusinessConstants.HYPERTENSION_JUDGE_NORMAL);
		count = hypertensionRecordDao.getRecordCount(vo);
		resultMap.put("normalCount", count);
		//获取高血压数量
		vo.setDetectionResults(BusinessConstants.HYPERTENSION_JUDGE_HIGN);
		count = hypertensionRecordDao.getRecordCount(vo);
		resultMap.put("highCount", count);
		//获取低血压数量
		vo.setDetectionResults(BusinessConstants.HYPERTENSION_JUDGE_LOW);
		count = hypertensionRecordDao.getRecordCount(vo);
		resultMap.put("lowCount", count);
		
		return resultMap;
	}

	@Override
	public List<HypertensionRecordEntity> getRecordList(String personId) {
		return hypertensionRecordDao.getRecordList(personId);
	}

	@Override
	public HypertensionRecordEntity getLastRecord(String personId) {
		return hypertensionRecordDao.getLastRecord(personId);
	}

	@Override
	public HypertensionRecordVo getLastRecordInDay(HypertensionRecordVo vo) {
		return hypertensionRecordDao.getLastRecordInDay(vo);
	}

	@Override
	@Transactional
	public Map<String, Object> addHypertensionRecord(HypertensionRecordEntity entity) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		//获取签约信息
		PersonInformationVo personInfo = personDao.getPersonDetailByPersonId(entity.getPersonId());
		SignVo signInfo = signDao.getSignDetailByIdCard(personInfo.getIdCard());
		if (signInfo == null) {
			// 若居民未签约，返回未签约信息
			returnMap.put("returnMsg", BusinessConstants.FAIL);
			return returnMap;
		}
		Date newDate = new Date();
		// 设置记录时间
		entity.setMeasuringTime(newDate);
		// 设置血压监测结果
		boolean abnormalFlag = false;	//异常标识
		
		if (entity.getSystolicPressure() < 90 || entity.getDiastolicPressure() < 60) {
			// 舒张压<60或收缩压<90或为低血压
			entity.setDetectionResult(BusinessConstants.HYPERTENSION_RESULT_LOW);
			abnormalFlag = true;
		}else if (entity.getSystolicPressure() >= 90 && entity.getSystolicPressure() <= 120 && entity.getDiastolicPressure() >= 60 && entity.getDiastolicPressure() <= 80) {
			// 收缩压在[90,120]且舒张压在[60,80]范围为正常血压
			entity.setDetectionResult(BusinessConstants.HYPERTENSION_RESULT_NORMAL);
		}else if ((entity.getSystolicPressure() > 120 && entity.getSystolicPressure() < 140)  || (entity.getDiastolicPressure() > 80 && entity.getDiastolicPressure() < 90)) {
			// 收缩压在(120,140)或舒张压在(80,90)为正常高值
			entity.setDetectionResult(BusinessConstants.HYPERTENSION_RESULT_NORMAL_HIGN);
		}else if ((entity.getSystolicPressure() >= 140 && entity.getSystolicPressure() < 160) || (entity.getDiastolicPressure() >= 90 && entity.getSystolicPressure() <	100) ) {
			// 收缩压在[90,160)或舒张压在[90,100)，为轻度偏高
			entity.setDetectionResult(BusinessConstants.HYPERTENSION_RESULT_HIGH_LEVEL1);
			abnormalFlag = true;
		}else if ((entity.getSystolicPressure() >= 160 && entity.getSystolicPressure() < 180) || (entity.getDiastolicPressure() >= 100 && entity.getSystolicPressure() < 110) ) {
			// 收缩压在[160,180)或舒张压在[100,110)，为中度偏高
			entity.setDetectionResult(BusinessConstants.HYPERTENSION_RESULT_HIGH_LEVEL2);
			abnormalFlag = true;
		}else if (entity.getSystolicPressure() >= 180 || entity.getDiastolicPressure() >= 110) {
			// 收缩压>=180，或舒张压>= 110，为重度偏高
			entity.setDetectionResult(BusinessConstants.HYPERTENSION_RESULT_HIGH_LEVEL3);
			abnormalFlag = true;
		}
		hypertensionRecordDao.insert(entity);
		if (abnormalFlag) {
			// 设置健康评估初始化对象
			HealthAssessmentEntity assessment = new HealthAssessmentEntity();
			assessment.setDocTeamId(signInfo.getDocTeamId());
			assessment.setPersonId(entity.getPersonId());
			assessment.setExceptionType(BusinessConstants.HEALTH_ASSESSMENT_TYPE_HYPER);	//类型设置为血压异常
			assessment.setSystolicPressure(entity.getSystolicPressure());
			assessment.setDiastolicPressure(entity.getDiastolicPressure());
			assessment.setDetectionResult(entity.getDetectionResult());
			assessment.setRemark(entity.getRemarks());
			assessment.setCreateTime(newDate);
			assessment.setStatus(BusinessConstants.HEALTH_ASSESSMENT_STATUS_WAIT);//设置状态为待评估
			assessmentDao.insert(assessment);
			returnMap.put("assessmentId", assessment.getId());
		}
		returnMap.put("returnMsg", BusinessConstants.SUCCESS);
		returnMap.put("abnormalFlag", abnormalFlag);
		returnMap.put("createTime", newDate.getTime());
		return returnMap;
	}

	@Override
	public Map<String, Object> getLastAbnormalSignRecord(String personId) {
		return hypertensionRecordDao.getLastAbnormalSignRecord(personId);
	}


}
