package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;

public class HealthCheckSourceEntity extends DataEntity<HealthCheckSourceEntity>{

	private static final long serialVersionUID = 1L;
	private int id;
	private String checkType;	//1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检
	private String payType;			//体检类型 1.自费 2.免费
	private Date checkDate;		//体检日期
	private Integer docId;		//放号医生ID
	private Integer sourceNum;	//放号数量
	private Date createTime;	//创建时间
	private Integer version;	//版本号，用于控制并发
	private String status;		//状态   1.已发布  2.已过期
	private String delFlag;		//删除状态  0.未删除  1.已删除
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Integer getSourceNum() {
		return sourceNum;
	}
	public void setSourceNum(Integer sourceNum) {
		this.sourceNum = sourceNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	
}
