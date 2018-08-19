package com.boco.common.utils;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * ClassName: RestfulUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: 发送restful请求的工具类，使用jersey框架. <br/>
 * date: 2016年12月23日 上午9:17:24 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class RestfulUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private static ObjectMapper mapper = new ObjectMapper();
	
	private static int timeout=Integer.parseInt(PropertiesUtils.getValue("publicHealth.timeout"));//单位毫秒
	/**
	 * 
	 * sendPostRequest:(发送post请求). <br/>
	 * @author q
	 * @param url
	 * @param form
	 * @return
	 */
	public static String sendPostRequest(String url,Form form){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
        Response response = target.request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED));
		return response.readEntity(String.class);
	}
	/**
	 * 
	 * sendGetRequest:(发送get请求). <br/>
	 * @author q
	 * @param url
	 * @return
	 */
	public static String sendGetRequest(String url){
		
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
        String response = target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
		return response;
	}
	/**
	 * 
	 * getPublicData:(获取卫计委接口数据). <br/>
	 * TradeCode : 卫计委接口编号
	 * InputParameterMap : 参数map
	 * @author q
	 * @return
	 * @throws DocumentException 
	 */
	public static String getPublicData(String TradeCode,Map<String, Object> InputParameterMap){
		try {
			mapper.setSerializationInclusion(Include.ALWAYS);
			 mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
						 true);
			 
			String InputParameter = mapper.writeValueAsString(InputParameterMap);
			ClientConfig cc = new ClientConfig();  
			RequestConfig  req=RequestConfig.custom().setConnectTimeout(timeout).build();
			cc.connectorProvider(new ApacheConnectorProvider());
			cc.property(ApacheClientProperties.REQUEST_CONFIG, req); 
			Client client = ClientBuilder.newClient(cc);
			//Client client = ClientBuilder.newClient();
			//WebTarget target = client.target("http://61.157.75.21:11004/webservice.asmx/HIS_Interface");
			//WebTarget target = client.target("http://11.21.1.11:8188/WebService.asmx/HIS_Interface");//正式库地址
			String hisUrl=PropertiesUtils.getValue("HIS_Interface");
			WebTarget target = client.target(hisUrl);
			Form form = new Form();
			form.param("TradeCode", TradeCode);
			form.param("InputParameter", InputParameter);
			javax.ws.rs.core.Response response = target.request().post(
					Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
			String respStr = response.readEntity(String.class);
			
			Document document = DocumentHelper.parseText(respStr);
			Element root = document.getRootElement();
			String data = root.getText();
			
			return data;
		} catch (Exception e) {
			if(e instanceof ProcessingException||e instanceof ConnectTimeoutException||e instanceof SocketTimeoutException||e instanceof SocketException){
				Map<String,Object> timeoutmap =new HashMap<String,Object>();
				timeoutmap.put("Result", 0);
				timeoutmap.put("Msg", "公卫接口连接请求超时");
				return JsonUtils.getJson(timeoutmap);
			}
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
		
	}
}
