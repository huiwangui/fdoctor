package com.boco.common.push;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.boco.common.utils.JsonUtils;

/**
 * 
 * ClassName: 封装推送参数 <br/>
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class PushUtils {
	public static Map<String, Object> packPushParam(Integer pushType, Map<String, Object> paramMap){
		paramMap.put("pushTime", new Date().getTime());
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("type", pushType);
		returnMap.put("data", paramMap);
		return returnMap;
	}
}
