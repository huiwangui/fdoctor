package com.boco.common.json;

/**
 * 返回对象父类
 * 
 * @author sufj
 *
 */
public class MessageResult {

	private int code;
	private String msg;
	private Object data;

	public MessageResult() {
	}

	public MessageResult(int code, String msg, Object data) {
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
