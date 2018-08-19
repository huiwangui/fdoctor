package com.boco.modules.fdoc.model;

import java.util.Date;

/**
 * 医生服务记录表
 * @author lzz
 */
public class DocServiceRecordEntity {

	/*
	 * 主键id
	 */
	private Integer id;
	/*
	 * 医生id
	 */
	private String doctorId;
	/*
	 * 居民id
	 */
	private String personId;
	/*
	 * 服务包id
	 */
	private Integer packId;
	/*
	 * 服务项目id
	 */
	private Integer detailsId;
	/*
	 * 服务时审核用电话号码
	 */
	private String mobile;
	/*
	 * 服务地址
	 */
	private String serviceAddress;
	/*
	 * 医生备注
	 */
	private String remark;
	/*
	 * 服务时间
	 */
	private Date serviceTime;
	/*
	 * 创建时间
	 */
	private Date createTime;
	/*
	 * 修改时间
	 */
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Integer getPackId() {
		return packId;
	}
	public void setPackId(Integer packId) {
		this.packId = packId;
	}
	public Integer getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
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
