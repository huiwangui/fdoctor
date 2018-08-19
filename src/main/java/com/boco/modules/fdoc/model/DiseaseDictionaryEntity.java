package com.boco.modules.fdoc.model;
/**
 * 疾病字典表
 * @author Administrator
 *
 */
public class DiseaseDictionaryEntity {
	private int id;//主键
	private String diseaseId;//疾病ID
	private int diseaseCode;//疾病代码
	private String diseaseName;//疾病名称
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}
	public int getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(int diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	
}
