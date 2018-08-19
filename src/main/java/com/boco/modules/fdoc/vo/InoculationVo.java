package com.boco.modules.fdoc.vo;

import java.util.Date;

public class InoculationVo {
	private Integer id;
	private String inoculationNum;  //预约号
	private String uid; //当前登录用户uid
	private String idCard;//接种者身份证
	private Integer detailId;//疫苗详细ID
	private Integer docId;//接种医生ID
	private Long inoDate;//预约接种日期时间戳
	private String period;//接种时间段 1.上午 2.下午
	private String status;//状态： 0.未接种，1.已接种	 -1.已取消
	private Date createTime; //创建时间
	private Integer inoNum; //已接种人数
	private Integer notInoNum; //未接种人数
	private String personName; //接种人姓名
	private String vaccineName; //接种疫苗名称
	private String vaccineSourceId; //疫苗放号ID
	private String hospName;	//接种地点
	private String payStatus;//付款状态： 0.未付款 1.已付款 2.正在退款  3.已退款
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInoculationNum() {
		return inoculationNum;
	}
	public void setInoculationNum(String inoculationNum) {
		this.inoculationNum = inoculationNum;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Long getInoDate() {
		return inoDate;
	}
	public void setInoDate(Long inoDate) {
		this.inoDate = inoDate;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
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
	public Integer getInoNum() {
		return inoNum;
	}
	public void setInoNum(Integer inoNum) {
		this.inoNum = inoNum;
	}
	public Integer getNotInoNum() {
		return notInoNum;
	}
	public void setNotInoNum(Integer notInoNum) {
		this.notInoNum = notInoNum;
	}
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public String getVaccineSourceId() {
		return vaccineSourceId;
	}
	public void setVaccineSourceId(String vaccineSourceId) {
		this.vaccineSourceId = vaccineSourceId;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	
}
