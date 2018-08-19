package com.boco.modules.fdoc.dao;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.NationEntity;

@MyBatisDao
public interface NationDao extends CrudDao<NationEntity>{
	/**
	 * 
	 * getNationByCode:(据民族代码获取民族). <br/>
	 * @author q
	 * @param nationCode
	 * @return
	 */
	public NationEntity getNationByCode(String nationCode);
}
