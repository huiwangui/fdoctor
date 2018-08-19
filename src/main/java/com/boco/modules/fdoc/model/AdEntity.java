package com.boco.modules.fdoc.model;
import java.util.Date;

import com.boco.common.persistence.DataEntity;

   /**
    * t_ad 实体类
    * Thu Jul 28 10:05:29 CST 2016 sun
    */ 
public class AdEntity extends DataEntity<AdEntity>{
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;//广告标题
	private String tenant;//广告商户（可拓展）
	private String url;//广告跳转URL
	private String img;//广告image
	private String loc;//广告位置（1. banner，如需做广告收费，则需要 细分表）
	private Date addTime;//添加时间
	private String addPerson;//添加人
	private Date updateTime;
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setTitle(String title){
	this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setTenant(String tenant){
	this.tenant=tenant;
	}
	public String getTenant(){
		return tenant;
	}
	public void setUrl(String url){
	this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setImg(String img){
	this.img=img;
	}
	public String getImg(){
		return img;
	}
	public void setLoc(String loc){
	this.loc=loc;
	}
	public String getLoc(){
		return loc;
	}
	public void setAddTime(Date addTime){
	this.addTime=addTime;
	}
	public Date getAddTime(){
		return addTime;
	}
	public void setAddPerson(String addPerson){
	this.addPerson=addPerson;
	}
	public String getAddPerson(){
		return addPerson;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

