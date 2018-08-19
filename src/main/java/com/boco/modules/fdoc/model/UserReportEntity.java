package com.boco.modules.fdoc.model;

import java.util.Date;

/**
 *    
 * Reason:居民报告类
 *
 */
public class UserReportEntity {
	/**
	 * 居民报告类主键
	 */
	private String tid;

	/**
	 * 医生id
	 */
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
	/**
	 * 居民主键
	 */
	private String personId;
	
	
	public String getDocTeamId() {
		return docTeamId;
	}
	public void setDocTeamId(String docTeamId) {
		this.docTeamId = docTeamId;
	}
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	 
	 
	 
	 
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
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
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	

}
