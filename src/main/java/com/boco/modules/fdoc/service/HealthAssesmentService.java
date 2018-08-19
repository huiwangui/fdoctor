package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.HealthAssessmentEntity;
import com.boco.modules.fdoc.vo.HealthAssessmentVo;

public interface HealthAssesmentService {
	/**
	 * 
	 * addHealthAssesment:(新增健康评估). <br/>
	 * @author q
	 * @return
	 */
	public Integer addHealthAssesment(HealthAssessmentEntity entity);
	/**
	 * 
	 * updateHealthAssesment:(修改血压记录). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Integer updateHealthAssesment(HealthAssessmentEntity entity);
	/**
	 * 
	 * getHealthAssessmentList:(获取健康评估列表). <br/>
	 * @author q
	 * @param 医生团队ID、人员ID（可无）、状态
	 * @return
	 */
	public List<HealthAssessmentVo> getHealthAssessmentList(HealthAssessmentVo vo);
	/**
	 * 
	 * getHealthAssessmentCount:(获取健康评估记录数). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Integer getHealthAssessmentCount(HealthAssessmentVo vo);
	/**
	 * 
	 * getHealthAssessmentListByUser:(这里用一句话描述这个方法的作用). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<HealthAssessmentVo> getHealthAssessmentListByUser(HealthAssessmentVo vo);
	/**
	 * 
	 * getHealthAssessmentDetail:(获取评估详情). <br/>
	 * @author q
	 * @param id
	 * @return
	 */
	public HealthAssessmentVo getHealthAssessmentDetail(Integer id);
}
