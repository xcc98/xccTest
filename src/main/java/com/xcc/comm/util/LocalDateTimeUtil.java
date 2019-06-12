package com.xcc.comm.util;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocalDateTimeUtil {
    /************ 使用前必看 ***********/
    /*
     *  当前时间
     *  LocalDateTime.now();
     *  根据年月日新建
     *  LocalDateTime.of()
     *  计算两个时间差(单位天 按ChronoUnit.* 枚举算) 下面结果是-1
     *  LocalDateTime.of(2019,03,12,0,0,0).until(LocalDateTime.of(2019,03,11,0,0,0),ChronoUnit.DAYS);
     *  时间往后新增往前新增
     *  LocalDateTime.now().minus(3, ChronoUnit.DAYS);
     *  LocalDateTime.now().plus(3, ChronoUnit.DAYS);
     *  LocalDate.now().minus(3,ChronoUnit.DAYS);
     *  LocalDate.now().plus(3,ChronoUnit.DAYS);
     *  比较时间先后
     *  LocalDateTime.now().isAfter(LocalDateTime.of(2019,03,11,0,0,0));
     *  LocalDateTime.now().isBefore(LocalDateTime.of(2019,03,11,0,0,0));
     */

    private final static ConcurrentMap<String, DateTimeFormatter> formatterCache = new ConcurrentHashMap<>(7);

    /**
     * 默认为YYYY-MM-dd格式的解析 解析为localDate
     *
     * @return
     */
    public static LocalDate parseDate(@NotNull String strDate) {
        Objects.requireNonNull(strDate, "strDate");
        return parseDate(strDate, DatePatternEnum.yyyy_MM_dd);
    }

    /***
     * 返回为localDate的时间
     *
     * @param strDate
     * @param patternEnum
     * @return : java.time.LocalDateTime
     * @date 2019/3/27 14:36
     */
    public static LocalDate parseDate(@NotNull String strDate, @NotNull DatePatternEnum patternEnum) {
        Objects.requireNonNull(strDate, "strDate");
        Objects.requireNonNull(patternEnum, "patternEnum");
        return LocalDate.parse(strDate, getFormatter(patternEnum.pattern));
    }

    /**
     * format localDate to string
     *
     * @return : java.lang.String
     * @date 2019/3/27 16:01
     */
    public static String formatDate(LocalDate localDate, DatePatternEnum patternEnum) {
        if (localDate == null) {
            return "";
        } else {
            return localDate.format(getFormatter(patternEnum.pattern));
        }
    }

    /**
     * 默认为yyyy-MM-dd HH:mm:ss格式的解析
     *
     * @return
     */
    public static LocalDateTime parseDateTime(@NotNull String strDate) {
        Objects.requireNonNull(strDate, "strDate");
        return parseDateTime(strDate, DateTimePatternEnum.yyyy_MM_dd_HH_mm_ss);
    }

    /***
     * 返回java8类型的时间
     *
     * @param strDate
     * @param patternEnum
     * @return : java.time.LocalDateTime
     * @date 2019/3/27 14:36
     */
    public static LocalDateTime parseDateTime(@NotNull String strDate, @NotNull DateTimePatternEnum patternEnum) {
        Objects.requireNonNull(strDate, "strDate");
        Objects.requireNonNull(patternEnum, "patternEnum");
        return LocalDateTime.parse(strDate, getFormatter(patternEnum.pattern));
    }

    /**
     * format localDateTime to string
     *
     * @return : java.lang.String
     * @date 2019/3/27 16:01
     */
    public static String formatDateTime(LocalDateTime localDateTime, DateTimePatternEnum patternEnum) {
        if (localDateTime == null) {
            return "";
        } else {
            return localDateTime.format(getFormatter(patternEnum.pattern));
        }
    }

    private static DateTimeFormatter getFormatter(String pattern) {
        DateTimeFormatter format = formatterCache.get(pattern);
        if (format == null) {
            format = createInstance(pattern);
            final DateTimeFormatter previousValue = formatterCache.putIfAbsent(pattern, format);
            if (previousValue != null) {
                // another thread snuck in and did the same work
                // we should return the instance that is in ConcurrentMap
                format = previousValue;
            }
        }
        return format;
    }

    private static DateTimeFormatter createInstance(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * 获取指定毫秒数所表示的日期
     *
     * @param millis
     *            millis the new time in UTC milliseconds from the epoch.
     * @return
     */
    public static LocalDateTime getDate(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), TimeZone.getDefault().toZoneId());
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999
     *
     * @return : java.time.LocalDateTime
     * @date 2019/3/27 16:30
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00
     *
     * @return : java.time.LocalDateTime
     * @date 2019/3/27 16:31
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * Date 转为LocalDateTime
     *
     * @return : java.time.LocalDateTime
     * @date 2019/3/27 16:32
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @return : java.util.Date
     * @date 2019/3/27 16:35
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Gets the number of milliseconds from the Java epoch of 1970-01-01T00:00:00Z.
     *
     * @return : java.lang.Long
     * @date 2019/3/27 16:46
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * Gets the number of seconds from the Java epoch of 1970-01-01T00:00:00Z.
     *
     * @return : java.lang.Long
     * @date 2019/3/27 16:46
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取月末日期
     *
     * @return : java.time.LocalDate
     * @date 2019/3/27 17:56
     */
    public static LocalDate getMonthEndDate(LocalDate localDate) {
        return localDate.withDayOfMonth(1);
    }

    /**
     * @return : java.time.LocalDate
     * @date 2019/3/27 17:56
     */
    public static LocalDate getMonthStartDate(LocalDate localDate) {
        return localDate.withDayOfMonth(1);
    }

    /**
     * 获取月末时间
     *
     * @return : java.time.LocalDate
     * @date 2019/3/27 17:58
     */
    public static LocalDateTime getMonthEndDateTime(LocalDateTime localDateTime) {
        return localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取月末时间
     *
     * @return : java.time.LocalDateTime
     * @date 2019/3/27 18:02
     */
    public static LocalDateTime getMonthStartDateTime(LocalDateTime localDateTime) {
        return localDateTime.withDayOfMonth(localDateTime.getDayOfMonth()).withHour(0).withMinute(0).withSecond(0)
                .withNano(0);
    }

    public enum DateTimePatternEnum {
        yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"), yyyyMMddHHmmss("yyyyMMddHHmmss"), yyyy_MM_dd_HH_mm(
                "yyyy-MM-dd HH:mm"), yyyyMMddHHmm("yyyyMMddHHmm"), yyyy_MM_dd_HH_mm_ss_SSS("yyyy-MM-dd HH:mm:ss.SSS"),
        // jdk 必须要求在秒之后加.
        yyyyMMddHHmmssSSS("yyyyMMddHHmmss.SSS");
        private final String pattern;

        DateTimePatternEnum(final String pattern) {
            this.pattern = pattern;
        }
    }

    public enum DatePatternEnum {
        yyyy_MM_dd("yyyy-MM-dd"), yyyy_SLASH_MM_SLASH_dd("yyyy/MM/dd"), yyyyMMdd("yyyyMMdd");
        private final String pattern;

        DatePatternEnum(final String pattern) {
            this.pattern = pattern;
        }
    }
}
