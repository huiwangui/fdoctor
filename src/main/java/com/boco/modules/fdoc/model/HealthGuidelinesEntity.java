package com.boco.modules.fdoc.model;

import java.util.Date;

public class HealthGuidelinesEntity {
	
private	Integer id;
private	String docId;//医生id
private	Date  	addtime;//添加时间
private	String personId;//居民id
private	String nutritionIntervention;//营养方案
private	String exerciseIntervention;//运动方案	
private	String behavioralIntervention;//行为方案
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getDocId() {
	return docId;
}
public void setDocId(String docId) {
	this.docId = docId;
}
public Date getAddtime() {
	return addtime;
}
public void setAddtime(Date addtime) {
	this.addtime = addtime;
}
public String getPersonId() {
	return personId;
}
public void setPersonId(String personId) {
	this.personId = personId;
}
public String getNutritionIntervention() {
	return nutritionIntervention;
}
public void setNutritionIntervention(String nutritionIntervention) {
	this.nutritionIntervention = nutritionIntervention;
}
public String getExerciseIntervention() {
	return exerciseIntervention;
}
public void setExerciseIntervention(String exerciseIntervention) {
	this.exerciseIntervention = exerciseIntervention;
}
public String getBehavioralIntervention() {
	return behavioralIntervention;
}
public void setBehavioralIntervention(String behavioralIntervention) {
	this.behavioralIntervention = behavioralIntervention;
}

}
