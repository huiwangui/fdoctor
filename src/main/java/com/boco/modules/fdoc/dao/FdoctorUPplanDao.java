package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.vo.UpPlanVo;

@MyBatisDao
public interface FdoctorUPplanDao {
	
	/**
	 * 医生修改随访计划
	 * @author Jomo
	 * 
	 */
	public int updatePlan(UpPlanVo upPlanVo);
	/**
	 * 医生添加随访计划
	 * @author Jomo
	 */
	public int savePlan(UpPlanVo upPlanVo);
		
		
	public List<UpPlanVo> getListWithNonexecution(UpPlanVo vo);
	
	public UpPlanVo getMaxDateUpdateNonexecution(UpPlanVo vo);
	/**未完成计划是否被查看**/
	public List<UpPlanVo> getListWithNonexecutionIsLook(UpPlanVo vo);
	
	/**处理未完成随访计划**/
	public  int  lookoverPlan(int id);
	
	/**是否处理的总数**/
	public Integer getCountListWithNonexecutionIsLook(UpPlanVo vo);
	
}
