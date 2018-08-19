package com.boco.modules.fdoc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.dao.SysRegionDao;
import com.boco.modules.fdoc.model.SysRegionEntity;
import com.boco.modules.fdoc.service.SysRegionService;

@Service
public class SysRegionServiceImpl implements SysRegionService {

	@Resource
	SysRegionDao regionDao;

	@Override
	public List<SysRegionEntity> getChildrenRegions(String regionCode) {
		if (StringUtils.isNotEmpty(regionCode)) {
			// 如果传入的regionCode不为空，说明为查询指定区划的子级区划
			return regionDao.getChildrenRegions(regionCode);
		} else {
			// 如果传入的regionCode为空，说明为查询默认最上级的子级区划，从配置文件获取最上级子级区划代码
			regionCode = PropertiesUtils.getValue(BusinessConstants.TOP_REGION);
			return regionDao.getChildrenRegions(regionCode);
		}
	}

	@Override
	public SysRegionEntity getParentRegion(String regionCode) {
		return regionDao.getParentRegion(regionCode);
	}

	@Override
	public SysRegionEntity getRegionByCode(String regionCode) {
		return regionDao.getRegionByCode(regionCode);
	}

	@Override
	public List<SysRegionEntity> getAllParentRegion(String regionCode, List<SysRegionEntity> regionList, Integer codeLength) {
		SysRegionEntity parentRegion = regionDao.getParentRegion(regionCode);
		if (parentRegion != null && parentRegion.getRegionCode().length() >= codeLength) {
			regionList.add(parentRegion);
			getAllParentRegion(parentRegion.getRegionCode(), regionList, codeLength);
		}
		return regionList;
	}

	@Override
	public List<Map<String, Object>> getAccurateChildrenRegions(String regionCode) {
		if (StringUtils.isNotEmpty(regionCode)) {
			// 如果传入的regionCode不为空，说明为查询指定区划的子级区划
			return regionDao.getAccurateChildrenRegions(regionCode);
		} else {
			// 如果传入的regionCode为空，说明为查询默认最上级的子级区划，从配置文件获取最上级子级区划代码
			regionCode = PropertiesUtils.getValue(BusinessConstants.TOP_REGION);
			return regionDao.getAccurateChildrenRegions(regionCode);
		}
		
	}

	@Override
	public SysRegionEntity getRegionById(String id) {
		return regionDao.getRegionById(id);
	}

}
