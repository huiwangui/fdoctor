package com.boco.common.sms;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boco.common.utils.PropertiesUtils;

/**
 * 短信消费队列
 * 
 * @author tonysu
 *
 */
@SuppressWarnings(value = "all")
public class SmsConsumer extends Thread {

	private Logger logger = LoggerFactory.getLogger(SmsConsumer.class);

	// apikey
	private static String apikey = PropertiesUtils.getValue("yp_sms_apikey");

	public void run() {
		while (true) {
			
			try {
				wait(2000L);//休息两秒 wait（100L）意思为：不占用CPU，线程等待100毫秒
				if (SmsQueueList.sharedQueue.size() > 0) {
					SmsVo smsVo = SmsQueueList.sharedQueue.take();
					logger.info(smsVo.toString());
					String sms_code_on = PropertiesUtils.getValue("sms_code_on");
					boolean on = Boolean.parseBoolean(sms_code_on);
					if (on) {// 开启短信验证码短信
						// 设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
//						String response = JavaSmsApi.sendSms(apikey, smsVo.getContent(), smsVo.getPhone());
//						logger.info(response);
					}
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
	}

}
