 package com.boco.modules.fdoc.dao.surrender;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.surrender.SurrenderInformationEntity;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;

/**
 * Tilte: SurrenderInformationDao 
 * Description:
 * @author h
 * @date  2017年11月28日下午5:34:43
 * @version 1.0
 *  
 */
@MyBatisDao
public interface SurrenderInformationDao extends CrudDao<SurrenderInformationEntity>{
	
	public int getYearOfSignByPersonId(SurrenderRequestEntity entity);
}
