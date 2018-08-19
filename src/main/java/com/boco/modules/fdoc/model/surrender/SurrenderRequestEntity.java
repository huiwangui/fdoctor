package com.boco.modules.fdoc.model.surrender;

import java.util.Date;

/**
 *  解约请求实体类
 */
public class SurrenderRequestEntity {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 居民id
     */
    private String personId;

    /**
     * 医生团队id
     */
    private String docTeamId;

    /**
     * 解约发起方 1,医生 2,用户
     */
    private String originator;

    /**
     * 居民电话号码
     */
    private String residentMobile;

    /**
     * 医生电话号码
     */
    private String docMobile;

    /**解约原因权值
     * 
     */
    private Integer reasonValue;

    /**
     * 其他解约原因
     */
    private String reasonOther;

    /**
     * 解约请求审核状态 1，审核中 2，拒绝解约 3，已解约(同意解约)
     */
    private String status;

    /**
     * 解约请求时间
     */
    private Date requestTime;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核备注
     */
    private String auditRemark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public String getDocTeamId() {
        return docTeamId;
    }

    public void setDocTeamId(String docTeamId) {
        this.docTeamId = docTeamId == null ? null : docTeamId.trim();
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator == null ? null : originator.trim();
    }

    public String getResidentMobile() {
        return residentMobile;
    }

    public void setResidentMobile(String residentMobile) {
        this.residentMobile = residentMobile == null ? null : residentMobile.trim();
    }

    public String getDocMobile() {
        return docMobile;
    }

    public void setDocMobile(String docMobile) {
        this.docMobile = docMobile == null ? null : docMobile.trim();
    }

    public Integer getReasonValue() {
        return reasonValue;
    }

    public void setReasonValue(Integer reasonValue) {
        this.reasonValue = reasonValue;
    }

    public String getReasonOther() {
        return reasonOther;
    }

    public void setReasonOther(String reasonOther) {
        this.reasonOther = reasonOther == null ? null : reasonOther.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }
}