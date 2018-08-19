package com.boco.modules.fdoc.model;

import java.util.Date;
/**
 * 
 * ClassName: UserEntity <br/>
 * Reason: 用户实体类. <br/>
 * date: 2017年2月15日 上午11:10:07 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class UserEntity {
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
    * 实名认证标识：1. 已认证  0. 未认证
    */
    private String authenFlag;

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
}