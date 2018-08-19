package com.boco.modules.fdoc.model;

import java.util.List;

public class FamilyMedicalHistory {

	private int id;
	private String familyId; // 对应家庭表主键',
	private String familyNameCode; // 家族性疾病名称代码',
	private String familyNameChs;// 家族性疾病名称代码(中文)',
	private String relationCode; // '患者与本人关系代码',
	private String relationChs; // '患者与本人关系代码(中文)',
	private String healthFileCode; // '城乡居民健康档案编号',
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getFamilyNameCode() {
		return familyNameCode;
	}
	public void setFamilyNameCode(String familyNameCode) {
		this.familyNameCode = familyNameCode;
	}
	public String getFamilyNameChs() {
		return familyNameChs;
	}
	public void setFamilyNameChs(String familyNameChs) {
		this.familyNameChs = familyNameChs;
	}
	public String getRelationCode() {
		return relationCode;
	}
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}
	public String getRelationChs() {
		return relationChs;
	}
	public void setRelationChs(String relationChs) {
		this.relationChs = relationChs;
	}
	public String getHealthFileCode() {
		return healthFileCode;
	}
	public void setHealthFileCode(String healthFileCode) {
		this.healthFileCode = healthFileCode;
	}
	
	

}
