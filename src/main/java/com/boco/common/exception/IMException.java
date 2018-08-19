package com.boco.common.exception;

import org.apache.commons.logging.impl.Log4JLogger;

public class IMException extends RuntimeException{

	public IMException(){
		Log4JLogger l = new Log4JLogger("log4j.properties"); 
		l.error("即时通信操作失败");
	}
	
}
