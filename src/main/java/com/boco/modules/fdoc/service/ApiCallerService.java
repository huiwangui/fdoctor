package com.boco.modules.fdoc.service;

import com.boco.modules.fdoc.model.ApiCallerEntity;

public interface ApiCallerService {
	/**
	 * 获取对外接口调用方
	 * @param entity
	 * @return
	 */
	public ApiCallerEntity getApiCaller(ApiCallerEntity entity);
}
