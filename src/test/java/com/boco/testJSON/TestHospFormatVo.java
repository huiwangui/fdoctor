package com.boco.testJSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.vo.HospitalVo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TestHospFormatVo {
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
					throws IOException, JsonProcessingException {
				jgen.writeString("");// 为空的返回空字符串。这里需要注意的问题是，封装的基础数据类型、list、map等如果返回null，转化为空字符会出现什么问题
			}
		});
	}
	public static String getJson(Object obj) {
		String result = null;
		try {
			// 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
			// Include.Include.ALWAYS 默认
			// Include.NON_DEFAULT 属性为默认值不序列化
			// Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
			// Include.NON_NULL 属性为NULL 不序列化
			mapper.setSerializationInclusion(Include.NON_NULL);
			result = mapper.writeValueAsString(obj);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
