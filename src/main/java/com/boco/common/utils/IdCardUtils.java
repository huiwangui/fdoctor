package com.boco.common.utils;

import java.util.Calendar;
import java.util.Date;
/**
 * 身份证相关方法
 * @author q
 *
 */
public class IdCardUtils {
	
	/** 中国公民身份证号码最小长度。 */
	public final static int CHINA_ID_MIN_LENGTH = 15;

	/** 中国公民身份证号码最大长度。 */
	public final static int CHINA_ID_MAX_LENGTH = 18;

	/**
	 * 根据身份编号获取年龄
	 *
	 * @param idCard
	 *            身份编号
	 * @return 年龄
	 */
	public static int getAgeByIdCard(String idCard) {
		int iAge = 0;
		Calendar cal = Calendar.getInstance();
		String year = idCard.substring(6, 10);
		int iCurrYear = cal.get(Calendar.YEAR);
		iAge = iCurrYear - Integer.valueOf(year);
		return iAge;
	}

	/**
	 * 根据身份编号获取生日
	 *
	 * @param idCard
	 *            身份编号
	 * @return 生日(yyyyMMdd)
	 */
	public static Date getBirthByIdCard(String idCard) {
		if (idCard.length() == CHINA_ID_MAX_LENGTH) {
			String birthStr = idCard.substring(6, 14);
			return DateUtils.parseDate(birthStr);
		}else {
			String birthStr = "19" + idCard.substring(6, 12);
			return DateUtils.parseDate(birthStr);
		}
		
	}

	/**
	 * 根据身份编号获取生日年
	 *
	 * @param idCard
	 *            身份编号
	 * @return 生日(yyyy)
	 */
	public static Short getYearByIdCard(String idCard) {
		return Short.valueOf(idCard.substring(6, 10));
	}

	/**
	 * 根据身份编号获取生日月
	 *
	 * @param idCard
	 *            身份编号
	 * @return 生日(MM)
	 */
	public static Short getMonthByIdCard(String idCard) {
		return Short.valueOf(idCard.substring(10, 12));
	}

	/**
	 * 根据身份编号获取生日天
	 *
	 * @param idCard
	 *            身份编号
	 * @return 生日(dd)
	 */
	public static Short getDateByIdCard(String idCard) {
		return Short.valueOf(idCard.substring(12, 14));
	}

	/**
	 * 根据身份编号获取性别
	 *
	 * @param idCard
	 *            身份编号
	 * @return 性别(1-男，2-女，N-未知)
	 */
	public static String getGenderByIdCard(String idCard) {
		String sGender = "未知";
		
		
		String sCardNum = null;
		if (idCard.length() == CHINA_ID_MAX_LENGTH) {
			sCardNum = idCard.substring(16, 17);
		}else if(idCard.length() == CHINA_ID_MIN_LENGTH) {
			sCardNum = idCard.substring(14, 15);
		}
		
		if (Integer.parseInt(sCardNum) % 2 != 0) {
			sGender = "1";// 男
		} else {
			sGender = "2";// 女
		}
		return sGender;
	}
}