package com.boco.modules.fdoc.vo;

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
public class HypertensionRecordVo {
	
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
     * 查询条件：检测结果列表，用逗号隔开
     */
    private String detectionResults;
    
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

	public String getDetectionResults() {
		return detectionResults;
	}

	public void setDetectionResults(String detectionResults) {
		this.detectionResults = detectionResults;
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