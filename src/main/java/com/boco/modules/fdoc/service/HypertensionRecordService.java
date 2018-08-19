package com.boco.modules.fdoc.service;

import java.util.List;
import java.util.Map;

import com.boco.modules.fdoc.model.HypertensionRecordEntity;
import com.boco.modules.fdoc.vo.HypertensionRecordVo;

public interface HypertensionRecordService {
	/**
	 * 
	 * getRecordCount:(获取血压记录总数). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Map<String, Integer> getRecordCount(String personId);
	/**
	 * 
	 * getRecordList:(这里用一句话描述这个方法的作用). <br/>
	 * @author q
	 * @param personId
	 * @return
	 */
	public List<HypertensionRecordEntity> getRecordList(String personId);
	/**
	 * 
	 * getLastRecord:(获取最近一条血压记录). <br/>
	 * @author q
	 * @param personId
	 * @return
	 */
	public HypertensionRecordEntity getLastRecord(String personId);
	/**
	 * 
	 * getLastRecordInDay:(查询某天的最后一条血压记录). <br/>
	 * @author q
	 * @return
	 */
	public HypertensionRecordVo getLastRecordInDay(HypertensionRecordVo vo);
	/**
	 * 
	 * addHypertensionRecord:(新增血压记录). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Map<String, Object> addHypertensionRecord(HypertensionRecordEntity entity);
	/**
	 * 
	 * getLastAbnormalSignRecord:(获取最近一条体征记录). <br/>
	 * @author q
	 * @return
	 */
	public Map<String, Object> getLastAbnormalSignRecord(String personId);
}
