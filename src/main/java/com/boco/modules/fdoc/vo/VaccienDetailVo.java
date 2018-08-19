package com.boco.modules.fdoc.vo;


public class VaccienDetailVo {
	private int detailId;
	private String name; //疫苗名称
	private String typeName; //疫苗类型
	private String unit;//单位
	private String approvaNumber;//批准文号
	private Double money; //接种价格（保留）
	private String type; //接种类别（一类、二类）
	private String dose; //计量（1/3等）
	private String optimalTime; //适宜注射该规格疫苗的时间段
	private int vaccineId; //对应疫苗表的ID
	private int sortCode;//排序码
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getApprovaNumber() {
		return approvaNumber;
	}
	public void setApprovaNumber(String approvaNumber) {
		this.approvaNumber = approvaNumber;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
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
		return "VaccienDetailVo [detailId=" + detailId + ", name=" + name
				+ ", typeName=" + typeName + ", unit=" + unit
				+ ", approvaNumber=" + approvaNumber + ", money=" + money
				+ ", type=" + type + ", dose=" + dose + ", optimalTime="
				+ optimalTime + ", vaccineId=" + vaccineId + ", sortCode="
				+ sortCode + "]";
	}
	
}
