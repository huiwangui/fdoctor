package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.model.SysRegionEntity;

@MyBatisDao
public interface TestDao {
	public List<SysRegionEntity> getRegionList(SysRegionEntity entity);
	public Integer update(SysRegionEntity entity);
}
