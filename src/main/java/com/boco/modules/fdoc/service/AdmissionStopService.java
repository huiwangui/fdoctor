package com.boco.modules.fdoc.service;

import com.boco.common.json.BaseJsonVo;

/**
 * 
 * @author sunche
 * @Time 2016年8月3日下午3:30:06
 */

public interface AdmissionStopService {
	/**
	 * 获取停诊目录
	 * @return
	 */
	public BaseJsonVo getStopList();
	/**
	 * 获取停诊详情
	 * @param id
	 * @return
	 */
	public BaseJsonVo getStopDetail(int id);
}
