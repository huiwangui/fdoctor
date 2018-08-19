package com.boco.modules.fdoc.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.persistence.Page;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.SendMsgEntity;
import com.boco.modules.fdoc.service.SendMsgService;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pushMsg", produces = "text/json;charset=UTF-8")
public class PushMsgApi {
	@Resource
	SendMsgService sendMsgService;
	
	@RequestMapping(value = "/getMsgListByDoctor", method = RequestMethod.GET)
	@ApiOperation(value = "获取推送消息列表（医生端）", notes = "{'userName':'医生登录用户名'}")
	public String getMsgListByDoctor(@RequestParam String userName, @RequestParam Integer pageSize,
			@RequestParam Integer pageNo){
		// 封装查询参数
		SendMsgEntity entity = new SendMsgEntity();
		entity.setDocUserName(userName);
		Integer msgCount = sendMsgService.getMsgCount(entity);
		// 封装分页参数
		Page<SendMsgEntity> page = new Page<SendMsgEntity>(pageNo,pageSize, msgCount);
		entity.setPage(page);
		List<SendMsgEntity> msgList = sendMsgService.getMsgList(entity);
		return JsonUtils.getPageJson(msgCount, pageSize, msgList);
	
	}
	
	@RequestMapping(value = "/getMsgListByUser", method = RequestMethod.GET)
	@ApiOperation(value = "获取推送消息列表（居民端）", notes = "{'uid':'用户uid'}")
	public String getMsgListByUser(@RequestParam String uid, @RequestParam Integer pageSize,
			@RequestParam Integer pageNo){
		// 封装查询参数
		SendMsgEntity entity = new SendMsgEntity();
		entity.setUid(uid);
		Integer msgCount = sendMsgService.getMsgCount(entity);
		// 封装分页参数
		Page<SendMsgEntity> page = new Page<SendMsgEntity>(pageNo,pageSize, msgCount);
		entity.setPage(page);
		List<SendMsgEntity> msgList = sendMsgService.getMsgList(entity);
		return JsonUtils.getPageJson(msgCount, pageSize, msgList);
	}
}
