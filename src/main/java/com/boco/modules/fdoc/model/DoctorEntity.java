package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.utils.StringUtils;

/**
 * 医生实体类
 * ClassName: DoctorEntity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年2月6日 下午2:04:27 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class DoctorEntity {
    /**
    * 医生ID
    */
    private String id;

    /**
    * 医生姓名
    */
    private String docName;

    /**
    * 性别（1男2女）
    */
    private String sex;

    /**
    * 所属科室名称
    */
    private String deptName;

    /**
    * 执业点（所属医院）ID
    */
    private String orgId;

    /**
    * 区划代码
    */
    private String regionCode;

    /**
    * 电话号码
    */
    private String phoneNumber;

    /**
    * 擅长领域
    */
    private String specialty;

    /**
    * 简介
    */
    private String introduction;

    /**
    * 所属医生团队ID
    */
    private String teamId;

    /**
    * 医生分类。1.医生 2.公卫医生  3.护士
    */
    private String docType;

    private Date createTime;

    private Date updateTime;
    
    /**
     * 队长标识，1为队长
     */
    private String leaderFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRegionCode() {
		return StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}
	
	public void setRegionCode(String regionCode) {
	    this.regionCode = StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
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

	public String getLeaderFlag() {
		return leaderFlag;
	}

	public void setLeaderFlag(String leaderFlag) {
		this.leaderFlag = leaderFlag;
	}
}