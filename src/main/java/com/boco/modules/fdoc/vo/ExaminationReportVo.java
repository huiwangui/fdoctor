package com.boco.modules.fdoc.vo;

import com.boco.modules.fdoc.model.ExaminationReportEntity;

public class ExaminationReportVo extends ExaminationReportEntity{
	/**
	 * 居民姓名
	 */
	private String personName;
	/**
	 * 医生姓名
	 */
	private String docName;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
}
