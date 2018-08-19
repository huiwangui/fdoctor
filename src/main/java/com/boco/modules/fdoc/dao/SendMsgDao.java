package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.SendMsgEntity;

@MyBatisDao
public interface SendMsgDao extends CrudDao<SendMsgEntity>{
	/**
	 * 
	 * getMsgList:(获取消息列表). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public List<SendMsgEntity> getMsgList(SendMsgEntity entity);
	/**
	 * 
	 * getMsgCount:(获取消息总数). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Integer getMsgCount(SendMsgEntity entity);
}
