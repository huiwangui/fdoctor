package com.boco.common.sms;

import java.util.Map;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.utils.PropertiesUtils;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


public class SmsVo {

	private String mobile;
	
	private Integer timeout;
	
	private String url;
	
	private String appKey;
	
	private String appSecret;
	
	private String sendItem;
	
	private String smsCode;
	
	private String smsFreeSignName;
	
	private String smsTemplateCode;
	
	public SmsVo(String mobile, String sendItem){
		this.mobile = mobile;
		this.timeout = Integer.parseInt((PropertiesUtils.getValue("sms_code_timeout")));
		this.url = PropertiesUtils.getValue("sms_code_url");
		if (BusinessConstants.SMS_SEND_ITEM_DOC.equals(sendItem)) {
			this.appKey = PropertiesUtils.getValue("sms_code_doc_appkey");
			this.appSecret = PropertiesUtils.getValue("sms_code_doc_appSecret");
		}else {
			this.appKey = PropertiesUtils.getValue("sms_code_user_appkey");
			this.appSecret = PropertiesUtils.getValue("sms_code_user_appSecret");
		}
		this.sendItem = sendItem;
		this.smsCode = SmsRandom.createRandom();
		this.smsFreeSignName = PropertiesUtils.getValue("sms_sms_free_sign_name");
		this.smsTemplateCode = PropertiesUtils.getValue("sms_template_code");
	}

	
	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getSmsCode() {
		return smsCode;
	}


	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}


	public Integer getTimeout() {
		return timeout;
	}


	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getSendItem() {
		return sendItem;
	}

	public void setSendItem(String sendItem) {
		this.sendItem = sendItem;
	}
	
	public String getSmsFreeSignName() {
		return smsFreeSignName;
	}


	public void setSmsFreeSignName(String smsFreeSignName) {
		this.smsFreeSignName = smsFreeSignName;
	}


	public String getSmsTemplateCode() {
		return smsTemplateCode;
	}


	public void setSmsTemplateCode(String smsTemplateCode) {
		this.smsTemplateCode = smsTemplateCode;
	}


	public String sendSmsMsg(){
		try {
			TaobaoClient client = new DefaultTaobaoClient(this.url, this.appKey, this.appSecret);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setSmsType("normal");
			req.setSmsFreeSignName(this.smsFreeSignName);
			req.setSmsParamString("{\"code\":\""+this.smsCode+"\"}");
			req.setRecNum(this.mobile);
			req.setSmsTemplateCode(this.smsTemplateCode);
			
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			return rsp.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return BusinessConstants.FAIL;
		}
		
	}
}
