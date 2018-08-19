package com.boco.modules.fdoc.vo;

import java.util.Date;

/**
 * shcdule查询参数
 * @author q
 *
 */
public class ScheduleVo {
	private int id;
	private int hisId;//排班在his中的ID
	private String type;//挂号类别（普通，专家）
	private int docId;//医生ID
	private Date workTime;//出诊日期
	private byte number;//可挂数量
	private double money;//挂号费用
	private String period;//0-全天，1-上午，2-下午，3-夜诊
	private String weekDate;
	private String workDate;
	private Date createTime;//创建时间
	private Date updataTime;//修改时间
	private Date beginTime;
	private Date endTime;
	private Integer version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHisId() {
		return hisId;
	}
	public void setHisId(int hisId) {
		this.hisId = hisId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
	public byte getNumber() {
		return number;
	}
	public void setNumber(byte number) {
		this.number = number;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getWeekDate() {
		return weekDate;
	}
	public void setWeekDate(String weekDate) {
		this.weekDate = weekDate;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdataTime() {
		return updataTime;
	}
	public void setUpdataTime(Date updataTime) {
		this.updataTime = updataTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
