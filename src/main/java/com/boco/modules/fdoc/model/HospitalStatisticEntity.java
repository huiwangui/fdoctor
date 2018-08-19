package com.boco.modules.fdoc.model;

   /**
    * t_hospital_statistic 实体类
    * Thu Jul 28 10:33:02 CST 2016 sun
    */ 


public class HospitalStatisticEntity{
	private int id;
	private int bookNum;//预约量
	private String satisfy;//服务满意度(满分5分，默认5分)
	private String evalulate;//患者评价
	private String waitTime;//候诊时间
	private String concerns;//关注度
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setBookNum(int bookNum){
	this.bookNum=bookNum;
	}
	public int getBookNum(){
		return bookNum;
	}
	public void setSatisfy(String satisfy){
	this.satisfy=satisfy;
	}
	public String getSatisfy(){
		return satisfy;
	}
	public void setEvalulate(String evalulate){
	this.evalulate=evalulate;
	}
	public String getEvalulate(){
		return evalulate;
	}
	public void setWaitTime(String waitTime){
	this.waitTime=waitTime;
	}
	public String getWaitTime(){
		return waitTime;
	}
	public void setConcerns(String concerns){
	this.concerns=concerns;
	}
	public String getConcerns(){
		return concerns;
	}
}

