package com.boco.modules.fdoc.vo;

import java.util.Date;

public class CommentVo {
	private int id;
	private String uid;// 用户编号
	private String nickname;// 评论人昵称
	private String content;// 评论内容
	private int type;// 1：评论，2：回复
	private String rUid;// 当回复时，回复人ID
	private String rNickname;// 当回复时，回复人昵称
	private Date createTime;// 创建时间
	private Date updateTime;// 修改时间

	private int docId;//医生ID
	private int hospId;//医生ID
	
	private int objId;// 评论的对象ID，如果回复的则引用主键ID
	
	private Date objType;// 评论对象类型:医院或医生

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}

	public Date getObjType() {
		return objType;
	}

	public void setObjType(Date objType) {
		this.objType = objType;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public int getHospId() {
		return hospId;
	}

	public void setHospId(int hospId) {
		this.hospId = hospId;
	}

}
