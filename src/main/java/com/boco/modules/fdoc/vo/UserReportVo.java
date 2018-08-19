package com.boco.modules.fdoc.vo;

import java.util.Date;

import com.boco.common.persistence.Page;

public class UserReportVo {
	private String uid;
	private String personId;
    /**
     * 居民名称
     */
    private String personName;
    /**
     * 居民对应的签约医生
     */
    private String docName;
    private String doctorId;
    private String docTeamId;
	/**
	 * 上传时间
	 */
	private Date uploadTime;
	/**
	 * 报告描述
	 */
	private String reportDescription;
	/**
	 * 报告拍片日期
	 */
	private Date imgTime;
	/**
	 * 存放拍片的URL
	 */
	private String img;
	
	private String status;
	/**
	 * 分页对象
	 */
	private Page<UserReportVo> page;
	
	 
	 
	public String getDocTeamId() {
		return docTeamId;
	}
	public void setDocTeamId(String docTeamId) {
		this.docTeamId = docTeamId;
	}
	public Page<UserReportVo> getPage() {
		return page;
	}
	public void setPage(Page<UserReportVo> page) {
		this.page = page;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
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
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getReportDescription() {
		return reportDescription;
	}
	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}
	public Date getImgTime() {
		return imgTime;
	}
	public void setImgTime(Date imgTime) {
		this.imgTime = imgTime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}
