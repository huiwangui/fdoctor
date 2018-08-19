package com.boco.modules.fdoc.vo;
/**
 * 推送消息类
 * @author Administrator
 *
 */
public class PushMessage {
	 private String personName;
	 private String healthFileCode;
	 private String idCard;
	 private String sex;
	 private String flag; //签约标志 1 未签约 2 待续约 3已签约
	 private int age;
	 private double ageData;
	 private String phoneNumber;
	 
	public double getAgeData() {
		return ageData;
	}
	public void setAgeData(double ageData) {
		this.ageData = ageData;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getHealthFileCode() {
		return healthFileCode;
	}
	public void setHealthFileCode(String healthFileCode) {
		this.healthFileCode = healthFileCode;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	 
}
