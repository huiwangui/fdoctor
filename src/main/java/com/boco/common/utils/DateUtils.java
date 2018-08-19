package com.boco.common.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author jeeplus
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	public static int immuneNumber = 0;
	public static int followUpNumber = 0;
	public static int physicalExaminationNumber = 0;

	/**
	 * 
	 * addZero:(数字格式化，前面不足的补0). <br/>
	 * 
	 * @author q
	 * @param size
	 *            ：返回的位数
	 * @param item
	 *            ：目标数字
	 * @return
	 */
	public static String addZero(int size, int item) {
		String str1 = String.valueOf(item);
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < size; i++) {
			bf.append("0");
		}
		DecimalFormat df = new DecimalFormat(bf.toString());
		String str2 = df.format(Integer.parseInt(str1));
		return str2;
	}

	private static String[] parsePatterns = { "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
			"yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyyMMdd" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDateFormat() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60
				* 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
				+ sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 
	 * getYearLaterDate:(获取目标时间N年后的日期). <br/>
	 * 
	 * @author q
	 * @param itemDate
	 * @param yeasLater
	 * @return
	 */
	public static Date getYearLaterDate(Date itemDate, Integer yeasLater) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, yeasLater);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 
	 * getAge:(获取年龄). <br/>
	 * 
	 * @author q
	 * @param birthDate
	 * @return
	 */
	public static int getAge(Date birthDate) {

		if (birthDate == null)
			throw new RuntimeException("出生日期不能为null");

		int age = 0;

		Date now = new Date();

		SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat format_M = new SimpleDateFormat("MM");

		String birth_year = format_y.format(birthDate);
		String this_year = format_y.format(now);

		String birth_month = format_M.format(birthDate);
		String this_month = format_M.format(now);

		// 初步，估算
		age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

		// 如果未到出生月份，则age - 1
		if (this_month.compareTo(birth_month) < 0)
			age -= 1;
		if (age < 0)
			age = 0;
		return age;
	}

	/**
	 * 
	 * getStartTimeOfDay:(获取当日零点时间戳). <br/>
	 * 
	 * @author q
	 * @param date
	 * @return
	 */
	public static Long getStartTimeOfDay(Date date) {
		Calendar dateStart = Calendar.getInstance();
		dateStart.setTime(date);
		dateStart.set(Calendar.HOUR_OF_DAY, 0);
		dateStart.set(Calendar.MINUTE, 0);
		dateStart.set(Calendar.SECOND, 0);
		dateStart.set(Calendar.MILLISECOND, 0);
		return dateStart.getTime().getTime();
	}

	/**
	 * 
	 * getStartTimeOfDay:(获取当日末点时间戳). <br/>
	 * 
	 * @author q
	 * @param date
	 * @return
	 */
	public static Long getEndTimeOfDay(Date date) {
		Calendar dateEnd = Calendar.getInstance();
		dateEnd.setTime(date);
		dateEnd.set(Calendar.HOUR_OF_DAY, 23);
		dateEnd.set(Calendar.MINUTE, 59);
		dateEnd.set(Calendar.SECOND, 59);
		dateEnd.set(Calendar.MILLISECOND, 999);
		return dateEnd.getTime().getTime();
	}

	/**
	 * 
	 * getStartOfMonth:(获取一个月起始时间). <br/>
	 * 
	 * @author q
	 * @return
	 */
	public static Date getStartOfMonth(int year, int month) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(
				" 00:00:00");
		return DateUtils.parseDate(str);
	}

	/**
	 * 
	 * getStartOfMonth:(获取一个月结束时间). <br/>
	 * 
	 * @author q
	 * @return
	 */
	public static Date getEndOfMonth(int year, int month) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
		return DateUtils.parseDate(str);
	}

	/**
	 * 
	 * getWeeksInMonth:(通过某个时间获取当前月份每一周的开始、截止日期). <br/>
	 * 
	 * @author q
	 * @param itemDate
	 * @return
	 */
	public static List<WeeksInMonth> getWeeksInMonth(Date nowDate) {
		List<WeeksInMonth> list = new ArrayList<WeeksInMonth>();
		// 获取每个月第一个星期一
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		Integer year = now.get(Calendar.YEAR);
		Integer month = now.get(Calendar.MONTH) + 1;

		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		int i = 1;
		while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			cal.set(Calendar.DAY_OF_MONTH, i++);
		}

		Date firstMonday = cal.getTime();

		int j = 1;
		Date itemDate = firstMonday;
		while (itemDate.before(DateUtils.getEndOfMonth(year, month))) {
			WeeksInMonth itemWeek = new WeeksInMonth();
			itemWeek.setBeginDate(itemDate);
			Calendar calendarInWeek = Calendar.getInstance();
			calendarInWeek.setTime(itemDate);
			calendarInWeek.add(Calendar.DATE, 6);
			itemWeek.setEndDate(calendarInWeek.getTime());
			itemWeek.setMonthNum(j);
			list.add(itemWeek);
			calendarInWeek.add(Calendar.DATE, 1);
			itemDate = calendarInWeek.getTime();
			j++;
		}

		return list;
	}

	public static Date parse(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(strDate);
	}

	/**
	 * 生日换算年龄
	 * 
	 */
	public static int getAgeByCal(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			} else {
				age--;
			}
		}
		return age;
	}

	/**
	 * 
	 * getDateBeforeOrAfter:(获取目标日期之前或者之后的时间). <br/>
	 * @author q
	 * @param nowDate
	 * @param days
	 * @return
	 */
	public static Date getDateBeforeOrAfter(Date nowDate, int days) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(nowDate);
		calendar1.add(Calendar.DATE, days);
		
		return calendar1.getTime();
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// System.out.println(formatDate(parseDate("2010/3/6")));
		// System.out.println(getDate("yyyy年MM月dd日 E"));
		// long time = new Date().getTime()-parseDate("2012-11-19").getTime();
		// System.out.println(time/(24*60*60*1000));
	}
}
