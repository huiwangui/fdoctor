package com.boco.modules.fdoc.vo;

import java.io.Serializable;
import java.util.Date;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.persistence.Page;
import com.boco.common.utils.StringUtils;

public class UserVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户唯一标识
	 */
	private String uid;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 个人居民编号
	 */
	private String personId;

	/**
	 * 用户代号
	 */
	private String userCode;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 头像URL
	 */
	private String img;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 状态：1正常(默认)，2禁用
	 */
	private String status;

	/**
	 * 邮箱（预留字段）
	 */
	private String email;

	/**
	 * 添加时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 最后登陆时间
	 */
	private Date loginTime;

	/**
	 * 删除标记0：正常（默认）1：逻辑删除 2：物理删除 3：审核
	 */
	private String delFlag;

	/**
	 * 身高
	 */
	private Double height;

	/**
	 * 体重
	 */
	private Double weight;

	/**
	 * 出生年月
	 */
	private Date dateOfBirth;

	/**
	 * 实名认证标识：1. 已认证 0. 未认证
	 */
	private String authenFlag;

	/**
	 * 健康档案编号
	 */
	private String personCode;

	/**
	 * 对应家庭表的主键
	 */
	private String familyId;

	/**
	 * 自定义编号
	 */
	private String customNumber;

	/**
	 * 姓名
	 */
	private String personName;

	/**
	 * 身份证件号码
	 */
	private String idCard;

	/**
	 * 本人联系电话
	 */
	private String phoneNumber;

	/**
	 * 现住址
	 */
	private String currentAddress;

	/**
	 * 行政区划代码
	 */
	private String regionCode;

	/**
	 * 户主标识，1为户主
	 */
	private String masterFlag;

	/**
	 * 所属组名称
	 */
	private String unit;

	/**
	 * 是否签约 0.未签约 1.已签约
	 */
	private Integer ifSign;

	/**
	 * 0:注销 1:正常 2：封存 3:死亡 4:结案
	 */
	private Integer state;
	
	/**
	 * 分页对象
	 */
	private Page<UserVo> page;
	
	private String jwPersonId;
	

	public String getJwPersonId() {
		return jwPersonId;
	}

	public void setJwPersonId(String jwPersonId) {
		this.jwPersonId = jwPersonId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAuthenFlag() {
		return authenFlag;
	}

	public void setAuthenFlag(String authenFlag) {
		this.authenFlag = authenFlag;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getCustomNumber() {
		return customNumber;
	}

	public void setCustomNumber(String customNumber) {
		this.customNumber = customNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getRegionCode() {
		return StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}
	
	public void setRegionCode(String regionCode) {
	    this.regionCode = StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}

	public String getMasterFlag() {
		return masterFlag;
	}

	public void setMasterFlag(String masterFlag) {
		this.masterFlag = masterFlag;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getIfSign() {
		return ifSign;
	}

	public void setIfSign(Integer ifSign) {
		this.ifSign = ifSign;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Page<UserVo> getPage() {
		return page;
	}

	public void setPage(Page<UserVo> page) {
		this.page = page;
	}
}
