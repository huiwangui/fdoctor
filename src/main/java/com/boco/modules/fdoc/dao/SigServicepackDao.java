package com.boco.modules.fdoc.dao;

import java.util.List;
import java.util.Map;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.SigServicepackEntity;
import com.boco.modules.fdoc.vo.ServicepackDetailsVo;
import com.boco.modules.fdoc.vo.SigServicepackVo;

@MyBatisDao
public interface SigServicepackDao extends CrudDao<SigServicepackEntity>{
	/**
	 * 根据服务包id获取详细服务列表
	 * @param servicepackId
	 * @return
	 */
	public List<ServicepackDetailsVo> getServicepackDetail(int servicepackId);
	/**
	 * 
	 * getServicePacksByValues:(根据权值获取列表). <br/>
	 * @author q
	 * @param values 服务包权值，以逗号隔开
	 * @return
	 */
	public List<SigServicepackEntity> getServicePacksByValues(Map<String, String> valuesMap);
	/**
	 * Tilte: getServicePacksByPackValue 
	 * Description:根据权值获取列表(服务包年限就是签约有效时间)
	 * @param valuesMap
	 * @return List<SigServicepackVo>
	 * @author h
	 */
	public List<SigServicepackVo> getServicePacksByPackValue(Map<String, String> valuesMap);
}
