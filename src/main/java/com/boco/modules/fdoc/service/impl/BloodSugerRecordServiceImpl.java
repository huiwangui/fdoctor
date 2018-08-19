package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.constants.BusinessConstants;
import com.boco.modules.fdoc.dao.BloodSugerRecordDao;
import com.boco.modules.fdoc.dao.DoctorDao;
import com.boco.modules.fdoc.dao.HealthAssessmentDao;
import com.boco.modules.fdoc.dao.PersonInformationDao;
import com.boco.modules.fdoc.dao.SignDao;
import com.boco.modules.fdoc.model.BloodSugerRecordEntity;
import com.boco.modules.fdoc.model.HealthAssessmentEntity;
import com.boco.modules.fdoc.service.BloodSugerRecordService;
import com.boco.modules.fdoc.vo.BloodSugerRecordVo;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SignVo;

@Service
public class BloodSugerRecordServiceImpl implements BloodSugerRecordService{
	
	@Resource
	BloodSugerRecordDao bloodSugerRecordDao;
	@Resource
	HealthAssessmentDao assessmentDao;
	@Resource
	SignDao signDao;
	@Resource
	PersonInformationDao personDao;

	@Override
	public Map<String, Integer> getRecordCount(String personId) {
		BloodSugerRecordVo vo = new BloodSugerRecordVo();
		vo.setPersonId(personId);
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		//查询总数
		Integer count = bloodSugerRecordDao.getRecordCount(vo);
		returnMap.put("allCount", count);
		//正常血糖数
		vo.setResults(BusinessConstants.BLOOD_SUGER_JUDGE_NORMAL);
		count = bloodSugerRecordDao.getRecordCount(vo);
		returnMap.put("normalCount", count);
		//高血糖数
		vo.setResults(BusinessConstants.BLOOD_SUGER_JUDGE_HIGH);
		count = bloodSugerRecordDao.getRecordCount(vo);
		returnMap.put("highCount", count);
		//低血糖数
		vo.setResults(BusinessConstants.BLOOD_SUGER_JUDGE_LOW);
		count = bloodSugerRecordDao.getRecordCount(vo);
		returnMap.put("lowCount", count);
		return returnMap;
	}

	@Override
	public List<BloodSugerRecordEntity> getRecordList(String personId) {
		return bloodSugerRecordDao.getRecordList(personId);
	}

	@Override
	public BloodSugerRecordEntity getLastRecord(String personId) {
		return bloodSugerRecordDao.getLastRecord(personId);
	}

	@Override
	public BloodSugerRecordEntity getLastRecordInDay(BloodSugerRecordVo vo) {
		return bloodSugerRecordDao.getLastRecordInDay(vo);
	}

	@Override
	@Transactional
	public Map<String, Object> addBloodSugerRecord(BloodSugerRecordEntity entity) {
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
		
		boolean abnormalFlag = false;
		// 设置记录时间
		entity.setMeasuringTime(newDate);
		// 设置检测结果
		if (entity.getBloodSuger() < 3.9) {
			//空腹血糖小于3.9，为低血糖
			entity.setDetectionResult(BusinessConstants.BLOOD_SUGER_LOW);
			abnormalFlag = true;
		}else if (entity.getBloodSuger() >= 3.9 && entity.getBloodSuger() < 6.1) {
			//空腹血糖处于[3.9,6.1)，为正常血糖
			entity.setDetectionResult(BusinessConstants.BLOOD_SUGER_NORMAL);
		}else if (entity.getBloodSuger() >= 6.1 && entity.getBloodSuger() < 7.0) {
			//空腹血糖处于[6.1,7)，为正常偏高
			entity.setDetectionResult(BusinessConstants.BLOOD_SUGER_NORMAL_HIGH);
		}else if (entity.getBloodSuger() >= 7.0 && entity.getBloodSuger() < 8.4) {
			//空腹血糖处于[7.0,8.4)，为轻度偏高
			entity.setDetectionResult(BusinessConstants.BLOOD_SUGER_HIGH_LEVEL1);
			abnormalFlag = true;
		}else if (entity.getBloodSuger() >= 8.4 && entity.getBloodSuger() <= 11.1) {
			//空腹血糖处于[8.4,11.1]，为中度偏高
			entity.setDetectionResult(BusinessConstants.BLOOD_SUGER_HIGH_LEVEL2);
			abnormalFlag = true;
		}else {
			//空腹血糖大于11.1，为重度偏高
			entity.setDetectionResult(BusinessConstants.BLOOD_SUGER_HIGH_LEVEL3);
			abnormalFlag = true;
		}
		bloodSugerRecordDao.insert(entity);
		// 判断体征是否异常，若异常，新增待评估信息
		if (abnormalFlag) {
			// 设置健康评估初始化对象
			HealthAssessmentEntity assessment = new HealthAssessmentEntity();
			assessment.setDocTeamId(signInfo.getDocTeamId());
			assessment.setPersonId(entity.getPersonId());
			assessment.setExceptionType(BusinessConstants.HEALTH_ASSESSMENT_TYPE_BLOODSUGER);	//类型设置为血糖异常
			assessment.setBloodSuger(entity.getBloodSuger());
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

}
