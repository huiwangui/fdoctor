package com.boco.modules.fdoc.model;

import java.io.Serializable;
import java.util.Date;

public class UserDocSignEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    /**
    * 团队ID
    */
    private String docTeamId;

    /**
    * 医生所属机构名称
    */
    private String docOrgName;

    /**
    * 医生所属机构地址
    */
    private String docOrgAddress;

    /**
    * 居民ID
    */
    private String personId;

    /**
    * 签约图片
    */
    private String signImg;

    /**
    * 签约类型：1.医生端主动签约 2.居民端提出申请完成签约
    */
    private String signType;

    /**
    * 签约状态：1.签约有效期内 2.待续约 3.已解约
    */
    private String status;

    /**
    * 签约时间
    */
    private Date signTime;

    /**
    * 备注
    */
    private String remark;
    /**
     * 服务包权值
     */
    private Integer servicePackValue;
    /**
     * 签约结束时间
     */
    private Date dueTime;
    
    

	public Integer getServicePackValue() {
		return servicePackValue;
	}

	public void setServicePackValue(Integer servicePackValue) {
		this.servicePackValue = servicePackValue;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocTeamId() {
		return docTeamId;
	}

	public void setDocTeamId(String docTeamId) {
		this.docTeamId = docTeamId;
	}

	public String getDocOrgName() {
		return docOrgName;
	}

	public void setDocOrgName(String docOrgName) {
		this.docOrgName = docOrgName;
	}

	public String getDocOrgAddress() {
		return docOrgAddress;
	}

	public void setDocOrgAddress(String docOrgAddress) {
		this.docOrgAddress = docOrgAddress;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getSignImg() {
		return signImg;
	}

	public void setSignImg(String signImg) {
		this.signImg = signImg;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    
    
}