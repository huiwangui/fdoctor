package com.boco.modules.fdoc.vo;

import java.util.Date;
import java.util.List;

import com.boco.common.persistence.Page;

public class OrderParamVo {
	private String uid;
	/**
	 * 医生登录
	 */
	private String userName;
	/**
	 * 订单编号
	 */
	private String orderNumber;
	/**
	 * 坐诊日期
	 */
	private Date clinicDate;
	/**
    * 0.上午  1.下午   
    */
    private String clinicTime;
    private String patientIds;
    /**
     * 体检项目
     */
    private String tjName;
    /**
     * 随访项目
     */
    private String fpName;
    /**
	 * 分页对象
	 */
	private Page<OrderParamVo> page;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getClinicDate() {
		return clinicDate;
	}
	public void setClinicDate(Date clinicDate) {
		this.clinicDate = clinicDate;
	}
	public String getClinicTime() {
		return clinicTime;
	}
	public void setClinicTime(String clinicTime) {
		this.clinicTime = clinicTime;
	}
	public String getPatientIds() {
		return patientIds;
	}
	public void setPatientIds(String patientIds) {
		this.patientIds = patientIds;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Page<OrderParamVo> getPage() {
		return page;
	}
	public void setPage(Page<OrderParamVo> page) {
		this.page = page;
	}
	public String getTjName() {
		return tjName;
	}
	public void setTjName(String tjName) {
		this.tjName = tjName;
	}
	public String getFpName() {
		return fpName;
	}
	public void setFpName(String fpName) {
		this.fpName = fpName;
	}
	 
}
