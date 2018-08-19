package com.boco.modules.boco;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.PropertiesUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimCustmsgPushRequest;
import com.taobao.api.request.OpenimCustmsgPushRequest.CustMsg;
import com.taobao.api.request.OpenimImmsgPushRequest;
import com.taobao.api.request.OpenimImmsgPushRequest.ImMsg;
import com.taobao.api.request.OpenimTribeSendmsgRequest;
import com.taobao.api.request.OpenimTribeSendmsgRequest.TribeMsg;
import com.taobao.api.request.OpenimTribeSendmsgRequest.User;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.response.OpenimCustmsgPushResponse;
import com.taobao.api.response.OpenimImmsgPushResponse;
import com.taobao.api.response.OpenimTribeSendmsgResponse;
import com.taobao.api.response.OpenimUsersAddResponse;

public class Imtest {
	private static String url = PropertiesUtils.getValue("im_url");
	private static String appKey = PropertiesUtils.getValue("im_appkey");
	private static String appSecret = PropertiesUtils.getValue("im_appSecret");  
	@Test
	public void sendMessage() throws Exception{		
		TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
		OpenimCustmsgPushRequest req = new OpenimCustmsgPushRequest();
		CustMsg obj1 = new CustMsg();
		obj1.setFromUser("napox3");//napox3系统管理员
		obj1.setToAppkey(appKey);
		List<String> list = new ArrayList<String>();
		list.add("fdoctor");
		obj1.setToUsers(list);
		obj1.setSummary("客户端最近消息里面显示的消息摘要");
		obj1.setData("push payload");
		obj1.setAps("{\"alert\":\"ios apns push\"}");
		obj1.setApnsParam("apns推送的附带数据");
		obj1.setInvisible(0L);
		obj1.setFromNick("系统消息");
		obj1.setFromTaobao(0L);
		req.setCustmsg(obj1);
		OpenimCustmsgPushResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	}
	
	@Test
	public void sendSysMessage() throws Exception{		
		TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
		OpenimImmsgPushRequest req = new OpenimImmsgPushRequest();
		ImMsg obj1 = new ImMsg();
		obj1.setFromUser("系统管理员");
		List<String> list = new ArrayList<String>();
		list.add("fdoctor");
		obj1.setToUsers(list);
		obj1.setMsgType(0L);
		obj1.setContext("这个一个文本消息");
		//obj1.setToAppkey("0");
		obj1.setMediaAttr("");
		obj1.setFromTaobao(0L);
		req.setImmsg(obj1);
		OpenimImmsgPushResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	} 
	 
}
