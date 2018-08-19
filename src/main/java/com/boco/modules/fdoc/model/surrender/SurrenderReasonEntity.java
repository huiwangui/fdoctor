package com.boco.modules.fdoc.model.surrender;

/**
 *  解约原因实体类
 */
public class SurrenderReasonEntity {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 解约原因权值，必须为2得幂次方
     */
    private Integer reasonValue;

    /**
     * 解约原因
     */
    private String reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReasonValue() {
        return reasonValue;
    }

    public void setReasonValue(Integer reasonValue) {
        this.reasonValue = reasonValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}