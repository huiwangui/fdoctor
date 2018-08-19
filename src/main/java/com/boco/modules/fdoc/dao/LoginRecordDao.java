package com.boco.modules.fdoc.dao;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.model.LoginRecordEntity;

@MyBatisDao
public interface LoginRecordDao {

	/**
	 * 记录登录数据
	 * introduction：insertLoginRecord
	 * @param lrEntity
	 * @return
	 */
	public Integer insertLoginRecord(LoginRecordEntity lrEntity);

}
