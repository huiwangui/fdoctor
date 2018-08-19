package com.boco.modules.fdoc.dao.surrender;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.surrender.SurrenderReasonEntity;
@MyBatisDao
public interface SurrenderReasonDao  extends CrudDao<SurrenderReasonEntity>{
	
	/**
	 * 获取所有解约原因
	 * @return
	 */
	public List<SurrenderReasonEntity> findAllReasonList();
}
