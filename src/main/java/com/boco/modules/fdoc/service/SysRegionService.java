package com.boco.modules.fdoc.service;

import java.util.List;
import java.util.Map;

import com.boco.modules.fdoc.model.SysRegionEntity;

public interface SysRegionService {
	/**
	 * 
	 * getChildrenRegions:(查询子级区划列表,若传入的regionCode为空串或Null,则查询默认最高区划下面的子级区划). <br/>
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
	 * getAllParentRegion:(查看所有父级区划，直到codeLength参数表示的级数，如9则为乡级). <br/>
	 * @author q
	 * @return
	 */
	public List<SysRegionEntity> getAllParentRegion(String regionCode, List<SysRegionEntity> regionList, Integer codeLength);
	
	/**
	 * 
	 * getChildrenRegions:(获取准确子级区划，避开默认区划影响). <br/>
	 * @author q
	 * @param parentRegionCode
	 * @return
	 */
	public List<Map<String, Object>> getAccurateChildrenRegions(String regionCode);
	
	/**
	 * 
	 * getRegionByCode:(通过id查询区划信息). <br/>
	 * @author q
	 * @param regionCode
	 * @return
	 */
	public SysRegionEntity getRegionById(String id);
}
