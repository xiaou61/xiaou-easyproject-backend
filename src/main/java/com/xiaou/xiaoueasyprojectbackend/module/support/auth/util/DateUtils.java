package com.xiaou.xiaoueasyprojectbackend.module.support.auth.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description: 时间处理工具类
 * @Author: sxgan
 * @Date: 2024-04-06 21:50
 * @Version: 1.0
 **/
@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    
    @Getter
    @AllArgsConstructor
    public enum DatePatternEnum {
        YYYY("yyyy"),
        YYYY_MM("yyyy-MM"),
        YYYY_MM_DD("yyyy-MM-dd"),
        YYYY_MM_DD_PATH("yyyy/MM/dd"),
        YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss");
        
        private final String pattern;
    }
    
    private static final String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
    
    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }
    
    /**
     * 获取当前日期字符串,格式为yyyy-MM-dd
     *
     * @return String 当前日期
     */
    public static String getDateStr() {
        return dateTimeNow(DatePatternEnum.YYYY_MM_DD.pattern);
    }
    
    /**
     * 获取当前日期,格式为yyyy-MM-dd HH:mm:ss
     *
     * @return String 当前日期
     */
    public static String getTime() {
        return dateTimeNow(DatePatternEnum.YYYY_MM_DD_HH_MM_SS.pattern);
    }
    
    /**
     * 获取当前时间,格式为yyyyMMddHHmmss
     *
     * @return String 当前时间
     */
    public static String dateTimeNow() {
        return dateTimeNow(DatePatternEnum.YYYYMMDDHHMMSS.pattern);
    }
    
    /**
     * 当前日期路径格式， 即年/月/日，如2018/08/08
     *
     * @return String 当前日期
     */
    public static String datePath() {
        return dateTimeNow(DatePatternEnum.YYYY_MM_DD_PATH.pattern);
    }
    
    /**
     * 获取当前时间
     *
     * @param datePattern 时间格式
     * @return String 当前时间
     */
    public static String dateTimeNow(String datePattern) {
        return parseDateToStr(new Date(), datePattern);
    }
    
    
    /**
     * 传入时间获取对应日期字符串，转换格式为yyyy-MM-dd
     *
     * @param date 传入时间
     * @return String 日期字符串
     */
    public static String dateTime(final Date date) {
        return parseDateToStr(date, DatePatternEnum.YYYY_MM_DD.pattern);
    }
    
    /**
     * 获取当前日期,格式为yyyy-MM-dd
     *
     * @param datePattern 日期格式
     * @param date        传入时间
     * @return String 日期字符串
     */
    public static String parseDateToStr(final Date date, String datePattern) {
        return new SimpleDateFormat(datePattern).format(date);
    }
    
    
    /**
     * 将时间字符串转换为Date类型
     *
     * @param dataPattern 日期格式
     * @param ts          时间字符串
     * @return Date 日期
     */
    public static Date parseStrToDate(final String ts, String dataPattern) {
        try {
            return new SimpleDateFormat(dataPattern).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 获取服务器启动时间
     *
     * @return Date 服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }
    
    /**
     * 计算两个时间相差天数
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return int 天数
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return Math.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)));
    }
    
    /**
     * 计算时间差，精确到分钟
     *
     * @param endDate   最后时间
     * @param startTime 开始时间
     * @return 时间差（天/小时/分钟）
     */
    public static String timeDistance(Date endDate, Date startTime) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
    
    /**
     * LocalDateTime转Date
     *
     * @param temporalAccessor LocalDateTime
     * @return Date
     */
    public static Date toDate(LocalDateTime temporalAccessor) {
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }
    
    /**
     * LocalDate转Date
     *
     * @param temporalAccessor LocalDate
     * @return Date
     */
    public static Date toDate(LocalDate temporalAccessor) {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        return toDate(localDateTime);
    }
    
    /**
     * 获取当前时间到午夜零点的秒数（今天剩余秒数）
     *
     * @return 今天剩余秒数
     */
    public static Integer getSecondsToMidnight() {
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int) (tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
    }
    
    /**
     * 判断某一时间是否为指定范围内
     *
     * @param date  时间
     * @param begin 开始时间
     * @param end   结束时间
     * @return true|false
     */
    public static boolean isBetween(Date date, Date begin, Date end) {
        return date.after(begin) && date.before(end);
    }
    
    /**
     * 判断某一时间是否为指定范围内
     *
     * @param dateStr  时间字符串
     * @param beginStr 开始时间字符串
     * @param endStr   结束时间字符串
     * @param pattern  时间格式
     * @return true|false
     */
    public static boolean isBetween(String dateStr, String beginStr, String endStr, String pattern) {
        Date date = DateUtils.parseStrToDate(dateStr, pattern);
        Date beginDate = DateUtils.parseStrToDate(beginStr, pattern);
        Date endDate = DateUtils.parseStrToDate(endStr, pattern);
        return isBetween(date, beginDate, endDate);
    }
    
    /**
     * 获取当前日期时间的字符串数组，数组第0位为日期，第1位为时间
     *
     * @return String[]
     */
    public static String[] getDateAndTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        return format.split("[@]");
    }
}