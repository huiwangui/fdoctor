package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.vo.SignVo;
import com.boco.modules.fdoc.vo.UpPlanVo;

@MyBatisDao
public interface FamilydoctorUpDao {
	/**
	 * 根据居民健康档案编号查询随访记录
	 * @param vo
	 * @return
	 */
	UpPlanVo getUpplanByHealthFileCode(UpPlanVo vo);
	/**
     * 获取已签约列表中未执行的随访计划或已完成随访计划列表总数
     */
    int getSignedListCountByFinishFlag(UpPlanVo vo);
    /**
     * 获取某人已完成的随访计划列表或未完成的随访计划列表总数 
     * @param vo
     * @return
     */
    int getFinishSignedListCountByHealthFileCode(UpPlanVo vo);
    
	/**
	 * 通过身份证号模糊查询该医生下的签约居民
	 * @param vo
	 * @return
	 */
	public List<UpPlanVo> getSignedListByIdCard(UpPlanVo vo);
	/**
	 * 查找未制定随访计划的签约居民或已完成随访计划的签约居民
	 * @param vo
	 * @return
	 */
	public List<UpPlanVo> getSignedListByFinishFlag(UpPlanVo vo);
	
	/**
	 * 获取已签约列表中某人已完成的随访计划列表
	 * @param vo
	 * @return
	 */
	public List<UpPlanVo> getFinishSignedListByHealthFileCode(UpPlanVo vo);
	/**
	 * 在已签约列表中删除某人制定的未执行随访计划 
	 * @param vo
	 * @return
	 */
	public int deleteUnFinishUpPlanByHealthFileCodeAndDiseaseId(UpPlanVo vo);
	/**
	 * 删除某人制定的未执行随访计划 (根据随访计划id)
	 * @param vo
	 * @return
	 */
	public int deleteUnFinishUpPlanById(UpPlanVo vo);
	/**
	 * 获取某人指定的未完成的随访计划 (根据疾病名称)
	 * @param vo
	 * @return
	 */
	public List<UpPlanVo> getunFinishSignedListBydiseaseId(UpPlanVo vo);
	/**
	 * 获取某人指定的未完成的随访计划 (根据随访计划id)
	 * @param vo
	 * @return
	 */
	public  UpPlanVo getunFinishSignedListById(UpPlanVo vo);
	
	/**
	 * 根据随访计划id查找随访计划 
	 * @param vo
	 * @return
	 */
	public UpPlanVo getUpPlan(UpPlanVo vo);
	/**
	 * 根据随访计划id更改随访记录
	 * @param vo
	 * @return
	 */
	public Integer updateUpPlan(UpPlanVo vo);
}
