package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.BaseEntity;

public class SendMsgEntity extends BaseEntity<SendMsgEntity>{

	/**
    * 主键
    */
    private int id;

    /**
    * 消息标题
    */
    private String title;

    /**
    * 消息内容
    */
    private String content;

    /**
    * 用户Uid
    */
    private String uid;

    /**
    * 医生团队ID
    */
    private String docUserName;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 接收者：1.医生  2.用户
    */
    private Integer receiverType;

    /**
    * 消息内容类型：1.签约推送至居民端 2.签约请求推送至医生端 3.签约请求同意推送至居民端 4.签约请求拒绝推送至居民端 5.异常血压推送至医生端 6.异常血糖推送至医生端 7.健康评估推送至居民端 8.群发模板推送至居民端
    */
    private Integer pushInfoType;

    /**
    * 附带数据，json格式
    */
    private String data;
    
    public SendMsgEntity(String title,String content,Integer receiverType ,Integer pushInfoType, String data){
    	this.title = title;
    	this.content = content;
    	this.receiverType = receiverType;
    	this.pushInfoType = pushInfoType;
    	this.data = data;
    	this.createTime = new Date();
    }

    
    public SendMsgEntity(){
    	
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDocUserName() {
        return docUserName;
    }

    public void setDocUserName(String docUserName) {
        this.docUserName = docUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(Integer receiverType) {
        this.receiverType = receiverType;
    }

    public Integer getPushInfoType() {
        return pushInfoType;
    }

    public void setPushInfoType(Integer pushInfoType) {
        this.pushInfoType = pushInfoType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		
	}
}