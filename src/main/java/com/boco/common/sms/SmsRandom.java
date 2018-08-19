package com.boco.common.sms;

import com.boco.common.utils.PropertiesUtils;

/**
 * 生成随机验证码
 * 
 * @author tonysu
 *
 */
public class SmsRandom {

	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom() {
		String sms_code_length;
		String sms_code_isnumber;
		try {
			 sms_code_length = PropertiesUtils.getValue("sms_code_length");
			 sms_code_isnumber = PropertiesUtils.getValue("sms_code_isnumber");
		} catch (Exception e) {
			 sms_code_length = "4";
			 sms_code_isnumber = "true";
		}
		
		boolean numberFlag = Boolean.parseBoolean(sms_code_isnumber);
		int length = Integer.parseInt(sms_code_length);

		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	public static void main(String[] args) {
		String code = SmsRandom.createRandom();
		System.out.println(code);
	}
}
