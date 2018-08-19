package com.boco.modules.fdoc.service;

import com.boco.modules.fdoc.model.NationEntity;

public interface NationService {
	/**
	 * 
	 * getNationByCode:(据民族代码获取民族). <br/>
	 * @author q
	 * @param nationCode
	 * @return
	 */
	public NationEntity getNationByCode(String nationCode);
}
