package com.boco.modules.fdoc.vo;

import java.util.Date;
import java.util.List;

import com.boco.common.persistence.Page;
/**
 * 签约服务申请表
 * @author huiwangui
 *
 */
public class SignServiceApplicationVo {
	private int id;//主键
	private String uid;
	private String healthFileCode;//健康档案编号
	private Date signDate;//申请日期
	private int renewalOrUnwind;//续约/解约:0(解约),1(续约)
	private String doctorId;//医生id
	private int handleFlag;//签约处理/未处理标志:0(未处理),1(处理)
	private String personName;//申请签约人员姓名
	private String idCard;//身份证号
	private String sex;//性别
	//private int age;//年龄
	private double ageData;
	private String userName;//医生姓名
	private String phoneNumber;//联系电话 
	private List<String> regionList;
	private Page<SignServiceApplicationVo> page; 
	
	public double getAgeData() {
		return ageData;
	}
	public void setAgeData(double ageData) {
		this.ageData = ageData;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Page<SignServiceApplicationVo> getPage() {
		return page;
	}
	public void setPage(Page<SignServiceApplicationVo> page) {
		this.page = page;
	}
	public List<String> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<String> regionList) {
		this.regionList = regionList;
	}
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
	 
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public int getHandleFlag() {
		return handleFlag;
	}
	public void setHandleFlag(int handleFlag) {
		this.handleFlag = handleFlag;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
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
	/*public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}*/
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
