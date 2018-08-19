package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.SendMsgDao;
import com.boco.modules.fdoc.model.SendMsgEntity;
import com.boco.modules.fdoc.service.SendMsgService;

@Service
public class SendMsgServiceImpl implements SendMsgService{
	@Resource
	SendMsgDao sendMsgDao;

	@Override
	public Integer addMsg(SendMsgEntity entity) {
		return sendMsgDao.insert(entity);
	}

	@Override
	public List<SendMsgEntity> getMsgList(SendMsgEntity entity) {
		entity.setCreateTime(new Date());
		return sendMsgDao.getMsgList(entity);
	}

	@Override
	public Integer getMsgCount(SendMsgEntity entity) {
		return sendMsgDao.getMsgCount(entity);
	}

}
