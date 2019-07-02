package com.xcc.comm.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author
 * 日期工具类 zhangpeng
 */
public class DateUtilsExt {

	public final static String YYYY = "yyyy";

	public final static String MM = "MM";

	public final static String DD = "dd";

	public final static String YYYY_MM_DD = "yyyy-MM-dd";

	public final static String YYYY_SLASH_MM_SLASH_DD = "yyyy/MM/dd";

	public final static String YYYYMMDD = "yyyyMMdd";

	public final static String YYYY_MM = "yyyy-MM";

	public final static String YYYYMM = "yyyyMM";

	public final static String HH_MM_SS = "HH:mm:ss";

	public final static String HH_MM = "HH:mm";

	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	public final static String YYYYMMDDHHMM = "yyyyMMddHHmm";

	public final static String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	public final static String YYYYMMDDHHMMSS_SSS = "yyyyMMddHHmmssSSS";

	public final static String MM_SLASH_DD = "MM/dd";

	public static final String DEFAULT_TIME_STR = "2000-01-01 00:00:00";
	

	/**
	 * 日期格式化－将<code>Date</code>类型的日期格式化为<code>String</code>型
	 *
	 * @param date 待格式化的日期
	 * @param pattern 时间样式
	 * @return 一个被格式化了的<code>String</code>日期
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		else {
			return DateFormatUtils.format(date, pattern);
		}
	}

	/**
	 * 默认为yyyy-MM-dd格式的解析
	 *
	 * @param strDate
	 * @return
	 */
	public static Date parse(String strDate) throws Exception {
		return parse(strDate, YYYY_MM_DD);
	}

	/**
	 * 日期解析－将<code>String</code>类型的日期解析为<code>Date</code>型
	 *
	 * @param strDate 待解析的日期字符串
	 * @param pattern 日期格式
	 * @BusinessException ParseException 如果所给的字符串不能被解析成一个日期
	 * @return 一个被格式化了的<code>Date</code>日期
	 */
	public static Date parse(String strDate, String pattern) throws Exception {
		try {
			return DateUtils.parseDate(strDate, pattern);
		}
		catch (ParseException e) {
			throw new Exception(e);
		}
	}

	/**
	 * 获取指定毫秒数所表示的日期
	 *
	 * @param millis millis the new time in UTC milliseconds from the epoch.
	 * @return
	 */
	public static Date getDate(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 *
	 * 获取间隔日期 <br>
	 * 〈功能详细描述〉
	 *
	 * @param date 基准日期
	 * @param field 指定日期字段
	 * @param intervals 间隔数
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static Date getDate(Date date, int field, int intervals) throws Exception {
		try {
			Calendar calendar = Calendar.getInstance();
			if (date != null) {
				calendar.setTime(date);
			}
			calendar.set(field, calendar.get(field) + intervals);
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new Exception("获取间隔日期",e);
		}
	}

	/**
	 * 获取月末日期
	 * @param day
	 * @return
	 */
	public static Date getMonthEndDate(Date day) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(day);
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		return cale.getTime();
	}
	/**
	 * 获取月末时间  2018-04-30 23:59:59
	 * @param day
	 * @return
	 */
	public static Date getMonthEndTime(Date day) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(day);
		cale.set(Calendar.DATE, cale.getActualMaximum(Calendar.DATE));
		//时
		cale.set(Calendar.HOUR_OF_DAY, 23);
		//分
		cale.set(Calendar.MINUTE, 59);
		//秒
		cale.set(Calendar.SECOND, 59);
		return cale.getTime();
	}

	/**
	 * 获取月初开始时间 2018-04-01 00:00:00
	 * @param day
	 * @return
	 */
	public static Date getMonthStartTime(Date day) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(day);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		//时
		cale.set(Calendar.HOUR_OF_DAY, 0);
		//分
		cale.set(Calendar.MINUTE, 0);
		//秒
		cale.set(Calendar.SECOND, 0);
		return cale.getTime();
	}

	/**
	 * 获取某天的开始时间
	 *
	 * @param date 日期
	 * @return
	 */
	public static Date beginOfDay(Date date) {
		return beginOfDay(calendar(date)).getTime();
	}

	/**
	 * 获取某天的结束时间
	 *
	 * @param date 日期
	 * @return
	 */
	public static Date endOfDay(Date date) {
		return endOfDay(calendar(date)).getTime();
	}

	/**
	 * 获取某天的开始时间
	 *
	 * @param calendar 日期 {@link Calendar}
	 * @return {@link Calendar}
	 */
	public static Calendar beginOfDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * 获取某天的结束时间
	 *
	 * @param calendar 日期 {@link Calendar}
	 * @return {@link Calendar}
	 */
	public static Calendar endOfDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar;
	}

	/**
	 * 转换为Calendar对象
	 *
	 * @param date 日期对象
	 * @return Calendar对象
	 */
	public static Calendar calendar(Date date) {
		return calendar(date.getTime());
	}

	/**
	 * 转换为Calendar对象
	 *
	 * @param millis 时间戳
	 * @return Calendar对象
	 */
	public static Calendar calendar(long millis) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal;
	}

	/**
	 * 计算两个日期相差的天数，end-start
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static long daysBetween(Date start, Date end) {
		long diff = (beginOfDay(end).getTime() - beginOfDay(start).getTime()) / 86400000;
		return Math.abs(diff);
	}

	/**
	 * 根据月期间获取当月所有天数
	 * @param accPeriod 列如："2018-01"
	 * @return 一个月的天数list
	 */
	public static List<Date> getDayOfMonth(String accPeriod) throws Exception {
		List<Date> days = new ArrayList<>();
		Date month = parse(accPeriod, YYYY_MM);
		Date monthBegin = getMonthStartTime(month);
		Date monthEnd = getMonthEndTime(month);
		int difDate = (int) daysBetween(monthBegin, monthEnd);

		Calendar calendar = Calendar.getInstance();

		for (int i = 0; i <= difDate; i++){
			calendar.setTime(monthBegin);
			calendar.add(Calendar.DATE, i);
			days.add(calendar.getTime());
		}
	return days;
	}

	/**
	 * 获取两个月份之间的月份列表
	 * 列如：
	 * 传入：2018-01,2018-05  返回：["2018-01","2018-02","2018-03","2018-04"]
	 *
	 * @param minDate
	 * @param maxDate
	 * @return
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
		ArrayList<String> result = new ArrayList<String>();

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(parse(minDate, YYYY_MM));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(parse(maxDate, YYYY_MM));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH) - 1, 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(format(curr.getTime(), YYYY_MM));
			curr.add(Calendar.MONTH, 1);
		}

		return result;
	}

	/**
	 * 日期 天 加减
	 *
	 * @param date 时间
	 * @param num  天数（负数则减）
	 * @return
	 */
	public static Date addDay(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);
		return calendar.getTime();
	}

	/**
	 * 按间隔获取日期
	 * @param start 开始日期 2018-01-01
	 * @param end 结束日期  2018-01-08
	 * @param num 间隔天数 >=0 例如 (2018-01-01,2018-01-08,1)
	 * @return [{start:2018-01-01,end:2018-01-03},{start:2018-01-04,end:2018-01-06},{start:2018-01-07,end:2018-01-08}]
	 */
	public static List<Map<String,Date>> getDaysByIncrease(String start, String end,int num) throws Exception {
		Date dateStart = parse(start, YYYY_MM_DD);
		Date dateEnd = parse(end, YYYY_MM_DD);
		List<Map<String,Date>> days= new ArrayList<>();
		if (dateEnd.before(dateStart)) {
			throw new Exception("start date must be before or equal to end date");
		}
		if (num <0 ){
			throw new Exception("Increase num must be greater than -1");
		}
		Map<String,Date> between;
		while (dateEnd.after(dateStart)) {
			between = new HashMap<>(2);
			between.put("start",dateStart);
			dateStart = addDay(dateStart,num);
			if (dateEnd.before(dateStart)) {
				between.put("end",dateEnd);
				days.add(between);
				break;
			}
			between.put("end",dateStart);
			dateStart = addDay(dateStart,1);
			if (dateEnd.before(dateStart)) {
				between.put("end",dateEnd);
				days.add(between);
				break;
			}
			days.add(between);
		}
		return days;
	}

	/**
	 * 日期 月份 加减
	 *
	 * @param date 时间
	 * @param num  月份数（负数则减）
	 * @return
	 */
	public static Date addMonth(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num);
		return calendar.getTime();
	}

	/**
	 * 获取指定月份的天数
	 * @return
	 */
	public static int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 获取指定月份的天数
	 * @param  ，格式“201611”
	 * @return
	 */
	public static List<Date> dayReport(String month) throws Exception {
		Date mon = parse(month, YYYYMM);
		Calendar cal = Calendar.getInstance();
		//month 为指定月份 ，格式“201611”
		cal.setTime(mon);
		int year = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int dayNumOfMonth = getDaysByYearMonth(year, m);
		// 从一号开始
		cal.set(Calendar.DAY_OF_MONTH, 1);
		List<Date> dateList = new ArrayList<>();
		for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
			Date date = cal.getTime();
			dateList.add(date);
		}
		return dateList;
	}

	/**
	 * 获取两个日期之前所有天的集合
	 *
	 * @param startDate startDate
	 * @param endDate   endDate
	 * @return java.util.List<java.util.Date>
	 * @author yanganyu
	 * @date 2019/2/27 15:58
	 */
	public static List<Date> getDatesBetween(LocalDate startDate, LocalDate endDate) throws Exception {
		return getLocalDatesBetween(startDate, endDate).stream()
				.map(localDate -> Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.collect(Collectors.toList());
	}

	/**
	 * 获取两个日期之前所有天的集合
	 *
	 * @param startLocalDate startLocalDate
	 * @param endLocalDate   endLocalDate
	 * @return java.util.List<java.time.LocalDate>
	 * @author yanganyu
	 * @date 2019/2/27 15:58
	 */
	public static List<LocalDate> getLocalDatesBetween(LocalDate startLocalDate, LocalDate endLocalDate) throws Exception {
		LocalDate incrementingDate = startLocalDate;
		List<LocalDate> allLocalDateList = new ArrayList<>();
		if (incrementingDate.isAfter(endLocalDate)) {
			throw new Exception("start date must be before or equal to end date");
		}
		while (!incrementingDate.isAfter(endLocalDate)) {
			allLocalDateList.add(incrementingDate);
			incrementingDate = incrementingDate.plusDays(1);
		}
		return allLocalDateList;
	}

	/**
	 *
	 * @param
	 * @param lastSettleMonth 最后结账月份
	 * @return  如果业务月份小于等于最后结账月份，则返回最后结账月份的下个月
	 *    如果业务月份大于最后结账月份，则返回业务月份
	 */
	public static String getAccountingMonth(String bizMonth,String lastSettleMonth) throws Exception {
		Date months = parse(bizMonth, YYYY_MM);
		Date lastSettleMonths = parse(lastSettleMonth,"yyyy-MM");
		if(months.compareTo(lastSettleMonths) <= 0 ){
			return DateTimeFormatter.ofPattern(YYYY_MM).format(LocalDateTime.ofInstant(lastSettleMonths.toInstant(),ZoneId.systemDefault()).plusMonths(1));
		} else {
			return bizMonth;
		}
	}
}
