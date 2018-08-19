package com.boco.modules.fdoc.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.boco.common.jedis.JedisUtils;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.service.CommonService;
import com.boco.modules.fdoc.vo.MasSmsVo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/smsCode", produces = "text/json;charset=UTF-8")
public class SmsCodeApi {

	@Resource
	CommonService commonService;
	
	@RequestMapping(value = "/sendSmsCodeToDoc", method = RequestMethod.GET)
	@ApiOperation(value = "发送手机验证码（医生端）", notes = "{'mobile':'手机号'}", response = BaseJsonVo.class)
	public String sendSmsCodeToDoc(@RequestParam String mobile, HttpServletRequest request){
		
		List<String> list=new ArrayList<String>();
		list.add(mobile);
		
		String[] mobiles=new String[list.size()];
		for(int i=0;i<list.size();i++){
		mobiles[i]=list.get(i);
		}
		MasSmsVo vo=new MasSmsVo(mobiles);
		JedisUtils.set("sms"+mobile, vo.getSmsCode(), vo.getTimeout());
		vo.sendSmsCode();
		
		/*SmsVo vo = new SmsVo(mobile, BusinessConstants.SMS_SEND_ITEM_DOC);
		JedisUtils.set("sms"+mobile, vo.getSmsCode(), vo.getTimeout());
		vo.sendSmsMsg();*/
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/sendSmsCodeToUser", method = RequestMethod.GET)
	@ApiOperation(value = "发送手机验证码（居民端）", notes = "{'mobile':'手机号'}", response = BaseJsonVo.class)
	public String sendSmsCodeToUser(@RequestParam String mobile, HttpServletRequest request){
		
		List<String> list=new ArrayList<String>();
		list.add(mobile);
		
		String[] mobiles=new String[list.size()];
		for(int i=0;i<list.size();i++){
		mobiles[i]=list.get(i);
		}
		MasSmsVo vo=new MasSmsVo(mobiles);
		JedisUtils.set("sms"+mobile, vo.getSmsCode(), vo.getTimeout());
		vo.sendSmsCode();
		
		/*SmsVo vo = new SmsVo(mobile, BusinessConstants.SMS_SEND_ITEM_USER);
		JedisUtils.set("sms"+mobile, vo.getSmsCode(), vo.getTimeout());
		vo.sendSmsMsg();*/
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/getSmsCode", method = RequestMethod.GET)
	@ApiOperation(value = "验证码测试", notes = "{'mobile':'手机号'}", response = BaseJsonVo.class)
	public String getSmsCode(@RequestParam String mobile, HttpServletRequest request){
		String smsCode = JedisUtils.get("sms"+mobile);
		return JsonUtils.getJson(BaseJsonVo.success(smsCode));
	}
}
