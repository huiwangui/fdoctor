package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.model.MoneyMangerEntity;

@MyBatisDao
public interface MoneyMangerDao {
	
	/**
	 * getMoneyMangerListInfo: 根据条件获取资金管理page数据
	 * @param moneyMangerEntity
	 * @return
	 */
	public abstract  List<MoneyMangerEntity>  getMoneyMangerListInfo(MoneyMangerEntity moneyMangerEntity);
	
	
	public abstract  List<MoneyMangerEntity>  capitalTotalMangerByLeader(MoneyMangerEntity moneyMangerEntity);
	

}
