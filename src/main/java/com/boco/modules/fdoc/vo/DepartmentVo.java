package com.boco.modules.fdoc.vo;

import java.util.Date;
import java.util.List;


public class DepartmentVo {

	private int id;
	private Integer hisId;//科室编号（HIS中的编号）
	private String name;//科室名称
	private String code;//科室标准码
	private String detail;//科室描述
	private String ageLimit;//年龄限制
	private Date updateTime;//更新时间
	private String level;//科室级别（0为大分类科室，1为详细科室）
	private String ima;
	private Integer bookingNum;
	private Integer doctors;//科室下医生人数
	private Date createTime;//创建时间
	
	/**
	 * 下级科室 multi
	 */
	private List<DepartmentVo> subDepts;

	public DepartmentVo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<DepartmentVo> getSubDepts() {
		return subDepts;
	}

	public void setSubDepts(List<DepartmentVo> subDepts) {
		this.subDepts = subDepts;
	}

	public Integer getHisId() {
		return hisId;
	}

	public void setHisId(Integer hisId) {
		this.hisId = hisId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIma() {
		return ima;
	}

	public void setIma(String ima) {
		this.ima = ima;
	}

	public Integer getBookingNum() {
		return bookingNum;
	}

	public void setBookingNum(Integer bookingNum) {
		this.bookingNum = bookingNum;
	}

	public Integer getDoctors() {
		return doctors;
	}

	public void setDoctors(Integer doctors) {
		this.doctors = doctors;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "DepartmentVo [id=" + id + ", hisId=" + hisId + ", name=" + name
				+ ", code=" + code + ", detail=" + detail + ", ageLimit="
				+ ageLimit + ", updateTime=" + updateTime + ", level=" + level
				+ ", ima=" + ima + ", bookingNum=" + bookingNum + ", doctors="
				+ doctors + ", createTime=" + createTime + ", subDepts="
				+ subDepts + "]";
	}
	
	
}
