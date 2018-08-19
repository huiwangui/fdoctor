package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.HealthAssessmentEntity;
import com.boco.modules.fdoc.vo.HealthAssessmentVo;

@MyBatisDao
public interface HealthAssessmentDao extends CrudDao<HealthAssessmentEntity>{
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
	 * getHealthAssessmentListByUser:(居民端获取健康评估列表). <br/>
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
