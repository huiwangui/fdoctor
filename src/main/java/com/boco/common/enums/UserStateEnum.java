package com.boco.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户状态
 * 
 * @author Kelvin
 *
 */
public enum UserStateEnum {
	NORMAL(1, "启用"), 
	FORBIDDEN(2, "禁止");

	private Integer key;
	private String value;

	private UserStateEnum(Integer key, String value) {
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
		for (UserStateEnum enums : UserStateEnum.values()) {
			map.put(enums.getKey(), enums.getValue());
		}
		return map;
	}
}
