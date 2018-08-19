package com.boco.modules.fdoc.model;

import java.util.Date;

public class ExaminationReportEntity {
	
	/**
	 * 主键ID
	 */
    private Integer id;

    /**
    * 居民ID
    */
    private String personId;

    /**
    * 医生ID
    */
    private String docId;
    
    /**
     * 对应体检ID
     */
    private String examinationId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 报告内容
     */
    private String content;

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
        this.personId = personId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(String examinationId) {
		this.examinationId = examinationId;
	}
    
}