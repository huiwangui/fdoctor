package com.boco.modules.fdoc.model;

import java.util.Date;

public class SignAgreementEntity {
    private Integer id;

    /**
    * 签约信息id
    */
    private Integer signId;

    /**
    * 协议号
    */
    private String agreementNum;

    /**
    * 签约时间
    */
    private Date signTime;

    /**
    * 到期时间
    */
    private Date dueTime;

    /**
    * 协议状态：1.进行中 2.已过期 3.已取消
    */
    private String status;

    /**
    * 取消时间
    */
    private Date cancleTime;

    /**
    * 所选服务包权值，为多选值相加
    */
    private Integer servicePackValue;

    /**
    * 协议内容
    */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSignId() {
        return signId;
    }

    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    public String getAgreementNum() {
        return agreementNum;
    }

    public void setAgreementNum(String agreementNum) {
        this.agreementNum = agreementNum;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCancleTime() {
        return cancleTime;
    }

    public void setCancleTime(Date cancleTime) {
        this.cancleTime = cancleTime;
    }

    public Integer getServicePackValue() {
        return servicePackValue;
    }

    public void setServicePackValue(Integer servicePackValue) {
        this.servicePackValue = servicePackValue;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}