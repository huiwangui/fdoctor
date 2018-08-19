package com.boco.modules.fdoc.model;

import java.util.Date;

public class FdoctorUPplanEntity {
	
	private Integer id;
	private String healthFileCode;//居民健康档案编号
	private Date upDate;//随访计划时间
	private String taskLevel;//任务等级
	private String upWay;//随访方式
	private String taskTarget;//任务目标
	private String templateName;//模板名字
	private Integer status;//状态:0(随访计划制定了未执行),1(此随访计划已执行) 
	private Integer diseaseId;//疾病id

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
	public Integer getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(Integer diseaseId) {
		this.diseaseId = diseaseId;
	}
	
}
