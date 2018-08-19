package com.boco.modules.fdoc.model.expert;

import java.util.Date;

public class ExpertInformationEntity {
    /**
     * 主键
     */
    private String id;

    /**
     * 专家姓名
     */
    private String expertName;

    /**
     * 性别（1男2女）
     */
    private String sex;

    /**
     * 职称
     */
    private String title;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 特长
     */
    private String specialty;

    /**
     * 头像
     */
    private String img;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 所在医院id
     */
    private String orgId;

    /**
     * 所在科室
     */
    private String department;
    
    /**
     * 个人风采
     */
    private String personalStyle;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName == null ? null : expertName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

     

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPersonalStyle() {
		return personalStyle;
	}

	public void setPersonalStyle(String personalStyle) {
		this.personalStyle = personalStyle;
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
    
}