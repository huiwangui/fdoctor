package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;
/**
 * 双向转诊转入、转出机构实体类，只和双向转诊业务有关，和其他业务中的医院信息无关
 * @author q
 *
 */
public class ReferralOrgEntity extends DataEntity<ReferralOrgEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
    private Integer id;

    /**
    * 组织机构名称
    */
    private String orgName;

    /**
    * 组织机构代码
    */
    private String orgCode;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 最近修改时间
    */
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}