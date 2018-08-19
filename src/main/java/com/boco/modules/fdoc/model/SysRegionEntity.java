package com.boco.modules.fdoc.model;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.persistence.Page;
import com.boco.common.utils.StringUtils;



public class SysRegionEntity{
    private String id;

    /**
    * 区划代码
    */
    private String regionCode;

    /**
     * 区划名称
     */
    private String name;

    /**
    * 区划完整名称
    */
    private String fullName;

    /**
    * 上级区划代码
    */
    private String parentCode;
    
    private Page<SysRegionEntity> page;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegionCode() {
		return StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}
	
	public void setRegionCode(String regionCode) {
	    this.regionCode = StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

	public Page<SysRegionEntity> getPage() {
		return page;
	}

	public void setPage(Page<SysRegionEntity> page) {
		this.page = page;
	}
    
    
}