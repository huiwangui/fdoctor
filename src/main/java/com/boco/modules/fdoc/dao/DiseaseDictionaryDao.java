package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.DiseaseDictionaryEntity;

@MyBatisDao
public interface DiseaseDictionaryDao extends CrudDao<DiseaseDictionaryEntity>{
	/**
	 * 
	 * getAllDictionary:(查询左右疾病字典项). <br/>
	 * @author q
	 * @return
	 */
	public List<DiseaseDictionaryEntity> getAllDictionary();
	/**
	 * 
	 * getDictionaryById:(通过ID查询疾病字典项). <br/>
	 * @author q
	 * @return
	 */
	public DiseaseDictionaryEntity getDictionaryById(String diseaseId);
}
