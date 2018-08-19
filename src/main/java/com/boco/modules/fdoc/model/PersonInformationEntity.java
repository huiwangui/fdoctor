package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.utils.StringUtils;

public class PersonInformationEntity {
    /**
    * 主键
    */
    private String personId;

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
    * 性别
    */
    private String sex;

    /**
    * 出生日期
    */
    private Date dateOfBirth;

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
    * 是否签约 0.未签约  1.已签约
    */
    private Integer ifSign;

    /**
    * 0:注销   1:正常   2：封存   3:死亡   4:结案
    */
    private Integer state;
    
    /**
     * 创建者用户名，不为空时说明为通过家庭医生系统创建
     */
    private String creator;
    /**
     * 基卫personId 
     */
    private String jwPersonId;
    
	/**
	 * qq联系方式
	 */
	private String qq;
	/**
	 * 微信联系方式
	 */
	
	private String wechat;
	
	
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

    public String getJwPersonId() {
		return jwPersonId;
	}

	public void setJwPersonId(String jwPersonId) {
		this.jwPersonId = jwPersonId;
	}

	public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}