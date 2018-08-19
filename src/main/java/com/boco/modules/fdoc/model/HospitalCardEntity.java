package com.boco.modules.fdoc.model;

   /**
    * t_hospital_card 实体类
    * Thu Jul 28 10:26:34 CST 2016 sun
    */ 


public class HospitalCardEntity{
	private int id;
	private String uid;//用户id
	private String num;//卡号
	private String hospital;//卡归属医院
	private String owner;//卡归属人
	private String ownerTel;//卡归属人联系方式
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setUid(String uid){
	this.uid=uid;
	}
	public String getUid(){
		return uid;
	}
	public void setNum(String num){
	this.num=num;
	}
	public String getNum(){
		return num;
	}
	public void setHospital(String hospital){
	this.hospital=hospital;
	}
	public String getHospital(){
		return hospital;
	}
	public void setOwner(String owner){
	this.owner=owner;
	}
	public String getOwner(){
		return owner;
	}
	public void setOwnerTel(String ownerTel){
	this.ownerTel=ownerTel;
	}
	public String getOwnerTel(){
		return ownerTel;
	}
}

