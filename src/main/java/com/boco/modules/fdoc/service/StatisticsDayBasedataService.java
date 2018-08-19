package com.boco.modules.fdoc.service;

import com.boco.modules.fdoc.model.statistics.StatisticsDayBasedataEntity;
import com.boco.modules.fdoc.model.statistics.StatisticsDayTwoBasedataEntity;
import com.boco.modules.fdoc.vo.statistics.StatisticsDayBasedataVo;
import com.boco.modules.fdoc.vo.statistics.StatisticsDayTwoBasedataVo;

/**
 * 
 * ClassName: 基础数据分天统计service，包括总计、按照team、区划分组等 <br/>
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public interface StatisticsDayBasedataService {
	/**
	 * 
	 * getBasedata:(总数综合统计. <br/>
	 * @author q
	 * @return
	 */
	public StatisticsDayBasedataEntity getBasedata();
	
	/**
	 * 获取增量数据
	 */
	public StatisticsDayBasedataVo getIncrementData();
	
	/**
	 *  获取城镇增量数据
	 */
	public StatisticsDayBasedataVo getTownIncrementData(String regionCode);
	/**
	 *  获取机构增量数据
	 */
	public StatisticsDayBasedataVo getTownIncrementData1(String orgId);

	/**
	 * 
	 * getTeamIncrementData:(获取团队增量数据). <br/>
	 * @author q
	 * @param teamId
	 * @return
	 */
	public StatisticsDayBasedataVo getTeamIncrementData(String teamId);
	
	/**
	 * 
	 * callDayBasedataProced
	 * @author q
	 */
	public void callDayBasedataProcedure();
	
	/**
	 * 
	 * callDayBasedataTeamProcedure:(调用医生团队数据统计存储过程). <br/>
	 * @author q
	 */
	public void callDayBasedataTeamProcedure();

	public void callDayTwoBasedataProcedure();


	public void callDayTwoTeamBasedataTeamProcedure();

	/**
	 * 
	 * callDayBasedataTeamProcedure:(调用城镇数据统计存储过程). <br/>
	 * @author q
	 */
	public void callDayBasedataTownProcedure();

	//add by pdk
	/**
	* 第二屏统计
	* */
	StatisticsDayTwoBasedataVo getStatisticDayTwoBasedata(String teamId, String orgId, String type);

	//add by pdk

	//获取接口数据
	StatisticsDayTwoBasedataEntity getAllPhysicalExaminationFollowUP();

	public void getTeamData();


}
