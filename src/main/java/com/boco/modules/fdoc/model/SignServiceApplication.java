package com.boco.modules.fdoc.model;

import java.util.Date;

/**
 * 签约服务申请表
 * @author huiwangui
 *
 */
public class SignServiceApplication {
	private int id;//主键
	private String healthFileCode;//健康档案编号
	private Date signDate;//申请日期
	private int renewalOrUnwind;//续约/解约:0(解约),1(续约)
	private int doctorId;//医生id
	private int handleFlag;//签约处理/未处理标志:0(未处理),1(处理)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHealthFileCode() {
		return healthFileCode;
	}
	public void setHealthFileCode(String healthFileCode) {
		this.healthFileCode = healthFileCode;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public int getRenewalOrUnwind() {
		return renewalOrUnwind;
	}
	public void setRenewalOrUnwind(int renewalOrUnwind) {
		this.renewalOrUnwind = renewalOrUnwind;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getHandleFlag() {
		return handleFlag;
	}
	public void setHandleFlag(int handleFlag) {
		this.handleFlag = handleFlag;
	}
	
	
}
