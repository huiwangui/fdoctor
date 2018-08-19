package com.boco.modules.fdoc.model;

/**
 * 
 * ClassName: DiseaseLabelEntity <br/>
 * Reason: 慢病标签实体类. <br/>
 * date: 2017年2月13日 下午1:39:41 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class DiseaseLabelEntity {
    /**
    * 主键ID
    */
    private Integer id;

    /**
    * 医生团队ID
    */
    private String docTeamId;

    /**
    * 标签名
    */
    private String labelName;

    /**
    * 是否为默认标签：0.否 1.是  若为默认标签，则无法修改、删除
    */
    private String defaultFlag;
    /**
	 * 标签疾病代码 1高血压 3糖尿病 6儿童2重型精神疾病等
	 */
	private Integer diseaseCode;

    public Integer getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(Integer diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

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

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
}