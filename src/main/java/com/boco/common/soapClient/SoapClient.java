package com.boco.common.soapClient;

import java.net.URL;

import javax.xml.namespace.QName;
/**
 * soap接口客户端类，用于调用双向转诊接口
 * @author q
 *
 */
public class SoapClient {
	
	private static final QName SERVICE_NAME = new QName(
			"http://webservice.fjzl.wondersgroup.com/", "ZhuanzhenImplService");
	
	private SoapClient(){
		
	}
	
	private static final SoapClient client = new SoapClient();
	
	public static SoapClient getInstance(){
		return client;
	}
	
	/**
	 * 获取门诊转诊列表
	 * @param commonParam
	 * @param apiParam
	 * @return
	 */
	public String outpatientList(String commonParam, String apiParam){
		URL wsdlURL = ZhuanzhenImplService.WSDL_LOCATION;
		ZhuanzhenImplService ss = new ZhuanzhenImplService(wsdlURL,
				SERVICE_NAME);
		ZhuanzhenInterface port = ss.getZhuanzhenImplPort();
		
		java.lang.String _outpatientList__return = port.outpatientList(
				commonParam, apiParam);
		return _outpatientList__return;
	}
	
	/**
	 * 门诊转诊申请
	 * @param commonParam
	 * @param apiParam
	 * @return
	 */
	public String outpatientApply(String commonParam, String apiParam){
		URL wsdlURL = ZhuanzhenImplService.WSDL_LOCATION;
		ZhuanzhenImplService ss = new ZhuanzhenImplService(wsdlURL,
				SERVICE_NAME);
		ZhuanzhenInterface port = ss.getZhuanzhenImplPort();
		java.lang.String _outpatientApply__return = port.outpatientApply(
				commonParam, apiParam);
		return _outpatientApply__return;
		
	}
	
	/**
	 * 住院转诊申请
	 * @param commonParam
	 * @param apiParam
	 * @return
	 */
	public String hospitalizationApply(String commonParam, String apiParam){
		URL wsdlURL = ZhuanzhenImplService.WSDL_LOCATION;
		ZhuanzhenImplService ss = new ZhuanzhenImplService(wsdlURL,
				SERVICE_NAME);
		ZhuanzhenInterface port = ss.getZhuanzhenImplPort();
		java.lang.String _inpatientApply__return = port.inpatientApply(
				commonParam, apiParam);
		return _inpatientApply__return;
	}
}
