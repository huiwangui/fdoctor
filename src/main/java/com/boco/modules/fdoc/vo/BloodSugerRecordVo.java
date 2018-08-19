package com.boco.modules.fdoc.vo;

import java.util.Date;

public class BloodSugerRecordVo {
	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 居民ID
	 */
	private String personId;

	/**
	 * 血糖值
	 */
	private Double bloodSuger;

	/**
	 * 检测结果：10.正常 21.偏高轻度 22.偏高中度 23.偏高重度 00.偏低
	 */
	private String detectionResult;

	/**
	 * 测量时间
	 */
	private Date measuringTime;
	
	/**
	 * 检测结果集合，用逗号隔开
	 */
	private String results;
	
	/**
     * 查询条件：开始日期
     */
    private Date StartDate;
    
    /**
     * 查询条件：结束日期
     */
    private Date endDate;
    
    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 操作医生ID
     */
    private String docId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Double getBloodSuger() {
		return bloodSuger;
	}

	public void setBloodSuger(Double bloodSuger) {
		this.bloodSuger = bloodSuger;
	}

	public String getDetectionResult() {
		return detectionResult;
	}

	public void setDetectionResult(String detectionResult) {
		this.detectionResult = detectionResult;
	}

	public Date getMeasuringTime() {
		return measuringTime;
	}

	public void setMeasuringTime(Date measuringTime) {
		this.measuringTime = measuringTime;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

}
