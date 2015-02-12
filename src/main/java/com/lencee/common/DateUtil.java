/**
 * 包名：com.lencee.common
 * 文件名：DateUtil.java
 * 版本信息：
 * 日期：2014年7月11日-上午9:36:09
 * 
 */

package com.lencee.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * 
 * <p>日期工具类<p>
 * <p>本类是对org.apache.commons.lang3.time.DateUtils的扩展</p>
 * @version 2014年12月8日 下午3:12:34
 *
 */
public class DateUtil extends DateUtils {

	private static Locale locale = Locale.SIMPLIFIED_CHINESE;
	private static Calendar calendar = Calendar.getInstance(locale);

	// ~ 格式转换
	// =============================================
	/**
	 * 将长整形日期转换为字符串日期
	 * 
	 * @param millis 长整形日期（单位：毫秒）
	 * @param pattern 格式化日期的模式，不允许为空
	 * @return String 满足日期模式的字符串
	 * @since 1.0.0
	 */
	public static String format(long millis, String pattern) {
		String retDateStr = DateFormatUtils.format(millis, pattern);
		return retDateStr;
	}

	/**
	 * 将日期类型日期转换为字符串日期
	 * 
	 * @param date 格式化的日期，不允许为空
	 * @param pattern 格式化日期的模式，不允许为空
	 * @return String 满足日期模式的字符串
	 * @since 1.0.0
	 */
	public static String format(Date date, String pattern) {
		String retDateStr = DateFormatUtils.format(date, pattern);
		return retDateStr;
	}

	/**
	 * 将日历类型日期转换为字符串日期
	 * 
	 * @param calendar 格式化的日历，不允许为空
	 * @param pattern 格式化日期的模式，不允许为空
	 * @return String 满足日期模式的字符串
	 * @since 1.0.0
	 */
	public static String format(Calendar calendar, String pattern) {
		String retDateStr = DateFormatUtils.format(calendar, pattern);
		return retDateStr;
	}

	/**
	 * 将日期字符串转换为日期类型
	 * 
	 * @param dateStr 日期字符串，不允许为空
	 * @param pattern 格式化日期的模式，不允许为空
	 * @return Date 日期对象
	 * @throws ParseException 日期字符串与日期模式不匹配时抛出该异常
	 * @since 1.0.0
	 */
	public static Date format(String dateStr, String pattern) throws ParseException {
		Date date = null;
		if (StringUtils.isNotBlank(dateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date = sdf.parse(dateStr);
		}
		return date;
	}

	/**
	 * 将长整形日期转换为DATE类型
	 * 
	 * @param millis 长整形日期（单位：毫秒）
	 * @return Date 日期对象
	 * @since 1.0.0
	 */
	public static Date format(long millis) {
		Date date = null;
		calendar.setTimeInMillis(millis);
		date = calendar.getTime();
		return date;
	}
	// ~ 取特定日期
	// =============================================
	/**
	 * 取当前日期的字符串格式
	 * 
	 * @param pattern 格式化日期的模式，不允许为空
	 * @return 当前日期的格式化字符
	 * @since 1.0.0
	 */
	public static String getCurrentDate(String pattern) {
		DateFormat sdfDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();
		String curdateStr = sdfDateFormat.format(date);
		return curdateStr;
	}
	/**
	 * 取指定日期的“年”
	 * 
	 * @param date 指定日期对象
	 * @return int 指定日期的年
	 * @since 1.0.0
	 */
	public static int getYear(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * 取指定日期的“月”
	 * 
	 * @param date 指定日期对象
	 * @return int 指定日期的月份
	 * @since 1.0.0
	 */
	public static int getMonth(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	/**
	 * 取日期的“日”
	 * 
	 * @param date 指定日期对象
	 * @return 指定日期的日历
	 * 
	 * @since 1.0.0
	 */
	public static int getDay(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}
	/**
	 * 取日期的“时”
	 * 
	 * @param date 指定日期对象
	 * @return 指定日期的小时
	 * @since 1.0.0
	 */
	public static int getHour(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR);
	}
	/**
	 * 取日期的“分”
	 * 
	 * @param date 指定日期对象
	 * @return 指定日期的分钟
	 * @since 1.0.0
	 */
	public static int getMinute(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}
	/**
	 * 取日期的“秒”
	 * 
	 * @param date 指定日期对象
	 * @return 指定日期的秒数
	 * @since 1.0.0
	 */
	public static int getSecond(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}
	/**
	 * 取指定日期是当前年内的第几周
	 * 
	 * @param date 指定日期对象
	 * @return int
	 * @exception
	 * @since 1.0.0
	 */
	public static int getWeekOfYear(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 * 取指定日期是当前月内的第几周
	 * 
	 * @param date 指定日期对象
	 * @return
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static int getWeekOfMonth(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	/**
	 * 取日期是当年的第几天
	 * 
	 * @param date 指定日期对象
	 * @return
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static int getDayOfYear(Date date) {
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	// ~ 日期比较
	// =============================================

	// ~ 日期计算
	// =============================================

	/**
	 * 判断日期是否在指定的日期之间
	 * 
	 * @param dateComp 目标日期
	 * @param dateStart 起始日期
	 * @param dateEnd 终止日期
	 * @param pattern 日期格式的模式,如果该字段传入为空则使用默认的格式（yyyy-MM-dd）,传入的
	 *            目标日期、起始日期、终止日期必须遵循此模式
	 * @return int
	 *         返回结果 = 0 ：<param>目标日期</param> 在 <param>起止日期</param> 之间
	 *         返回结果 = -1：<param>目标日期</param> 在 <param>起始日期</param> 之前
	 *         返回结果 = 1 ：<param>目标日期</param> 在 <param>终止日期</param> 之后
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	public static int isBetween(String dateComp, String dateStart, String dateEnd, String pattern) throws Exception {

		DateFormat sdfDateFormat = new SimpleDateFormat(pattern);

		Date dateCompD = sdfDateFormat.parse(dateComp);
		Date dateStartD = sdfDateFormat.parse(dateStart);
		Date dateEndD = sdfDateFormat.parse(dateEnd);

		if (dateEndD.compareTo(dateStartD) < 0) {
			throw new Exception("起始时间不允许大于终止时间");
		}

		if (dateCompD.compareTo(dateStartD) < 0) {
			return -1;
		}

		if (dateCompD.compareTo(dateEndD) > 0) {
			return 1;
		}

		return 0;
	}
	/**
	 * 判断日期是否在指定的日期之间
	 * 
	 * @param dateComp 目标日期
	 * @param dateStart 起始日期
	 * @param dateEnd 终止日期
	 * @return int
	 *         返回结果 = 0 ：<param>目标日期</param> 在 <param>起止日期</param> 之间
	 *         返回结果 = -1：<param>目标日期</param> 在 <param>起始日期</param> 之前
	 *         返回结果 = 1 ：<param>目标日期</param> 在 <param>终止日期</param> 之后
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	public static int isBetween(Date dateComp, Date dateStart, Date dateEnd) throws Exception {

		if (dateEnd.compareTo(dateStart) < 0) {
			throw new Exception("起始时间不允许大于终止时间");
		}

		if (dateComp.compareTo(dateStart) < 0) {
			return -1;
		}

		if (dateComp.compareTo(dateEnd) > 0) {
			return 1;
		}

		return 0;
	}

}
