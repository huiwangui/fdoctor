package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.SendMsgEntity;

public interface SendMsgService {
	/**
	 * 
	 * addMsg:(新增推送消息). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Integer addMsg(SendMsgEntity entity);
	/**
	 * 
	 * getMsgList:(获取消息列表). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public List<SendMsgEntity> getMsgList(SendMsgEntity entity);/**
	 * 
	 * getMsgCount:(获取消息总数). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Integer getMsgCount(SendMsgEntity entity);
}
