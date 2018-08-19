package com.boco.modules.fdoc.model;
import java.util.Date;

import com.boco.common.persistence.DataEntity;

   /**
    * t_admission_stop 实体类
    * Thu Jul 28 09:55:32 CST 2016 sun
    */ 


public class AdmissionStopEntity extends DataEntity<AdmissionStopEntity>{
	private int id;
	private int hisId;//停诊计划ID（停诊计划在HIS系统的主键）
	private int scheId;//排班ID（排班在HIS系统中的主键）
	private String status;//停诊/恢复标志
	private Date startDate;//开始日期
	private Date endDate;//结束日期
	private String flag;//停诊标志(0上午停诊，1下午停诊，2不停诊）
	private String reason;//停诊原因
	private Date createTime;//创建时间
	private Date updateTime;//变更时间
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
	public void setScheId(int scheId){
	this.scheId=scheId;
	}
	public int getScheId(){
		return scheId;
	}
	public void setStatus(String status){
	this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setStartDate(Date startDate){
	this.startDate=startDate;
	}
	public Date getStartDate(){
		return startDate;
	}
	public void setEndDate(Date endDate){
	this.endDate=endDate;
	}
	public Date getEndDate(){
		return endDate;
	}
	public void setFlag(String flag){
	this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public void setReason(String reason){
	this.reason=reason;
	}
	public String getReason(){
		return reason;
	}
	public void setUpdateTime(Date updateTime){
	this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

