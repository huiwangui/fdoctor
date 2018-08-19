package com.boco.modules.fdoc.dao.statistics;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.statistics.StatisticsDayTeamBasedataEntity;

@MyBatisDao
public interface StatisticsDayTeamBasedataDao extends CrudDao<StatisticsDayTeamBasedataEntity>{
	/**
	 * 
	 * getLastInfo:(获取最近一条统计信息). <br/>
	 * @author q
	 * @return
	 */
	public StatisticsDayTeamBasedataEntity getLastInfo(String regionCode);
	/**
	 * 
	 * getLastInfoByDate:(通过统计日期获取统计信息). <br/>
	 * @author q
	 * @return
	 */
	public StatisticsDayTeamBasedataEntity getLastInfoByDate(StatisticsDayTeamBasedataEntity entity);
	/**
	 * 
	 * callStatisticsProcedure:(手动调用存储过程). <br/>
	 * @author q
	 */
	public void callStatisticsProcedure();
}
