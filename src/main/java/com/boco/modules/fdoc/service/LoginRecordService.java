package com.boco.modules.fdoc.service;

import com.boco.modules.fdoc.model.LoginRecordEntity;

public interface LoginRecordService {

	/**
	 * 记录登录信息
	 * introduction：insertLoginRecord
	 * @param lrEntity
	 * @return
	 */
	public Integer insertLoginRecord(LoginRecordEntity lrEntity);

}
