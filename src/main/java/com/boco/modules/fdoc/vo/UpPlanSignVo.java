package com.boco.modules.fdoc.vo;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.utils.StringUtils;

public class UpPlanSignVo {
	private Integer docId;	//签约医生ID
	private String userName;	//医生姓名
	private String regionCode;	//医生区域
	private String personName;	//居民姓名	
	private String healthFileCode;	//居民健康档案编号
	private String idCard;	//居民身份证
	private String sex;	//性别
	private Integer age;//年龄 	 
	private Integer followUpFlag;	//1.随访计划已完成  0.未制作随访计划
	private Integer diseaseId;//疾病id:1(高血压),2(冠心病),3(糖尿病),4(肿瘤),5(重性精神病),6(其它)
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	 
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRegionCode() {
		return StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}
	
	public void setRegionCode(String regionCode) {
	    this.regionCode = StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getFollowUpFlag() {
		return followUpFlag;
	}
	public void setFollowUpFlag(Integer followUpFlag) {
		this.followUpFlag = followUpFlag;
	}
	public Integer getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(Integer diseaseId) {
		this.diseaseId = diseaseId;
	}
	
}
