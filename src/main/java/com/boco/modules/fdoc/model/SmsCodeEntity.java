package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;

/**
 * t_sms_code 实体类 Wed Jul 27 15:00:06 CST 2016 tony.su
 */

public class SmsCodeEntity extends DataEntity<SmsCodeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int busiId;// 业务编号
	private int busiTypeId;// 业务类型编号(枚举 )
	private String mobile;// 手机号
	private String message;// 短信内容
	private Date sendTime;// 发短信时间
	private Date validDate;// 有效期

	/*
	 * public void setId(int id) { this.id = id; }
	 * 
	 * public int getId() { return id; }
	 */

	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}

	public int getBusiId() {
		return busiId;
	}

	public void setBusiTypeId(int busiTypeId) {
		this.busiTypeId = busiTypeId;
	}

	public int getBusiTypeId() {
		return busiTypeId;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getValidDate() {
		return validDate;
	}
}
