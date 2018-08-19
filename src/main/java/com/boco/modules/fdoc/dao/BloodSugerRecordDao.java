package com.boco.modules.fdoc.dao;

import java.util.List;
import java.util.Map;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.BloodSugerRecordEntity;
import com.boco.modules.fdoc.vo.BloodSugerRecordVo;

@MyBatisDao
public interface BloodSugerRecordDao extends CrudDao<BloodSugerRecordEntity>{
	/**
	 * 
	 * getRecordCount:(获取总数). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Integer getRecordCount(BloodSugerRecordVo vo);
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
}
