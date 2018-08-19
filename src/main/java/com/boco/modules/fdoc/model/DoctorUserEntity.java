package com.boco.modules.fdoc.model;

import java.util.Date;

public class DoctorUserEntity {
    /**
    * 医生用户ID
    */
    private String doctorUserId;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 密码
    */
    private String password;
    
    /**
     * 昵称
     */
    private String nickName;

    /**
    * 图片地址
    */
    private String img;

    /**
    * 卫计委接口产品代码
    */
    private String productCode;

    /**
    * 医生ID
    */
    private String doctorId;

    private Date createTime;

    private Date updateTime;

    public DoctorUserEntity() {
    	
	}
    /**
     * 用户名、密码创建对象构造函数
     */
    public DoctorUserEntity(String userName,String password) {
    	this.userName = userName;
    	this.password = password;
	}
    
    public String getDoctorUserId() {
        return doctorUserId;
    }

    public void setDoctorUserId(String doctorUserId) {
        this.doctorUserId = doctorUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    
    
}