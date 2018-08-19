package com.boco.modules.fdoc.vo;

import java.util.Date;
import java.util.List;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.persistence.Page;
import com.boco.common.utils.StringUtils;

public class UpPlanVo {
	private Integer id;
	private String docId;// 签约医生ID
	private String userName;// 医生姓名
	private String regionCode;// 医生区域

	private String healthFileCode;// 居民健康档案编号
	private Date upDate;// 随访计划时间
	private String personName;// 居民姓名
	private String idCard; // 居民身份证
	// private Integer age;//年龄
	private double ageData;
	private String sex; // 性别
	private String phoneNumber;// 居民电话号码
	private String taskLevel;// 任务等级
	private String upWay;// 随访方式
	private String taskTarget;// 任务目标
	private String templateName;// 模板名字
	private Integer status;// 状态:0(随访计划制定了未执行),1(此随访计划已执行)
	private String diseaseName;// 疾病id
	private Integer followUpFlag; // 1.随访计划已完成 0.未制作随访计划
	private String followUpId;// 随访记录id
	private Integer ifSign;
	// private Integer familyId;//对应家庭表的主键
	private String familyId;// 对应家庭表的主键 对接
	private String familyFileCode;// 所属家庭档案编码
	private Long upDateTimeStamp;
	private int lookover;// 是否查看
	private Page<UpPlanVo> page; // 分页对象
	private List<String> regionList;

	public double getAgeData() {
		return ageData;
	}

	public void setAgeData(double ageData) {
		this.ageData = ageData;
	}

	public Page<UpPlanVo> getPage() {
		return page;
	}

	public void setPage(Page<UpPlanVo> page) {
		this.page = page;
	}

	public List<String> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<String> regionList) {
		this.regionList = regionList;
	}

	public int getLookover() {
		return lookover;
	}

	public void setLookover(int lookover) {
		this.lookover = lookover;
	}

	private String way;
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public Integer getIfSign() {
		return ifSign;
	}

	public void setIfSign(Integer ifSign) {
		this.ifSign = ifSign;
	}

	public Integer getFollowUpFlag() {
		return followUpFlag;
	}

	public void setFollowUpFlag(Integer followUpFlag) {
		this.followUpFlag = followUpFlag;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * public Integer getAge() { return age; } public void setAge(Integer age) {
	 * this.age = age; }
	 */
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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
	
	public String getRegionCode() {
		return StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}
	
	public void setRegionCode(String regionCode) {
	    this.regionCode = StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHealthFileCode() {
		return healthFileCode;
	}

	public void setHealthFileCode(String healthFileCode) {
		this.healthFileCode = healthFileCode;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}

	public String getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}

	public String getUpWay() {
		return upWay;
	}

	public void setUpWay(String upWay) {
		this.upWay = upWay;
	}

	public String getTaskTarget() {
		return taskTarget;
	}

	public void setTaskTarget(String taskTarget) {
		this.taskTarget = taskTarget;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(String followUpId) {
		this.followUpId = followUpId;
	}

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getFamilyFileCode() {
		return familyFileCode;
	}

	public void setFamilyFileCode(String familyFileCode) {
		this.familyFileCode = familyFileCode;
	}

	public Long getUpDateTimeStamp() {
		return upDateTimeStamp;
	}

	public void setUpDateTimeStamp(Long upDateTimeStamp) {
		this.upDateTimeStamp = upDateTimeStamp;
	}

}
