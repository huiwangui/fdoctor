package com.boco.modules.fdoc.vo;

import java.util.Date;

public class MinReportVo {
	/**
	 * 身份证号
	 */
	private String idCode;
	/**
	 * 查询开始时间
	 */
	private Date startTime;
	/**
	 * 查询结束时间
	 */
	private Date endTime;
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

}
