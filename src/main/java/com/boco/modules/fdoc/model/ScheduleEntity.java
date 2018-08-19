package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;

/**
    * t_schedule 实体类
    * Thu Jul 28 10:38:16 CST 2016 sun
    */ 


public class ScheduleEntity extends DataEntity<ScheduleEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private Integer version; //版本号
	
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
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setHisId(int hisId){
	this.hisId=hisId;
	}
	public int getHisId(){
		return hisId;
	}
	public void setType(String type){
	this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setDocId(int docId){
	this.docId=docId;
	}
	public int getDocId(){
		return docId;
	}
	public void setWorkTime(Date workTime){
	this.workTime=workTime;
	}
	public Date getWorkTime(){
		return workTime;
	}
	public void setNumber(byte number){
	this.number=number;
	}
	public byte getNumber(){
		return number;
	}
	public void setMoney(double money){
	this.money=money;
	}
	public double getMoney(){
		return money;
	}
	public void setPeriod(String period){
	this.period=period;
	}
	public String getPeriod(){
		return period;
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
