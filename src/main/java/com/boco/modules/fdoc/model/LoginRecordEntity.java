package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;

public class LoginRecordEntity extends DataEntity<LoginRecordEntity>{
	
	private static final long serialVersionUID = 1L;
	//主键id
	private int id;
	//登录账户类型 1医生 2专家 3居民
	private Integer loginType;
	//当前登陆的账户
	private String loginAccount;
	//厂商手机型号
	private String brandModel;
	//手机类型
	private Integer phoneType;
	//手机系统版本号
	private String version;
	//手机唯一识别码
	private String imei;
	//登录时间，即创建时间
	private Date loginTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getLoginType() {
		return loginType;
	}
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getBrandModel() {
		return brandModel;
	}
	public void setBrandModel(String brandModel) {
		this.brandModel = brandModel;
	}
	public Integer getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(Integer phoneType) {
		this.phoneType = phoneType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
