package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;

public class MoneyMangerEntity extends DataEntity<MoneyMangerEntity> {
	private static final long serialVersionUID = 1L;
	// 居民ID
	private int id;
	// 居民姓名
	private String personName;
	// 性别
	private int sex;
	// 年龄
	private int age;
	//生日
	private Date dateOfBirth;
	private String idCard; // 身份证号
	//签约时间
	private Date signTime;
	//服务包名称
	private String packName;
	//服务包价格
	private Double packPrice;
	//居民自付
	private Double userPay;
	//补偿金额
	private Double compensate;
	
	//总签约人数
	private  int  totalSig;
	
	//机构分成比例
	private Double orgratio;
	
	//总价格
	private Double totalPrice;
	//自己负总价格
	private Double totalUserPay;
	//补偿总价格
	private Double totalCompensate;
	//机构分成总价格
	private Double totalOrgin;
	//统计时间
	private String  countTime;
	//开始时间
	private String  beginTime;
	//结束时间
	private String  endTime;
	
	public MoneyMangerEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalUserPay() {
		return totalUserPay;
	}

	public void setTotalUserPay(Double totalUserPay) {
		this.totalUserPay = totalUserPay;
	}

	public Double getTotalCompensate() {
		return totalCompensate;
	}

	public void setTotalCompensate(Double totalCompensate) {
		this.totalCompensate = totalCompensate;
	}

	public Double getTotalOrgin() {
		return totalOrgin;
	}

	public void setTotalOrgin(Double totalOrgin) {
		this.totalOrgin = totalOrgin;
	}

	public String getCountTime() {
		return countTime;
	}

	public void setCountTime(String countTime) {
		this.countTime = countTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}
	
	public int getTotalSig() {
		return totalSig;
	}

	public void setTotalSig(int totalSig) {
		this.totalSig = totalSig;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
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

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public Double getPackPrice() {
		return packPrice;
	}

	public void setPackPrice(Double packPrice) {
		this.packPrice = packPrice;
	}

	public Double getUserPay() {
		return userPay;
	}

	public void setUserPay(Double userPay) {
		this.userPay = userPay;
	}

	public Double getCompensate() {
		return compensate;
	}

	public void setCompensate(Double compensate) {
		this.compensate = compensate;
	}

	public Double getOrgratio() {
		return orgratio;
	}

	public void setOrgratio(Double orgratio) {
		this.orgratio = orgratio;
	}
	
	

}
