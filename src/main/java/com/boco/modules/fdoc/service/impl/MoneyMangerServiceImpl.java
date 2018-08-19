package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.dao.MoneyMangerDao;
import com.boco.modules.fdoc.model.MoneyMangerEntity;
import com.boco.modules.fdoc.service.MoneyMangerService;

@Service
public class MoneyMangerServiceImpl  implements  MoneyMangerService{
	
	@Resource
	MoneyMangerDao  mangerDao;
	
	@Override
	public Page<MoneyMangerEntity> getMoneyMangerInfoList(
			Page<MoneyMangerEntity> page, MoneyMangerEntity entity) {
		entity.setPage(page);
		List<MoneyMangerEntity> moneyMangerListInfo = mangerDao.getMoneyMangerListInfo(entity);
		page.setList(moneyMangerListInfo);
		return page;
	}
	
	@Override
	public List<MoneyMangerEntity> capitalTotalMangerByLeader(
			MoneyMangerEntity moneyMangerEntity) {
		return mangerDao.capitalTotalMangerByLeader(moneyMangerEntity);
	}

}
