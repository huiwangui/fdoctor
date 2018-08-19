package com.boco.common.exception;

import org.apache.commons.logging.impl.Log4JLogger;

/**
 * 通用运行时异常，用于回滚数据库等
 * @author q
 *
 */
public class CommonException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommonException(){
		Log4JLogger l = new Log4JLogger("log4j.properties"); 
		l.error("操作失败");
	}

}
