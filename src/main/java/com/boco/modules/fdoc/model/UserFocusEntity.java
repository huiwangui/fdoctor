package com.boco.modules.fdoc.model;
import java.util.Date;

import com.boco.common.persistence.DataEntity;

   /**
    * t_user_focus 实体类
    * Wed Jul 27 16:29:43 CST 2016 tony.su
    */ 


public class UserFocusEntity extends DataEntity<UserFocusEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String uid;//用户编号
	private String type;//关注类型（1.医院/2 医生）
	private int objId;//关注对象ID
	private String objName;//关注对象名字
	private Date focusTime;//关注时间
	private Date cancelTime;//取消关注时间
	private String status;//关注状态（1：已关注 2：取消关注）
	private String remark;//备注
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
	public void setType(String type){
	this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setObjId(int objId){
	this.objId=objId;
	}
	public int getObjId(){
		return objId;
	}
	public void setObjName(String objName){
	this.objName=objName;
	}
	public String getObjName(){
		return objName;
	}
	public void setFocusTime(Date focusTime){
	this.focusTime=focusTime;
	}
	public Date getFocusTime(){
		return focusTime;
	}
	public void setCancelTime(Date cancelTime){
	this.cancelTime=cancelTime;
	}
	public Date getCancelTime(){
		return cancelTime;
	}
	public void setStatus(String status){
	this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setRemark(String remark){
	this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

