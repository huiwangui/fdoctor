package com.boco.modules.fdoc.dao.statistics;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.statistics.StatisticsDayTwoBasedataEntity;

import java.util.Date;

@MyBatisDao
public interface StatisticsDayTwoBasedataDao extends CrudDao<StatisticsDayTwoBasedataEntity>{
	/**
	 * 
	 * getLastInfo:(获取最近一条统计信息).
	 * @author q
	 * @return
	 */
	public StatisticsDayTwoBasedataEntity getLastInfo();

	public void updates(StatisticsDayTwoBasedataEntity statisticsDayTwoBasedataEntity);

	public StatisticsDayTwoBasedataEntity getOneByTeamId(String teamId);

	public StatisticsDayTwoBasedataEntity getRegionEstimatelByTeamId(String teamId);



}
