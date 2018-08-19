package com.boco.modules.fdoc.model;

import java.util.Date;

public class ApiCallerEntity {
    /**
    * 主键ID
    */
    private Integer id;

    /**
    * 厂商用户名
    */
    private String userName;

    /**
    * 厂商密码
    */
    private String password;

    /**
    * 厂商名
    */
    private String companyName;

    /**
    * 厂商代码
    */
    private String companyCode;

    /**
    * app名称
    */
    private String appName;

    /**
    * app代码
    */
    private String appCode;

    /**
    * 备注
    */
    private String remark;

    /**
    * 新增时间
    */
    private Date createTime;
    
    public ApiCallerEntity(){
    	
    }
    
    public ApiCallerEntity(String userName, String password){
    	this.userName = userName;
    	this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}