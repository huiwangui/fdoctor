package com.boco.modules.fdoc.model;
import java.util.Date;

import com.boco.common.persistence.DataEntity;

   /**
    * t_booking_rule 实体类
    * Thu Jul 28 10:11:31 CST 2016 sun
    */ 


public class BookingRuleEntity extends DataEntity<BookingRuleEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date getTime;//取号时间
	private Date updateTime;//可预约班次更新时间/放号时间
	private int period;//预约周期（X天内）
	private String cancleTime;//取消时限/退号时间
	private String punish;//爽约机制
	private String timeRange;//就诊时间段
	private String attention;//注意事项
	private int hospId;//医院ID
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setGetTime(Date getTime){
	this.getTime=getTime;
	}
	public Date getGetTime(){
		return getTime;
	}
	public void setUpdateTime(Date updateTime){
	this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setPeriod(int period){
	this.period=period;
	}
	public int getPeriod(){
		return period;
	}
	public void setCancleTime(String cancleTime){
	this.cancleTime=cancleTime;
	}
	public String getCancleTime(){
		return cancleTime;
	}
	public void setPunish(String punish){
	this.punish=punish;
	}
	public String getPunish(){
		return punish;
	}
	public void setTimeRange(String timeRange){
	this.timeRange=timeRange;
	}
	public String getTimeRange(){
		return timeRange;
	}
	public void setAttention(String attention){
	this.attention=attention;
	}
	public String getAttention(){
		return attention;
	}
	public void setHospId(int hospId){
	this.hospId=hospId;
	}
	public int getHospId(){
		return hospId;
	}
}

