package com.boco.common.json;

import java.util.HashMap;
import java.util.Map;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;

/**
 * 基础返回实体
 * 
 * @author sufj
 *
 */
public class BaseJsonVo {

	private int code;
	private String msg;
	private Object data;

	/**
	 * 成功
	 * 
	 * @param value
	 * @return
	 */
	public static BaseJsonVo success(Object value) {
		return new BaseJsonVo(ApiStatusEnum.SUCCESS.getKey(), ApiStatusEnum.SUCCESS.getValue(), value);
	}
	/**
	 * 失败
	 * @param value
	 * @return
	 */
	public static BaseJsonVo error(Object value) {
		return new BaseJsonVo(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue(), value);
	}
	/**
	 * 成功 只返回影响行数
	 * 用于 更新或者删除，返回影响的行数
	 * eg {"rowNum":1}
	 * 
	 * @param i
	 * @return
	 */
	public static BaseJsonVo success(int i) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(BusinessConstants.ROW_NUM, i);
		return new BaseJsonVo(ApiStatusEnum.SUCCESS.getKey(), ApiStatusEnum.SUCCESS.getValue(), map);
	}

	/**
	 * 未知错误
	 * 
	 * @return
	 */
	public static BaseJsonVo error() {
		return new BaseJsonVo(ApiStatusEnum.ERROR_CODE.getKey(), ApiStatusEnum.ERROR_CODE.getValue());
	}

	public static BaseJsonVo empty(int key, String msg) {
		return new BaseJsonVo(key, msg);
	}

	public static BaseJsonVo error(ApiStatusEnum enums) {
		return new BaseJsonVo(enums.getKey(), enums.getValue());
	}

	public static BaseJsonVo error(ApiStatusEnum enums, String value) {
		return new BaseJsonVo(enums.getKey(), value);
	}

	public BaseJsonVo() {
		super();
	}

	public BaseJsonVo(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public BaseJsonVo(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseJsonVo [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
