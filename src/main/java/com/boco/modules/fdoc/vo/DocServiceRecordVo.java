package com.boco.modules.fdoc.vo;

import java.util.Date;

import com.boco.common.persistence.Page;

public class DocServiceRecordVo {
	
	/*
	 * 服务时间
	 */
	private Date serviceTime;
	/*
	 * 服务名
	 */
	private String serviceName;
	/*
	 * 服务详细
	 */
	private String serviceDetails;
	/*
	 * 服务时间字符串
	 */
	private String serviceTimeStr;
	/*
	 * 医生id
	 */
	private String doctorId;
	/*
	 * 居民id
	 */
	private String personId;
	/*
	 * 服务包Id
	 */
	private Integer packId;
	/*
	 * 分页参数
	 */
	private Page<DocServiceRecordVo> page;
	
	public Date getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	public String getServiceTimeStr() {
		return serviceTimeStr;
	}
	public void setServiceTimeStr(String serviceTimeStr) {
		this.serviceTimeStr = serviceTimeStr;
	}
	public Page<DocServiceRecordVo> getPage() {
		return page;
	}
	public void setPage(Page<DocServiceRecordVo> page) {
		this.page = page;
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
	
	

}
