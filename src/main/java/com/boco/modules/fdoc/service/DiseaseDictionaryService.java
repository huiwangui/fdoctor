package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.DiseaseDictionaryEntity;

public interface DiseaseDictionaryService {
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
