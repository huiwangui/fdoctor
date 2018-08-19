package com.boco.modules.fdoc.vo;

import com.boco.common.persistence.DataEntity;

public class SigRecordVo extends DataEntity<SigRecordVo>{
	
	private static final long serialVersionUID = 8722353407593751490L;
	
	private int docId;//医生id
	private String docName;//医生名字
	private String personName;//签约人员
	private String sex;//性别
	private String age;//年龄
	private String idCard;//身份证号
	private String signTime;//签约时间
	private String packName;//签约包名
	private int sigId;//签约包id
	private String detailsId;//服务项目id
	private String serviceName;//服务项目名称
	private int resId;//居民id
	private String serviceDetails;//服务内容
	private int count;//已服务次数
	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	public int getSigId() {
		return sigId;
	}
	public void setSigId(int sigId) {
		this.sigId = sigId;
	}
	public String getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public String getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
