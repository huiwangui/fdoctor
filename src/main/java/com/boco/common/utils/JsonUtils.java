package com.boco.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.http.HttpSessionUtils;
import com.boco.common.json.MessageResult;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonUtils {

	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private static ObjectMapper mapper = new ObjectMapper();

//	static {
//		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//			@Override
//			public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
//					throws IOException, JsonProcessingException {
//				System.out.println(value.getClass());
//				jgen.writeString("");// 为空的返回空字符串。这里需要注意的问题是，封装的基础数据类型、list、map等如果返回null，转化为空字符会出现什么问题
//			}
//		});
//	}
	/**
	 * 获取json串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsons(Object obj) {
		String result = null;
		try {
			// 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
			// Include.Include.ALWAYS 默认
			// Include.NON_DEFAULT 属性为默认值不序列化
			// Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
			// Include.NON_NULL 属性为NULL 不序列化
			mapper.setSerializationInclusion(Include.ALWAYS);
			result = mapper.writeValueAsString(obj);

			// 如果是jsonp请求则添加jsonp支持
			HttpServletRequest request = HttpSessionUtils.getRequest();
			String callback = request.getParameter("jsonpCallback");
			if (!StringUtils.isEmpty(callback)) {
				result = callback + "(" + result + ")";
			}
		} catch (JsonProcessingException e) {
			logger.error("json对象处理异常：" + obj);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取json串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJson(Object obj) {
		String result = null;
		try {
			// 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
			// Include.Include.ALWAYS 默认
			// Include.NON_DEFAULT 属性为默认值不序列化
			// Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
			// Include.NON_NULL 属性为NULL 不序列化
			//mapper.setSerializationInclusion(Include.NON_NULL);
			mapper.setSerializationInclusion(Include.ALWAYS);
			result = mapper.writeValueAsString(obj);

			// 如果是jsonp请求则添加jsonp支持
			HttpServletRequest request = HttpSessionUtils.getRequest();
			String callback = request.getParameter("jsonpCallback");
			if (!StringUtils.isEmpty(callback)) {
				result = callback + "(" + result + ")";
			}
		} catch (JsonProcessingException e) {
			logger.error("json对象处理异常：" + obj);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取json串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJson(Object obj,String include) {
		String result = null;
		try {
			// 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
			// Include.Include.ALWAYS 默认
			// Include.NON_DEFAULT 属性为默认值不序列化
			// Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
			// Include.NON_NULL 属性为NULL 不序列化
			if (BusinessConstants.JSON_NOT_EMPTY.equals(include)) {
				mapper.setSerializationInclusion(Include.NON_EMPTY);
			}else if (BusinessConstants.JSON_ALL.equals(include)) {
				mapper.setSerializationInclusion(Include.ALWAYS);
			}else {
				mapper.setSerializationInclusion(Include.NON_NULL);
			}
			result = mapper.writeValueAsString(obj);

			// 如果是jsonp请求则添加jsonp支持
			HttpServletRequest request = HttpSessionUtils.getRequest();
			String callback = request.getParameter("jsonpCallback");
			if (!StringUtils.isEmpty(callback)) {
				result = callback + "(" + result + ")";
			}
		} catch (JsonProcessingException e) {
			logger.error("json对象处理异常：" + obj);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据传入对象和json串，返回解析对象
	 * 
	 * @param body
	 * @param valueType
	 * @return
	 */
	public static <T> T getObject(String body, Class<T> valueType) {
		try {
			if (!StringUtils.isEmpty(body)) {// H5页面提交，POST时进行了转码，后台对于做解码
				if (body.indexOf("%7B%22") != -1) {// 花括号的转码 {
					body = java.net.URLDecoder.decode(body, "UTF-8");
				}
			}
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);// 忽略未知的类型
			// mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
			// true);
			return mapper.readValue(body, valueType);
		} catch (JsonParseException e) {
			logger.error("json解析异常：" + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error("json映射异常：" + e.getMessage());
		} catch (IOException e) {
			logger.error("json IO异常：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 根据传入对象和json串，返回解析对象
	 * 
	 * @param body
	 * @param valueType
	 * @return
	 */
	public static <T> T getObject(String body, Class<T> valueType, boolean ignore) {
		try {
			if (ignore) {
				// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
				mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);// 忽略未知的类型
			}
			return mapper.readValue(body, valueType);
		} catch (JsonParseException e) {
			logger.error("json解析异常：" + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error("json映射异常：" + e.getMessage());
		} catch (IOException e) {
			logger.error("json IO异常：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 根据传入对象和json串，返回解析list对象
	 * 
	 * @param body
	 * @param valueType
	 * @return
	 */
	@SuppressWarnings("all")
	public static <T> List<T> getObjectList(String body, Class<T> valueType) {
		try {
			if (!StringUtils.isEmpty(body)) {// H5页面提交，POST时进行了转码，后台对于做解码
				if (body.indexOf("%7B%22") != -1) {// 花括号的转码 {
					body = java.net.URLDecoder.decode(body, "UTF-8");
				}
			}
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);// 忽略未知的类型
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, valueType);
			List<T> list = (List<T>) mapper.readValue(body, javaType);
			return list;
		} catch (JsonParseException e) {
			logger.error("json解析异常：" + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error("json映射异常：" + e.getMessage());
		} catch (IOException e) {
			logger.error("json IO异常：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 将字符串转换为MessageResult格式串
	 * 
	 * @param result
	 * @return
	 */
	public static String getMr(ApiStatusEnum status, Object data) {
		MessageResult mr = new MessageResult(status.getKey(), status.getValue(), data);
		String ret = JsonUtils.getJson(mr);
		return ret;
	}

	/**
	 * 返回MessageResult格式
	 * 
	 * @param status
	 * @param data
	 * @return
	 */
	public static ModelAndView getMrForMav(ApiStatusEnum status, Object data) {
		ModelAndView mav = new ModelAndView();
		MessageResult mr = new MessageResult(status.getKey(), status.getValue(), data);
		mav.addObject(mr);
		return mav;
	}

	/**
	 * 将字符串转换为MessageResult格式串
	 * 
	 * @param result
	 * @return
	 */
	public static String getMr(ApiStatusEnum status, String message, Object data) {
		MessageResult mr = new MessageResult(status.getKey(), message, data);
		String ret = JsonUtils.getJson(mr);
		return ret;
	}

	/**
	 * 返回MessageResult格式
	 * 
	 * @param status
	 * @param data
	 * @return
	 */
	public static ModelAndView getMrForMav(ApiStatusEnum status, String message, Object data) {
		ModelAndView mav = new ModelAndView();
		MessageResult mr = new MessageResult(status.getKey(), message, data);
		mav.addObject(mr);
		return mav;
	}

	/**
	 * 获取json串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonFormat(Object obj) {
		String result = null;
		try {
			mapper.setSerializationInclusion(Include.NON_EMPTY);
			 mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
						 true);
			result = mapper.writeValueAsString(obj);

		} catch (JsonProcessingException e) {
			logger.error("json对象处理异常：" + obj);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * getSingleJsonMap:(返回单层json解析之后的map). <br/>
	 * @author q
	 * @param commentBody
	 * @return
	 */
	public static Map<String, String> getSingleJsonMap(String commentBody) {
		HashMap jsonMap = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES,true);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
			jsonMap = mapper.readValue(commentBody,HashMap.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
	}
	
	/**
	 * 
	 * getObjectJsonMap:(返回复杂json解析之后的map). <br/>
	 * @author q
	 * @param commentBody
	 * @return
	 */
	public static Map<String, Object> getObjectJsonMap(String commentBody) {
		HashMap jsonMap = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES,true);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
			jsonMap = mapper.readValue(commentBody,HashMap.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
	}
	
	/**
	 * 
	 * getPageJson:(封装分页查询返回的json). <br/>
	 * @author q
	 * @param count
	 * @param pageSize
	 * @param data
	 * @return
	 */
	public static String getPageJson(Integer count,Integer pageSize,Object data){
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("code", 200);
		map.put("msg", "成功");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (pageSize == 0) {
			dataMap.put("pageTotal", 0);
		}else {
			if (count % pageSize == 0) {
				dataMap.put("pageTotal", count / pageSize);
			}else {
				dataMap.put("pageTotal", count / pageSize + 1);
			}
		}
		dataMap.put("list", data);
		map.put("data", dataMap);
		
		
		return JsonUtils.getJsonFormat(map);
	}
	
	/**
	 * 
	 * getPageJson:(封装分页查询返回的json). <br/>
	 * @author q
	 * @param count
	 * @param pageSize
	 * @param data
	 * @return
	 */
	public static String getPageJsonWithTotal(Integer count,Integer pageSize,Object data){
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("code", 200);
		map.put("msg", "成功");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (pageSize == 0) {
			dataMap.put("pageTotal", 0);
		}else {
			if (count % pageSize == 0) {
				dataMap.put("pageTotal", count / pageSize);
			}else {
				dataMap.put("pageTotal", count / pageSize + 1);
			}
		}
		dataMap.put("total", count);
		dataMap.put("list", data);
		map.put("data", dataMap);
		
		
		return JsonUtils.getJsonFormat(map);
	}
	/**
	 * 
	 * getPageJson:(封装分页查询返回的json). <br/>
	 * @author q
	 * @param count
	 * @param pageSize
	 * @param data
	 * @return
	 */
	public static String getPageAndItemSumJson(Integer count,Integer pageSize,Object data){
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("code", 200);
		map.put("msg", "成功");
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("itemSum", count);
		if (pageSize == 0) {
			dataMap.put("pageTotal", 0);
		}else {
			if (count % pageSize == 0) {
				dataMap.put("pageTotal", count / pageSize);
			}else {
				dataMap.put("pageTotal", count / pageSize + 1);
			}
		}
		dataMap.put("list", data);
		map.put("data", dataMap);
		
		
		return JsonUtils.getJsonFormat(map);
	}

	public static void main(String[] args) {
		/*
		 * String wei = getJsonFormat(new GoodsCommentVo());
		 * System.out.println(wei);
		 */
	}
}
