package com.boco.modules.fdoc.service.impl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.dao.FdoctorUPplanDao;
import com.boco.modules.fdoc.service.FdoctorUPplanService;
import com.boco.modules.fdoc.vo.UpPlanVo;



/**
 * 医生随访计划服务实现层
 * @author Jomo
 * 
 */
@Service
public class FdoctorUPplanServiceImpl implements FdoctorUPplanService{
	/**
	 * 医生修改随访计划
	 * @author Jomo
	 * 
	 */
	@Resource 
	FdoctorUPplanDao fdoctorUPplanDao;

	@Override
	public String updatePlan(UpPlanVo upPlanVo) {

        int a=fdoctorUPplanDao.updatePlan(upPlanVo);
	
	  if(a>0){
		  return JsonUtils.getJson(BaseJsonVo.success(a));
	  }
	
	  return  JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
	}
	
	
	/**
	 * 医生添加随访计划
	 * @author Jomo
	 * 
	 */
	@Override
	public String savePlan(UpPlanVo upPlanVo) {
		 int a=fdoctorUPplanDao.savePlan(upPlanVo);
			
		  if(a>0){
			  return JsonUtils.getJson(BaseJsonVo.success(a));
		  }
		
		  return  JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}

	/**
	 * 获取未完成的随访计划列表
	 */
	@Override
	public List<UpPlanVo> getListWithNonexecution(UpPlanVo vo) {
		
		return fdoctorUPplanDao.getListWithNonexecution(vo);
	}
	
	
	public UpPlanVo getMaxDateUpdateNonexecution(UpPlanVo vo) {
		return fdoctorUPplanDao.getMaxDateUpdateNonexecution(vo);
	}
	
	
	
	
	/**未完成计划是否被查看**/
	public List<UpPlanVo> getListWithNonexecutionIsLook(UpPlanVo vo) {
		return fdoctorUPplanDao.getListWithNonexecutionIsLook(vo);
	}
	
	/**处理未完成随访计划**/
	public  int  lookoverPlan(int id) {
		return fdoctorUPplanDao.lookoverPlan(id);
	}


	@Override
	public Integer getCountListWithNonexecutionIsLook(UpPlanVo vo) {
		return fdoctorUPplanDao.getCountListWithNonexecutionIsLook(vo);
	
	}

}


