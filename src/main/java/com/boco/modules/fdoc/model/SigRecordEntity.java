package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;

public class SigRecordEntity extends DataEntity<SigRecordEntity>{

	private static final long serialVersionUID = 3070119135555752053L;
	
	private int id;//服务记录id
	private int resId;//记录id
	private int sigId;//服务包id
	private String  detailsId;//服务详情id
	private String serviceData;//服务时间
	private String serviceDetails;//服务内容
	private int docId;//医生id
	private String docName;//医生姓名
	private String serviceAddress;//服务地址
	private String istimely;//是否及时
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getSigId() {
		return sigId;
	}
	public void setSigId(int sigId) {
		this.sigId = sigId;
	}
	public String getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}
	public String getServiceData() {
		return serviceData;
	}
	public void setServiceData(String serviceData) {
		this.serviceData = serviceData;
	}
	public String getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIstimely() {
		return istimely;
	}
	public void setIstimely(String istimely) {
		this.istimely = istimely;
	}
}
