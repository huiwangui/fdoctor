package com.boco.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 封装各类正则表达式
 * 
 * @author q
 *
 */
public class MatcherUtiles {
	/**
	 * 验证手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean mobileMach(String mobile) {
		Pattern p = Pattern
				.compile("^(13\\d|14[57]|15[012356789]|18\\d|17[013678])\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 验证身份证号
	 * 
	 * @param idCard
	 * @return
	 */
	public static boolean idCardMach(String idCard) {
		Pattern p = Pattern
				.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		Matcher m = p.matcher(idCard);
		return m.matches();
	}

	/**
	 * 验证字符串只能为中文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean chineseMach(String str) {
		Pattern p = Pattern.compile("[\u4E00-\u9FA5]*$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 无特殊字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean noSpecialChar(String string) {
		// TODO Auto-generated method stub
		if (string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "")
				.length() == 0) {
			// 如果不包含特殊字符
			return true;
		}
		return false;
	}

	/**
	 * 验证医生、医院、评论星级（只能为0-5之间且为0.5的整数倍）
	 * 
	 * @param stars
	 * @return
	 */
	public static boolean starsMach(String stars) {
		try {
			double starsInt = Double.parseDouble(stars);
			if (starsInt >= 0 && starsInt <= 5 && starsInt % 0.5 == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
