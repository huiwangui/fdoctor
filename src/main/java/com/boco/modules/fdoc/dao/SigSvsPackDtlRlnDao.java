package com.boco.modules.fdoc.dao;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.SigSvsPackDtlRlnEntity;

@MyBatisDao
public interface SigSvsPackDtlRlnDao extends CrudDao<SigSvsPackDtlRlnEntity>{
	
	/**
	 * 中间表记录
	 * insertSvsPackInfo:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author ken
	 * @param sigSvsPackEntity
	 * @return
	 */
	public int insertSvsPackRlnInfo(SigSvsPackDtlRlnEntity entity); 

}
