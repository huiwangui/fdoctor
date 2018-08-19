package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.model.MoneyMangerEntity;

public interface MoneyMangerService {
	
	/**
	 * getMoneyMangerInfoList:获取资金管理信心
	 * @param page  分页对象
	 * @param entity 分页实体对象
	 * @return
	 */
	public Page<MoneyMangerEntity> getMoneyMangerInfoList(
			Page<MoneyMangerEntity> page, MoneyMangerEntity entity);
	
	
  public abstract  List<MoneyMangerEntity>  capitalTotalMangerByLeader(MoneyMangerEntity moneyMangerEntity);
}
