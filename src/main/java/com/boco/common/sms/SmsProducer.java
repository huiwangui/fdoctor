package com.boco.common.sms;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 * @author Kelvin
 * 短信生产队列
 */
@SuppressWarnings(value = "all")
public class SmsProducer extends Thread {

	public void run() {
		for (int i = 1; i < 10000; i++) {
			try {
				System.out.println("当前进去1 个人，还有 " + SmsQueueList.sharedQueue.size() +"个人。");
				//SmsQueueList.sharedQueue.put(new SmsVo());
			} catch (Exception ex) {
				Logger.getLogger(SmsProducer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
