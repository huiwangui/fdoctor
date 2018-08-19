package com.boco.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum ApiSmsEnum {

	//系统级反馈码
	ONE((int) -1, "提交接口错误"),
	THREE((int) -3, "用户名或密码错误"),
	FOUR((int) -4, "用户名或密码错误"),
	FIVE((int) -5, "签名不正确（格式为： 短信内容……【签名内容】）签名一定要放在短信最后"),
	SEVEN((int) -7, "余额不足"),
	EIGHT((int)-8, "通道错误"),
	NINE((int)-9, "无效号码"),
	TEN((int)-10, "签名内容不符合长度"),
	ELEVEN((int)-11, "用户有效期过期"),
	TWELVE((int)-12, "黑名单"),
	THIRTEEN((int)-13, "语音验证码的 Amount 参数必须是整形字符串"),
	FOURTEEN((int)-14, "语音验证码的内容只能为数字"),
	FIFTEEN((int)-15, "语音验证码的内容最长为 6 位"),
	SIXTEEN((int)-16, "余额请求过于频繁，5 秒才能取余额一次"),
	SEVENTEEN((int)-17, "非法 IP"),
	TWENTYTHREE((int)-23, "解密失败")
	
	;

	private Integer key;

	private String value;

	private ApiSmsEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (ApiSmsEnum status : ApiSmsEnum.values()) {
			map.put(status.getKey(), status.getValue());
		}
		return map;
	}

}
