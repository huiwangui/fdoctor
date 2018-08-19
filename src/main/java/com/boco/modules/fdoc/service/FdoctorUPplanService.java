package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.vo.UpPlanSignVo;
import com.boco.modules.fdoc.vo.UpPlanVo;


public interface FdoctorUPplanService {
	/**
	 * 医生修改随访计划服务层
	 * @author Jomo
	 * 
	 */
	public String  updatePlan(UpPlanVo upPlanVo);

	public String savePlan(UpPlanVo upPlanVo);

	public List<UpPlanVo> getListWithNonexecution(UpPlanVo vo);
	
	public UpPlanVo getMaxDateUpdateNonexecution(UpPlanVo vo);
	
	
	
	/**未完成计划是否被查看**/
	public List<UpPlanVo> getListWithNonexecutionIsLook(UpPlanVo vo);
	
	/**处理未完成随访计划**/
	public  int  lookoverPlan(int id);
	
	public Integer getCountListWithNonexecutionIsLook(UpPlanVo vo);
}
