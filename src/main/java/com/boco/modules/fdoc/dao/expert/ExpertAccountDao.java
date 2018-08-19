package com.boco.modules.fdoc.dao.expert;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.expert.ExpertAccountEntity;
@MyBatisDao
public interface ExpertAccountDao extends CrudDao<ExpertAccountEntity> {

	/**
	 * Tilte: verifyAccount 
	 * Description:
	 * @param account
	 * @return ExpertAccountEntity
	 * @author h
	 */
	ExpertAccountEntity verifyAccount(ExpertAccountEntity account);
	 
	 
	int update(ExpertAccountEntity account);

}
