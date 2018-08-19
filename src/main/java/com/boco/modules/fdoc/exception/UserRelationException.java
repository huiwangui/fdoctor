package com.boco.modules.fdoc.exception;

public class UserRelationException  extends RuntimeException{
   /**
	 * 用户关系异常
	 */
	private static final long serialVersionUID = 1L;

    private Integer code;
   
	public UserRelationException(Integer code,String msg) {
		super(msg);
		this.code = code;
    }

	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
   
   
}
