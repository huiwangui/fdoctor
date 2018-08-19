package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;
/**
 * 医生放号实体类
 * @author q
 *
 */
public class VaccineSourceEntity extends DataEntity<VaccineSourceEntity>{
	private static final long serialVersionUID = 1L;
	
	private int id;  
	private Integer vaccineId;  //疫苗ID
	private Date inoDate;  //接种日期
	private Integer docId;  //医生ID
	private Integer vaccineNum; // 疫苗数量
	private Date createTime;
	private Integer version;//版本号
	private String status; //状态   1.已发布  2.已过期
	private String delFlag;// 删除状态  0.未删除  1.已删除
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getVaccineId() {
		return vaccineId;
	}
	public void setVaccineId(Integer vaccineId) {
		this.vaccineId = vaccineId;
	}
	public Date getInoDate() {
		return inoDate;
	}
	public void setInoDate(Date inoDate) {
		this.inoDate = inoDate;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Integer getVaccineNum() {
		return vaccineNum;
	}
	public void setVaccineNum(Integer vaccineNum) {
		this.vaccineNum = vaccineNum;
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
	
	
	
}
