package com.boco.modules.fdoc.model.expert;

import java.util.Date;

public class ExpertUserSignEntity {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 专家id
     */
    private String expertId;

    /**
     * 居民id
     */
    private String personId;

    /**
     * 关注状态：1.关注中 2.取消关注
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId == null ? null : expertId.trim();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}