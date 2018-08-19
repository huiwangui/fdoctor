package com.boco.modules.fdoc.model;
import java.util.Date;

import com.boco.common.persistence.DataEntity;

   /**
    * t_hospital_comment 实体类
    * Thu Jul 28 10:36:04 CST 2016 sun
    */ 


public class HospitalCommentEntity extends DataEntity<HospitalCommentEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String uid;//用户编号
	private String nickname;//评论人昵称
	private String img;//评论人头像
	private int docId;//医院ID
	private int bookingId; //订单ID
	private int objId;//评论的对象ID，如果回复的则引用主键ID
	private String content;//评论内容
	private int type;//1：评论 2：回复
	private String rUid;//当回复时，回复人ID
	private String rNickname;//当回复时，回复人昵称
	private String rImg;//当回复时，回复人头像
	private Date createTime;//创建时间
	private Date updateTime;//修改时间
	private String stars;
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
	public void setNickname(String nickname){
	this.nickname=nickname;
	}
	public String getNickname(){
		return nickname;
	}
	
	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public void setObjId(int objId){
	this.objId=objId;
	}
	public int getObjId(){
		return objId;
	}
	public void setContent(String content){
	this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setType(int type){
	this.type=type;
	}
	public int getType(){
		return type;
	}
	public void setCreateTime(Date createTime){
	this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setUpdateTime(Date updateTime){
	this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getrUid() {
		return rUid;
	}
	public void setrUid(String rUid) {
		this.rUid = rUid;
	}
	public String getrNickname() {
		return rNickname;
	}
	public void setrNickname(String rNickname) {
		this.rNickname = rNickname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getrImg() {
		return rImg;
	}
	public void setrImg(String rImg) {
		this.rImg = rImg;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	
}

