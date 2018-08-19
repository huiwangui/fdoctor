package com.boco.modules.fdoc.vo;

import java.util.Date;
import java.util.List;

public class SigServicepackVo {
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 服务包名称
	 */
	private String packName;

	/**
	 * 服务包权值，必须为2的幂次方，且不能重复
	 */
	private Integer packValue;

	/**
	 * 服务包价格
	 */
	private Double packPrice;

	/**
	 * 用户应付金额
	 */
	private Double userPay;

	/**
	 * 机构分成比例 1-100
	 */
	private Float orgratio;

	/**
	 * 建议人群
	 */
	private String adviceGroup;

	/**
	 * 目标
	 */
	private String target;

	/**
	 * 创建者
	 */
	private String createby;

	/**
	 * 创建时间
	 */
	private Date createtime;

	/**
	 * 修改者
	 */
	private String updateby;

	/**
	 * 跟新时间
	 */
	private Date updatetime;

	/**
	 * 描述
	 */
	private String remarks;

	/**
	 * 服务包种类 1.基础服务 2.增值服务
	 */
	private String packType;

	/**
	 * 0:正常 1：逻辑删除
	 */
	private Short delFlag;
	private List<ServicepackDetailsVo> details; // 所含的详细服务内容
	private String residentName; // 选该服务包的居民姓名
	private String idCard; // 选该服务包的居民身份证号
	
	/**
	 * 服务包年限
	 */
	private Integer yearOfSigServicepack;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Float getOrgratio() {
		return orgratio;
	}

	public void setOrgratio(Float orgratio) {
		this.orgratio = orgratio;
	}

	public String getAdviceGroup() {
		return adviceGroup;
	}

	public void setAdviceGroup(String adviceGroup) {
		this.adviceGroup = adviceGroup;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<ServicepackDetailsVo> getDetails() {
		return details;
	}

	public void setDetails(List<ServicepackDetailsVo> details) {
		this.details = details;
	}

	public String getResidentName() {
		return residentName;
	}

	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getPackValue() {
		return packValue;
	}

	public void setPackValue(Integer packValue) {
		this.packValue = packValue;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public Short getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYearOfSigServicepack() {
		return yearOfSigServicepack;
	}

	public void setYearOfSigServicepack(Integer yearOfSigServicepack) {
		this.yearOfSigServicepack = yearOfSigServicepack;
	}
}
