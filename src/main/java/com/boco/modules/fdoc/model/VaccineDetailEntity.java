package com.boco.modules.fdoc.model;

import com.boco.common.persistence.DataEntity;
/**
 * 疫苗详细信息表
 * @author q
 */
public class VaccineDetailEntity extends DataEntity<VaccineDetailEntity>{
	private static final long serialVersionUID = 1L;
	private int id;
	private String type; //接种类别（一类、二类）
	private String dose; //计量（1/3等）
	private String optimalTime; //适宜注射该规格疫苗的时间段
	private int vaccineId; //对应疫苗表的ID
	private int sortCode;//排序码
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getOptimalTime() {
		return optimalTime;
	}
	public void setOptimalTime(String optimalTime) {
		this.optimalTime = optimalTime;
	}
	public int getVaccineId() {
		return vaccineId;
	}
	public void setVaccineId(int vaccineId) {
		this.vaccineId = vaccineId;
	}
	public int getSortCode() {
		return sortCode;
	}
	public void setSortCode(int sortCode) {
		this.sortCode = sortCode;
	}
	@Override
	public String toString() {
		return "VaccineDetailEntity [id=" + id + ", type=" + type + ", dose="
				+ dose + ", optimalTime=" + optimalTime + ", vaccineId="
				+ vaccineId + ", sortCode=" + sortCode + "]";
	}
	
	
}
