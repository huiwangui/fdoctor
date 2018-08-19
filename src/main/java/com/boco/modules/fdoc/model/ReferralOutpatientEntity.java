package com.boco.modules.fdoc.model;

import java.util.Date;
/**
 * 双向转诊-门诊转诊实体类
 * @author q
 *
 */
public class ReferralOutpatientEntity {
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
    * 居民性别（1.男  2.女）
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
    * 紧急程度（1.一般   2.紧急）
    */
    private String urgency;

    /**
    * 患者卡类型（0.社保卡   1.健康卡）
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
    * 转诊原因
    */
    private String referralReason;

    /**
    * 医生手机号码
    */
    private String doctorPhoneNumber;

    /**
    * 转诊办手机号码
    */
    private String referralOfficePhoneNumber;

    /**
    * 是否贫困人口（0.否   1.是）
    */
    private String poolFlag;

    /**
    * 是否为慢病病人（0.否   1.是）
    */
    private String chronicDiseaseFlag;

    /**
    * 是否回转（0. 否   1.是）
    */
    private String rotationFlag;

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
     * 发起申请医院名
     */
     private String applyHospitalName;
    
    /**
    * 发起申请医院代码
    */
    private String applyHospitalCode;

    /**
    * 发起申请科室名
    */
    private String applyDepartmentName;

    /**
    * 发起申请医生名
    */
    private String applyDoctorName;

    /**
    * 发起申请医生身份证
    */
    private String applyDoctorIdCard;

    /**
    * 发起医生工号
    */
    private String applyDoctorEmployeeNumber;

    /**
    * 发起科室代码
    */
    private String applyDepartmentCode;
    
    /**
     * 转入医院名称
     */
     private String itemHospitalName;

    /**
    * 转入医院ID
    */
    private String itemHospitalCode;

    /**
    * 转入科室名称
    */
    private String itemDepartmentName;

    /**
    * 转入医生姓名
    */
    private String itemDoctorName;

    /**
    * 是否补填（0.否   1.是）
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
    * 申请创建时间
    */
    private Date createTime;

    /**
    * 申请创建人账号
    */
    private String creator;
    /**
     * 主要检查结果
     */
    private String mainInspectionResults;
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
	public String getReferralReason() {
		return referralReason;
	}
	public void setReferralReason(String referralReason) {
		this.referralReason = referralReason;
	}
	public String getDoctorPhoneNumber() {
		return doctorPhoneNumber;
	}
	public void setDoctorPhoneNumber(String doctorPhoneNumber) {
		this.doctorPhoneNumber = doctorPhoneNumber;
	}
	public String getReferralOfficePhoneNumber() {
		return referralOfficePhoneNumber;
	}
	public void setReferralOfficePhoneNumber(String referralOfficePhoneNumber) {
		this.referralOfficePhoneNumber = referralOfficePhoneNumber;
	}
	public String getPoolFlag() {
		return poolFlag;
	}
	public void setPoolFlag(String poolFlag) {
		this.poolFlag = poolFlag;
	}
	public String getChronicDiseaseFlag() {
		return chronicDiseaseFlag;
	}
	public void setChronicDiseaseFlag(String chronicDiseaseFlag) {
		this.chronicDiseaseFlag = chronicDiseaseFlag;
	}
	public String getRotationFlag() {
		return rotationFlag;
	}
	public void setRotationFlag(String rotationFlag) {
		this.rotationFlag = rotationFlag;
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
	public void setPatientsIdentificationNumber(String patientsIdentificationNumber) {
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
	public String getApplyDepartmentName() {
		return applyDepartmentName;
	}
	public void setApplyDepartmentName(String applyDepartmentName) {
		this.applyDepartmentName = applyDepartmentName;
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
	public String getApplyDoctorEmployeeNumber() {
		return applyDoctorEmployeeNumber;
	}
	public void setApplyDoctorEmployeeNumber(String applyDoctorEmployeeNumber) {
		this.applyDoctorEmployeeNumber = applyDoctorEmployeeNumber;
	}
	public String getApplyDepartmentCode() {
		return applyDepartmentCode;
	}
	public void setApplyDepartmentCode(String applyDepartmentCode) {
		this.applyDepartmentCode = applyDepartmentCode;
	}
	
	public String getApplyHospitalCode() {
		return applyHospitalCode;
	}
	public void setApplyHospitalCode(String applyHospitalCode) {
		this.applyHospitalCode = applyHospitalCode;
	}
	public String getItemHospitalCode() {
		return itemHospitalCode;
	}
	public void setItemHospitalCode(String itemHospitalCode) {
		this.itemHospitalCode = itemHospitalCode;
	}
	public String getItemDepartmentName() {
		return itemDepartmentName;
	}
	public void setItemDepartmentName(String itemDepartmentName) {
		this.itemDepartmentName = itemDepartmentName;
	}
	public String getItemDoctorName() {
		return itemDoctorName;
	}
	public void setItemDoctorName(String itemDoctorName) {
		this.itemDoctorName = itemDoctorName;
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
	public String getMainInspectionResults() {
		return mainInspectionResults;
	}
	public void setMainInspectionResults(String mainInspectionResults) {
		this.mainInspectionResults = mainInspectionResults;
	}
	public String getApplyHospitalName() {
		return applyHospitalName;
	}
	public void setApplyHospitalName(String applyHospitalName) {
		this.applyHospitalName = applyHospitalName;
	}
	public String getItemHospitalName() {
		return itemHospitalName;
	}
	public void setItemHospitalName(String itemHospitalName) {
		this.itemHospitalName = itemHospitalName;
	}

    
    
}