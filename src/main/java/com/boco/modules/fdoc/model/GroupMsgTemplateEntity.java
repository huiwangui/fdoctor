package com.boco.modules.fdoc.model;

/**
 * 
 * ClassName: GroupMsgTemplateEntity <br/>
 * Reason: 慢病关怀群发模板表. <br/>
 * date: 2017年2月14日 下午4:04:21 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class GroupMsgTemplateEntity {
    /**
    * 主键ID
    */
    private Integer id;

    /**
    * 医生团队ID
    */
    private String docTeamId;

    /**
    * 模板名称
    */
    private String templateName;

    /**
    * 模板内容
    */
    private String templateContent;

    /**
    * 是否为默认标签：0.否 1.是  若为默认标签，则无法删除，但能修改
    */
    private String defaultFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocTeamId() {
        return docTeamId;
    }

    public void setDocTeamId(String docTeamId) {
        this.docTeamId = docTeamId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
}