package com.boco.modules.fdoc.model;

import java.util.Date;

public class ReferralHospitalizationEntity {
	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 转诊编码，作为和接口方一致的唯一标识
	 */
	private String referralCode;

	/**
	 * 居民ID
	 */
	private String personId;

	/**
	 * 居民姓名
	 */
	private String personName;

	/**
	 * 居民性别（1.男 2.女）
	 */
	private String sex;

	/**
	 * 居民年龄
	 */
	private Integer age;

	/**
	 * 出生日期
	 */
	private Date dateOfBirth;

	/**
	 * 居民身份证号
	 */
	private String idCard;

	/**
	 * 居民家庭住址
	 */
	private String address;

	/**
	 * 居民手机号码
	 */
	private String phoneNumber;

	/**
	 * 紧急程度（1.一般 2.紧急）
	 */
	private String urgency;

	/**
	 * 患者卡类型（0.社保卡 1.健康卡）
	 */
	private String patientCardType;

	/**
	 * 患者卡号
	 */
	private String patientCardNumber;

	/**
	 * 初步诊断
	 */
	private String impression;

	/**
	 * 病史摘要
	 */
	private String summary;

	/**
	 * 既往史
	 */
	private String history;

	/**
	 * 治疗情况
	 */
	private String treatmentSituation;

	/**
	 * 转诊目的
	 */
	private String refferralPurpose;

	/**
	 * 转诊原因
	 */
	private String referralReason;

	/**
	 * 主要检查结果
	 */
	private String mainInspectionResults;

	/**
	 * 是否贫困人口（0.否 1.是）
	 */
	private String poolFlag;

	/**
	 * 档案编号
	 */
	private String fileCode;

	/**
	 * 病案号
	 */
	private String patientsIdentificationNumber;

	/**
	 * 住院病案号
	 */
	private String hospitalizationPatientsIdentificationNumber;

	/**
	 * 康复建议
	 */
	private String rehabilitationSuggestion;

	/**
	 * 申请日期（实际转诊日期）
	 */
	private Date applyDate;

	/**
	 * 申请医院代码（转出医院）
	 */
	private String applyHospitalCode;

	/**
	 * 申请医院（转出医院名称）
	 */
	private String applyHospitalName;

	/**
	 * 转入医院代码
	 */
	private String itemHospitalCode;

	/**
	 * 转入医院名称
	 */
	private String itemHospitalName;

	/**
	 * 长期医嘱
	 */
	private String longTermTestament;

	/**
	 * 影像报告
	 */
	private String imagingReport;

	/**
	 * 检验检查
	 */
	private String inspectionAndTest;

	/**
	 * 入院记录
	 */
	private String admissionRecord;

	/**
	 * 病程记录
	 */
	private String courseRecord;

	/**
	 * 手术记录
	 */
	private String operationRecord;

	/**
	 * 临时医嘱
	 */
	private String statOrder;

	/**
	 * 其他
	 */
	private String others;

	/**
	 * 是否补填（0.否 1.是）
	 */
	private String fillingFlag;

	/**
	 * 补填原因
	 */
	private String fillingReason;

	/**
	 * 实际转诊日期
	 */
	private Date realReferralDate;

	/**
	 * 流程状态
	 */
	private String status;
	
	/**
	 * 申请医生姓名
	 */
	private String applyDoctorName;
	
	/**
	 * 申请医生身份证
	 */
	private String applyDoctorIdCard;

	/**
	 * 申请创建时间
	 */
	private Date createTime;

	/**
	 * 申请创建人账号
	 */
	private String creator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getPatientCardType() {
		return patientCardType;
	}

	public void setPatientCardType(String patientCardType) {
		this.patientCardType = patientCardType;
	}

	public String getPatientCardNumber() {
		return patientCardNumber;
	}

	public void setPatientCardNumber(String patientCardNumber) {
		this.patientCardNumber = patientCardNumber;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getTreatmentSituation() {
		return treatmentSituation;
	}

	public void setTreatmentSituation(String treatmentSituation) {
		this.treatmentSituation = treatmentSituation;
	}

	public String getRefferralPurpose() {
		return refferralPurpose;
	}

	public void setRefferralPurpose(String refferralPurpose) {
		this.refferralPurpose = refferralPurpose;
	}

	public String getReferralReason() {
		return referralReason;
	}

	public void setReferralReason(String referralReason) {
		this.referralReason = referralReason;
	}

	public String getMainInspectionResults() {
		return mainInspectionResults;
	}

	public void setMainInspectionResults(String mainInspectionResults) {
		this.mainInspectionResults = mainInspectionResults;
	}

	public String getPoolFlag() {
		return poolFlag;
	}

	public void setPoolFlag(String poolFlag) {
		this.poolFlag = poolFlag;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getPatientsIdentificationNumber() {
		return patientsIdentificationNumber;
	}

	public void setPatientsIdentificationNumber(
			String patientsIdentificationNumber) {
		this.patientsIdentificationNumber = patientsIdentificationNumber;
	}

	public String getHospitalizationPatientsIdentificationNumber() {
		return hospitalizationPatientsIdentificationNumber;
	}

	public void setHospitalizationPatientsIdentificationNumber(
			String hospitalizationPatientsIdentificationNumber) {
		this.hospitalizationPatientsIdentificationNumber = hospitalizationPatientsIdentificationNumber;
	}

	public String getRehabilitationSuggestion() {
		return rehabilitationSuggestion;
	}

	public void setRehabilitationSuggestion(String rehabilitationSuggestion) {
		this.rehabilitationSuggestion = rehabilitationSuggestion;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyHospitalCode() {
		return applyHospitalCode;
	}

	public void setApplyHospitalCode(String applyHospitalCode) {
		this.applyHospitalCode = applyHospitalCode;
	}

	public String getApplyHospitalName() {
		return applyHospitalName;
	}

	public void setApplyHospitalName(String applyHospitalName) {
		this.applyHospitalName = applyHospitalName;
	}

	public String getItemHospitalCode() {
		return itemHospitalCode;
	}

	public void setItemHospitalCode(String itemHospitalCode) {
		this.itemHospitalCode = itemHospitalCode;
	}

	public String getItemHospitalName() {
		return itemHospitalName;
	}

	public void setItemHospitalName(String itemHospitalName) {
		this.itemHospitalName = itemHospitalName;
	}

	public String getLongTermTestament() {
		return longTermTestament;
	}

	public void setLongTermTestament(String longTermTestament) {
		this.longTermTestament = longTermTestament;
	}

	public String getImagingReport() {
		return imagingReport;
	}

	public void setImagingReport(String imagingReport) {
		this.imagingReport = imagingReport;
	}

	public String getInspectionAndTest() {
		return inspectionAndTest;
	}

	public void setInspectionAndTest(String inspectionAndTest) {
		this.inspectionAndTest = inspectionAndTest;
	}

	public String getAdmissionRecord() {
		return admissionRecord;
	}

	public void setAdmissionRecord(String admissionRecord) {
		this.admissionRecord = admissionRecord;
	}

	public String getCourseRecord() {
		return courseRecord;
	}

	public void setCourseRecord(String courseRecord) {
		this.courseRecord = courseRecord;
	}

	public String getOperationRecord() {
		return operationRecord;
	}

	public void setOperationRecord(String operationRecord) {
		this.operationRecord = operationRecord;
	}

	public String getStatOrder() {
		return statOrder;
	}

	public void setStatOrder(String statOrder) {
		this.statOrder = statOrder;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getFillingFlag() {
		return fillingFlag;
	}

	public void setFillingFlag(String fillingFlag) {
		this.fillingFlag = fillingFlag;
	}

	public String getFillingReason() {
		return fillingReason;
	}

	public void setFillingReason(String fillingReason) {
		this.fillingReason = fillingReason;
	}

	public Date getRealReferralDate() {
		return realReferralDate;
	}

	public void setRealReferralDate(Date realReferralDate) {
		this.realReferralDate = realReferralDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getApplyDoctorName() {
		return applyDoctorName;
	}

	public void setApplyDoctorName(String applyDoctorName) {
		this.applyDoctorName = applyDoctorName;
	}

	public String getApplyDoctorIdCard() {
		return applyDoctorIdCard;
	}

	public void setApplyDoctorIdCard(String applyDoctorIdCard) {
		this.applyDoctorIdCard = applyDoctorIdCard;
	}
	
}