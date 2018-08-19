package com.boco.common.schedule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.common.sms.SmsConsumer;

public class SystemInit extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(SystemInit.class);

	@Override
	public void init() throws ServletException {
		super.init();
		long start = System.currentTimeMillis();

		initSms();//启动短信配置
		long end = System.currentTimeMillis();
		logger.info("短信验证码后台通道已启动，耗时：" + (end - start) + " 毫秒");
	}
	
	public void initSms() {
		// Creating Producer and Consumer Thread
		SmsConsumer prodThread = new SmsConsumer();
		// Starting producer and Consumer thread
		prodThread.start();
	}
	
	@Override
	public void destroy() {
		logger.info("短信验证码后台通道已启动[系统服务停止]");
		super.destroy();
	}
	
}
