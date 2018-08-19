package com.boco.modules.fdoc.service;

import java.util.List;
import java.util.Map;

import com.boco.modules.fdoc.model.BloodSugerRecordEntity;
import com.boco.modules.fdoc.vo.BloodSugerRecordVo;

public interface BloodSugerRecordService {
	/**
	 * 
	 * getRecordCount:(获取总数). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Map<String, Integer> getRecordCount(String personId);
	/**
	 * 
	 * getRecordList:(获取血糖记录列表). <br/>
	 * @author q
	 * @param personId
	 * @return
	 */
	public List<BloodSugerRecordEntity> getRecordList(String personId);
	/**
	 * 
	 * getLastRecord:(获取最近一条血压记录). <br/>
	 * @author q
	 * @param personId
	 * @return
	 */
	public BloodSugerRecordEntity getLastRecord(String personId);
	/**
	 * 
	 * getLastRecordInDay:(获取某一天最后的一条记录). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public BloodSugerRecordEntity getLastRecordInDay(BloodSugerRecordVo vo);
	/**
	 * 
	 * addBloodSugerRecord:(新增血压记录). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Map<String, Object> addBloodSugerRecord(BloodSugerRecordEntity entity);
}
