package com.boco.modules.fdoc.vo;

import com.boco.common.persistence.Page;

public class DiseaseLabelVo {
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
	 * 标签疾病代码 1高血压 3糖尿病 6儿童2重型精神疾病等
	 */
	private Integer diseaseCode;


	/**
	 * 是否为默认标签：0.否 1.是 若为默认标签，则无法修改、删除
	 */
	private String defaultFlag;
	
	/**
	 * 标签下的人数
	 */
	private Integer personCount;
	
	/**
	 * 人员姓名
	 */
	private String personName;
	
	/**
	 * 分页对象
	 */
	private Page<DiseaseLabelVo> page;
	
	/**
	 * 主键ID集合，用逗号隔开
	 */
	private String ids;
	
	/**
	 * 选中标识
	 */
	private String selectFlag;
	
	/**
	 * 人员ID
	 */
	private String personId;

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

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Page<DiseaseLabelVo> getPage() {
		return page;
	}

	public void setPage(Page<DiseaseLabelVo> page) {
		this.page = page;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Integer getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(Integer diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
}
