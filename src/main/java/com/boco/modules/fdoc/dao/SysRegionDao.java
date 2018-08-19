package com.boco.modules.fdoc.dao;

import java.util.List;
import java.util.Map;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.SysRegionEntity;

@MyBatisDao
public interface SysRegionDao extends CrudDao<SysRegionEntity>{
	/**
	 * 
	 * getChildrenRegions:(查询子级区划列表). <br/>
	 * @author q
	 * @param parentRegionCode
	 * @return
	 */
	public List<SysRegionEntity> getChildrenRegions(String regionCode);
	
	/**
	 * 
	 * getParentRegion:(查询父级区划). <br/>
	 * @author q
	 * @param regionCode
	 * @return
	 */
	public SysRegionEntity getParentRegion(String regionCode);
	
	/**
	 * 
	 * getRegionByCode:(通过regionCode查询区划信息). <br/>
	 * @author q
	 * @param regionCode
	 * @return
	 */
	public SysRegionEntity getRegionByCode(String regionCode);
	
	/**
	 * 
	 * getRegionByCode:(通过id查询区划信息). <br/>
	 * @author q
	 * @param regionCode
	 * @return
	 */
	public SysRegionEntity getRegionById(String id);
	
	/**
	 * 
	 * getChildrenRegions:(获取准确子级区划，避开默认区划影响). <br/>
	 * @author q
	 * @param parentRegionCode
	 * @return
	 */
	public List<Map<String, Object>> getAccurateChildrenRegions(String regionCode);
}
