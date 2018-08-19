package com.boco.modules.fdoc.vo;

import java.util.Date;

import com.boco.common.persistence.Page;

public class HealthAssessmentVo {
	private Integer id;

    /**
    * 医生团队ID
    */
    private String docTeamId;

    /**
    * 居民ID
    */
    private String personId;

    /**
    * 评估医生ID
    */
    private String docId;

    /**
    * 异常类型：1. 血压  2.血糖
    */
    private String exceptionType;

    /**
    * 异常血糖值（若异常项目为血压则为空）
    */
    private Double bloodSuger;

    /**
    * 异常收缩压（若异常项目为血糖则为空）
    */
    private Double systolicPressure;

    /**
    * 异常舒张压（若异常项目为血糖则为空）
    */
    private Double diastolicPressure;

    /**
    * 检测结果：00.偏低  10.正常 11.正常高值  21.轻度偏高  22.中度偏高  23.重度偏高
    */
    private String detectionResult;

    /**
    * 异常数据备注（用户录入）
    */
    private String remark;

    /**
    * 评估标题
    */
    private String title;

    /**
    * 评估内容
    */
    private String content;

    /**
    * 用药指导
    */
    private String medicationGuide;

    /**
    * 关键字，用;隔开
    */
    private String keyWords;

    /**
    * 评估时间
    */
    private Date assessmentTime;

    /**
    * 记录创建时间（异常数据产生时间）
    */
    private Date createTime;

    /**
    * 状态：1.待评估  2.已评估
    */
    private String status;
    
    /**
     * 居民姓名
     */
    private String personName;
    
    /**
     * 分页对象
     */
    private Page<HealthAssessmentVo> page;
    
    /**
     * 查询起始时间
     */
    private Date beginDate;
    
    /**
     * 查询终止时间
     */
    private Date endDate;
    
    /**
     * 医生姓名
     */
    private String docName;
    
    /**
     * 月份标识，值为当前月份-1
     */
    private Integer monthTag;
    
    /**
     * 医生头像
     */
    private String img;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocTeamId() {
		return docTeamId;
	}

	public void setDocTeamId(String docTeamId) {
		this.docTeamId = docTeamId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public Double getBloodSuger() {
		return bloodSuger;
	}

	public void setBloodSuger(Double bloodSuger) {
		this.bloodSuger = bloodSuger;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMedicationGuide() {
		return medicationGuide;
	}

	public void setMedicationGuide(String medicationGuide) {
		this.medicationGuide = medicationGuide;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Date getAssessmentTime() {
		return assessmentTime;
	}

	public void setAssessmentTime(Date assessmentTime) {
		this.assessmentTime = assessmentTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Page<HealthAssessmentVo> getPage() {
		return page;
	}

	public void setPage(Page<HealthAssessmentVo> page) {
		this.page = page;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Integer getMonthTag() {
		return monthTag;
	}

	public void setMonthTag(Integer monthTag) {
		this.monthTag = monthTag;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
    
}
