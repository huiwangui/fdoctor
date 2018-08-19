package com.boco.modules.fdoc.dao;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.PersonDeseaseInfoEntity;
import com.boco.modules.fdoc.vo.PersonDeseaseInfoVo;

/**
 * 
 * ClassName: 居民慢病（统计用）标识DAO <br/>
 * @author q
 * @version 
 * @since JDK 1.7+
 */
@MyBatisDao
public interface PersonDeseaseInfoDao extends CrudDao<PersonDeseaseInfoEntity>{

	/**
	 * Tilte: updateFlag 
	 * Description:修改标签
	 * @param vo void
	 * @author h
	 */
	int updateFlag(PersonDeseaseInfoVo vo);
}
