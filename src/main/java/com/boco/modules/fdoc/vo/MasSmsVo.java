package com.boco.modules.fdoc.vo;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.sms.SmsRandom;
import com.boco.common.utils.PropertiesUtils;
import com.mascloud.sdkclient.Client;

/**
 * Description 移动云短信发送SMS
 * @author lzz
 * @date 2017年9月19日 上午10:37:02
 */
public class MasSmsVo {
	
	/*
	 * 移动Mas短信发送地址
	 */
	private static final String url="http://112.35.4.197:15000";
	/*
	 * 在云系统注册接口的账号
	 */
	private static final String userAccount="adminf";
	/*
	 * 密码
	 */
	private static final String password="yxqwjj12";
	/*
	 * 云系统单位
	 */
	private static final String ecname="绵阳市游仙区卫生和计划生育局";
	/*
	 * 单位的签名码
	 */
	private static String sign="9Gl6sHI1h";
	/*
	 * 需要发送短信的电话号码数组
	 */
	private String[] mobiles;
	/*
	 * 短信内容（未使用模板）
	 */
	private String smsContent;
	/*
	 * 扩展码
	 */
	private String addSerial;
	/*
	 * 短信优先级1-5，5级最高，默认1
	 */
	private Integer smsPriority;
	/*
	 * 发送数据批次号，32位唯一编码（不填为空）
	 */
	private String msgGroup;
	/*
	 * 是否需要上行（默认true）
	 */
	private boolean isMo;
	/*
	 * 短信验证码
	 */
	private String smsCode;
	/*
	 * 验证码超时时间（秒）
	 */
	private Integer timeout;
	/*
	 * 登陆
	 
	private static Client client=Client.getInstance();
	
	 * 登陆状态
	 
	private static boolean isLoggedin;*/
	
	//验证码构造器
	public MasSmsVo(String[] mobiles){
		this.mobiles=mobiles;
		this.addSerial="";
		this.smsPriority=1;
		this.msgGroup="";
		this.isMo=true;
		this.smsCode=SmsRandom.createRandom();
		this.timeout =Integer.parseInt((PropertiesUtils.getValue("sms_code_timeout")));
		this.smsContent="你的验证码为"+this.smsCode+"，有效期为"+this.timeout/60+"分钟，注意该验证码为重要信息，请勿泄漏！";
	}
	//自定义短信构造器
	public MasSmsVo(String[] mobiles,String smsContent){
		this.mobiles=mobiles;
		this.addSerial="";
		this.smsPriority=1;
		this.msgGroup="";
		this.isMo=true;
		this.smsContent=smsContent;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public static String getUrl() {
		return url;
	}


	public static String getUserAccount() {
		return userAccount;
	}

	public static String getPassword() {
		return password;
	}

	public static String getEcname() {
		return ecname;
	}

	public static String getSign() {
		return sign;
	}

	public static void setSign(String sign) {
		MasSmsVo.sign = sign;
	}

	public String[] getMobiles() {
		return mobiles;
	}

	public void setMobiles(String[] mobiles) {
		this.mobiles = mobiles;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getAddSerial() {
		return addSerial;
	}

	public void setAddSerial(String addSerial) {
		this.addSerial = addSerial;
	}

	public Integer getSmsPriority() {
		return smsPriority;
	}

	public void setSmsPriority(Integer smsPriority) {
		this.smsPriority = smsPriority;
	}

	public String getMsgGroup() {
		return msgGroup;
	}

	public void setMsgGroup(String msgGroup) {
		this.msgGroup = msgGroup;
	}

	public boolean isMo() {
		return isMo;
	}

	public void setMo(boolean isMo) {
		this.isMo = isMo;
	}

	/*public static Client getClient() {
		return client;
	}
	public static void setClient(Client client) {
		MasSmsVo.client = client;
	}
	public static boolean isLoggedin() {
		return isLoggedin;
	}
	public static void setLoggedin(boolean isLoggedin) {
		MasSmsVo.isLoggedin = isLoggedin;
	}

	static{
		isLoggedin = client.login( url, userAccount, password, ecname );
		
	}*/
	//发送验证码
	public String sendSmsCode() {
		Client client=Client.getInstance();
		boolean isLoggedin=false;
		while(!isLoggedin){
			isLoggedin= client.login( url, userAccount, password, ecname );
		}
		if(isLoggedin){
			int sendResult = 
				client.sendDSMS( this.mobiles, this.smsContent, this.addSerial, this.smsPriority, this.sign, this.msgGroup, this.isMo );
			if( sendResult == mobiles.length ){
				client.logout();
				return BusinessConstants.SUCCESS;
			}
		}
		return BusinessConstants.FAIL;		
		
	}
	
	//发送自定义验证码
	public  String sendSms(){
		Client client=Client.getInstance();
		boolean isLoggedin=false;
		while(!isLoggedin){
			isLoggedin= client.login( url, userAccount, password, ecname );
		}
		if(isLoggedin){
			int sendResult = 
				client.sendDSMS( this.mobiles, this.smsContent, this.addSerial, this.smsPriority, this.sign, this.msgGroup, this.isMo );
			if( sendResult == mobiles.length ){
				client.logout();
				return BusinessConstants.SUCCESS;
			}
		}
		return BusinessConstants.FAIL;	
	}
	
}
