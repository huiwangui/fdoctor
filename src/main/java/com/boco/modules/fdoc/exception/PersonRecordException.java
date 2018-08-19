package com.boco.modules.fdoc.exception;
/**
 * 新增居民异常，在调用卫计委接口失败时抛出
 * @author q
 *
 */
public class PersonRecordException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PersonRecordException(String message) {
		super(message);		 
	}
}
