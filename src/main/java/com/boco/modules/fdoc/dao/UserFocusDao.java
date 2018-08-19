package com.boco.modules.fdoc.dao;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.UserFocusEntity;

@MyBatisDao
public interface UserFocusDao extends CrudDao<UserFocusEntity>{
	public int changeStatus(UserFocusEntity userFocusEntity);
	public int getCount(UserFocusEntity userFocusEntity);
}
