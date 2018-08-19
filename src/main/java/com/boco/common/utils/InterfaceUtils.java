package com.boco.common.utils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;

public class InterfaceUtils {
	/**
	 * 调用卫计委接口方法
	 * @param TradeCode 接口名称
	 * @param dataJson  输入参数
	 * @return
	 * @author h
	 */
	public static String invokeInterface(String TradeCode, String dataJson){
		try {			
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://61.157.75.21:11004/webservice.asmx/HIS_Interface");
			Form form = new Form();
			form.param("TradeCode", TradeCode);
			form.param("InputParameter", dataJson);
			javax.ws.rs.core.Response response = target.request().post(
					Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
			String respStr = response.readEntity(String.class);
			Document document = DocumentHelper.parseText(respStr);
			Element root = document.getRootElement();
			String data = root.getText();
			System.out.println(data);			 
		    return data;				 
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		} 
	}
}
