package com.boco.modules.fdoc.model;

import java.util.Date;

public class BloodSugerRecordEntity {
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
    * 检测结果：10.正常  21.偏高轻度  22.偏高中度  23.偏高重度  00.偏低
    */
    private String detectionResult;

    /**
    * 测量时间
    */
    private Date measuringTime;
    
    /**
     * 备注
     */
    private String remarks;

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}