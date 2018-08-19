package com.boco.modules.fdoc.dao;

import java.util.List;
import java.util.Map;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.HypertensionRecordEntity;
import com.boco.modules.fdoc.vo.HypertensionRecordVo;

@MyBatisDao
public interface HypertensionRecordDao extends CrudDao<HypertensionRecordEntity>{
	/**
	 * 
	 * getRecordCount:(获取血压记录总数). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Integer getRecordCount(HypertensionRecordVo vo);
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
	 * getLastAbnormalSignRecord:(获取最近一条体征记录). <br/>
	 * @author q
	 * @return
	 */
	public Map<String, Object> getLastAbnormalSignRecord(String personId);
}
