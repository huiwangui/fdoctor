package com.boco.modules.fdoc.vo;

import java.util.Date;
import java.util.List;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.model.SigServicepackEntity;

public class SignRequestVo {
	/**
	 * 签约申请主键ID
	 */
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
	 * 签约居民ID
	 */
	private String personId;

	/**
	 * 签约图片
	 */
	private String signImg;

	/**
	 * 申请状态：1.待确认 2.已确认 3.已拒绝
	 */
	private String status;

	/**
	 * 申请发起时间
	 */
	private Date createTime;
	
	/**
	 * 签约申请户主姓名
	 */
	private String personName;
	
	
	/**
	 * 签约申请家庭所属单位
	 */
	private String personUnit;
	
	/**
	 * 分页对象
	 */
	private Page<SignRequestVo> page;
	
	/**
	 * 医生团队中的医生姓名
	 */
	private String docName;
	
	/**
	 * 医生团队所属机构名
	 */
	private String orgName;
	
	/**
	 * 申请人QQ
	 */
	private String qq;
	
	/**
	 * 申请人微信号
	 */
	private String wechat;
	
	/**
	 * 申请人电话号码
	 */
	private String phoneNumber;
	
	 /**
     * 服务包权值
     */
    private Integer servicePackValue;
    /**
     * 签约结束时间
     */
    private Date dueTime;
    /**
     * 服务包
     */
    private List<SigServicepackEntity> packs;
    

	public List<SigServicepackEntity> getPacks() {
		return packs;
	}

	public void setPacks(List<SigServicepackEntity> packs) {
		this.packs = packs;
	}

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonUnit() {
		return personUnit;
	}

	public void setPersonUnit(String personUnit) {
		this.personUnit = personUnit;
	}

	public Page<SignRequestVo> getPage() {
		return page;
	}

	public void setPage(Page<SignRequestVo> page) {
		this.page = page;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	
}
