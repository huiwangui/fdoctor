package com.boco.common.sms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 短信队列
 * @author sufj
 *
 */
public class SmsQueueList {

	public static BlockingQueue<SmsVo> sharedQueue = new LinkedBlockingQueue<SmsVo>();
	
}
