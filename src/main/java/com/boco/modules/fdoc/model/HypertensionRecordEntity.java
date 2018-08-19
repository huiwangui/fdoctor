package com.boco.modules.fdoc.model;

import java.util.Date;

/**
 * 
 * ClassName: HypertensionRecordEntity <br/>
 * Reason: 血压记录表. <br/>
 * date: 2017年2月17日 下午1:57:55 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class HypertensionRecordEntity {
	
	/**
	 * 主键ID
	 */
    private Integer id;

    /**
    * 居民ID
    */
    private String personId;

    /**
    * 收缩压（mmHg）
    */
    private Double systolicPressure;

    /**
    * 舒张压（mmHg）
    */
    private Double diastolicPressure;

    /**
    * 检测结果：00.血压偏低  10.血压正常 11.正常高值  21.轻度偏高  22.中度偏高  23.重度偏高
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

    public Double getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(Double systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public Double getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(Double diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
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