package com.xiaou.xiaoueasyprojectbackend.common.utils;



import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalCalendar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CalendarUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * 星期日;
     */
    public static final String SUNDAY = "星期日";

    /**
     * 星期一;
     */
    public static final String MONDAY = "星期一";

    /**
     * 星期二;
     */
    public static final String TUESDAY = "星期二";

    /**
     * 星期三;
     */
    public static final String WEDNESDAY = "星期三";

    /**
     * 星期四;
     */
    public static final String THURSDAY = "星期四";

    /**
     * 星期五;
     */
    public static final String FRIDAY = "星期五";

    /**
     * 星期六;
     */
    public static final String SATURDAY = "星期六";

    /**
     * 显示年月日时分秒，例如 2015-08-11 09:51:53.
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 显示年月日时分，例如 2015-08-11 09:51.
     */
    public static final String NO_SECOND_DATETIME_PATTERN = "yyyy-MM-dd HH:mm";
    /**
     * 仅显示年月日，例如 2015-08-11.
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 仅显示时分秒，例如 09:51:53.
     */
    public static final String TIME_PATTERN = "HH:mm:ss";
    /**
     * 显示年月日时分秒(由/分割)，例如 2015/08/11 09:51:53.
     */
    public static final String DATETIME_PATTERN_WITH_SLASH = "yyyy/MM/dd HH:mm:ss";
    /**
     * 显示年月日(由/分割)，例如 2015/08/11.
     */
    public static final String DATE_PATTERN_WITH_SLASH = "yyyy/MM/dd";
    /**
     * 仅显示年(无符号)，例如 2015.
     */
    private static final String YEAR_PATTERN = "yyyy";
    /**
     * 仅显示年月，例如 2015-08.
     */
    private static final String MONTH_PATTERN = "yyyy-MM";

    /**
     * 获取当前日期和时间字符串.
     *
     * @return String 日期时间字符串，例如 2015-08-11 09:51:53
     */
    public static String getDateTimeStr() {
        return format(new Date(), DATETIME_PATTERN);
    }

    /**
     * 时间戳转换为日期时间字符串
     *
     * @param timestamp 时间戳
     * @param pattern   日期格式 例如DATETIME_PATTERN
     * @return String 日期时间字符串，例如 2015-08-11 09:51:53
     */
    public static String getDateTimeStr(long timestamp, String pattern) {
        return new SimpleDateFormat(pattern).format(timestamp);
    }

    /**
     * 获取当前日期字符串.
     *
     * @return String 日期字符串，例如2015-08-11
     */
    public static String getDateStr() {
        return format(new Date(), DATE_PATTERN);
    }

    /**
     * 获取指定日期字符串.
     *
     * @return String 日期字符串，例如2015-08-11
     */
    public static String getDateStr(Date theday) {
        return format(theday, DATE_PATTERN);
    }

    /**
     * 获取当前时间字符串.
     *
     * @return String 时间字符串，例如 09:51:53
     */
    public static String getTimeStr() {
        return format(new Date(), TIME_PATTERN);
    }

    /**
     * 获取当前年份字符串.
     *
     * @return String 当前年份字符串，例如 2015
     */
    public static String getYearStr() {
        return format(new Date(), YEAR_PATTERN);
    }

    /**
     * 获取当前月份字符串.
     *
     * @return String 当前月份字符串，例如 08
     */
    public static String getMonthStr() {
        return format(new Date(), "MM");
    }

    /**
     * 获取当前天数字符串.
     *
     * @return String 当前天数字符串，例如 11
     */
    public static String getDayStr() {
        return format(new Date(), "dd");
    }

    /**
     * 获取当前星期字符串.
     *
     * @return String 当前星期字符串，例如 星期二
     */
    public static String getDayOfWeekStr() {
        return format(new Date(), "E");
    }

    /**
     * 获取指定日期是星期几
     *
     * @param date 日期
     * @return String 星期几
     */
    public static String getDayOfWeekStr(Date date) {
        String[] weekOfDays = {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int num = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekOfDays[num];
    }


    /**
     * 获取日期时间字符串
     *
     * @param date   需要转化的日期时间
     * @param pattern 时间格式
     * @return String 日期时间字符串，例如 2015-08-11 09:51:53
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取指定日期num年数之后的日期.
     *
     * @param num 间隔年数(负数表示之前)
     * @return Date 日期
     */
    public static Date addYears(Date date, int num) {
        return add(date, num, Calendar.YEAR);
    }

    /**
     * 获取指定日期num月数之后的日期.
     *
     * @param num 间隔月数(负数表示之前)
     * @return Date 日期
     */
    public static Date addMonths(Date date, int num) {
        return add(date, num, Calendar.MONTH);
    }

    /**
     * 获取指定日期num周数之后的日期.
     *
     * @param date 日期
     * @param num 周数(负数表示之前)
     * @return Date 新的日期
     */
    public static Date addWeeks(Date date, int num) {
        return add(date, num, Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取指定日期num天数之后的日期.
     *
     * @param date 日期
     * @param num 天数(负数表示之前)
     * @return Date 新的日期
     */
    public static Date addDays(Date date, int num) {
        return add(date, num, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期num小时之后的日期.
     *
     * @param date 日期
     * @param num 小时数(负数表示之前)
     * @return Date 新的日期
     */
    public static Date addHours(Date date, int num) {
        return add(date, num, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定日期num分钟之后的日期.
     *
     * @param date 日期
     * @param num 分钟数(负数表示之前)
     * @return Date 新的日期
     */
    public static Date addMinutes(Date date, int num) {
        return add(date, num, Calendar.MINUTE);
    }

    /**
     * 获取指定日期num秒钟之后的日期.
     *
     * @param date 日期
     * @param num 秒钟数(负数表示之前)
     * @return Date 新的日期
     */
    public static Date addSeconds(Date date, int num) {
        return add(date, num, Calendar.SECOND);
    }

    /**
     * 获取当前日期指定数量日期时间单位之后的日期.
     *
     * @param date 日期
     * @param num 数量
     * @param unit 日期时间单位
     * @return Date 新的日期
     */
    public static Date add(Date date, int num, int unit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, num);
        return calendar.getTime();
    }

    /**
     * 获取本周的第一天，一个星期的第一天是星期一，最后一天是星期天
     *
     * @return Calendar 日历
     */
    public static Date getStartDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取本周的最后一天，一个星期的第一天是星期一，最后一天是星期天
     *
     * @return Calendar 日历
     */
    public static Date getEndDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取指定日期当周第一天的日期字符串
     *
     * @param date 指定日期
     * @return String 格式：yyyy-MM-dd
     */
    public static String getWeekStartTimeStr(Date date) {
        return getDateStr(getStartDayOfWeek(date));
    }

    /**
     * 获取本周最后一天的日期字符串
     *
     * @return String 格式：yyyy-MM-dd
     */
    public static String getWeekEndTimeStr() {
        return getDateStr(getEndDayOfWeek(new Date()));
    }


    /**
     * 获取指定日期所在月份的开始日期
     * @param date
     * @return
     */
    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在月份的最后一天
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }

    /**
     * 获取指定日期对应月份第一天
     * @param date
     * @return
     */
    public static String getMonthStartStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取指定日期对应月份最后一天
     * @param date
     * @return
     */
    public static String getMonthEndStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return sdf.format(calendar.getTime());
    }


    /**
     * 获取指定日期对应季度的第一天
     * @param theDay
     * @return
     */
    public static String getQuarterStartStr(Date theDay){
        LocalDate resDate = null;
        if (theDay == null) {
            resDate  = LocalDate.now();
        }else{
            resDate = LocalDate.parse(sdf.format(theDay),formatter);
        }
        Month month = resDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();

        resDate = LocalDate.of(resDate.getYear(), firstMonthOfQuarter, 1);

        return resDate.toString();
    }

    /**
     * 获取指定日期对应季度的最后一天
     * @param theDay
     * @return
     */
    public static String getQuarterEandStr(Date theDay){
        LocalDate resDate = LocalDate.now();
        if (theDay == null) {
            resDate = LocalDate.now();
        }else {
            resDate = LocalDate.parse(sdf.format(theDay),formatter);
        }
        Month month = resDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        resDate = LocalDate.of(resDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(resDate.isLeapYear()));
        return resDate.toString();
    }


    public static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Long getDateDiff(Date start,Date end){
        LocalDate sdate = LocalDate.parse(sdf.format(start),formatter);
        LocalDate edate = LocalDate.parse(sdf.format(end),formatter);
        return sdate.until(edate,ChronoUnit.DAYS);
    }

    public static Long getDateDiff(String  start,Date end){
        LocalDate sdate = LocalDate.parse(start,formatter);
        LocalDate edate = LocalDate.parse(sdf.format(end),formatter);
        return edate.until(sdate,ChronoUnit.DAYS);
    }

    public static Long getDateDiff(Date  start,String end){
        LocalDate sdate = LocalDate.parse(sdf.format(start),formatter);
        LocalDate edate = LocalDate.parse(sdf.format(end),formatter);
        return edate.until(sdate,ChronoUnit.DAYS);
    }

    /**
     * 计算两个日期之间的天数差值
     * @param start
     * @param end
     * @return
     */
    public static Long getDateDiff(String  start,String end){
        LocalDate sdate = LocalDate.parse(start,formatter);
        LocalDate edate = LocalDate.parse(end,formatter);
        return edate.until(sdate,ChronoUnit.DAYS);
    }

    public static Date getDatePlus(Date theDay,Integer count){
        LocalDate sdate = LocalDate.parse(sdf.format(theDay),formatter);
        sdate.plusDays(count);
        return Date.from(sdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取指定月份的所有日期
     * @param d
     * @return
     */
    public static List<CalCalendar> getDays(Date d){
        List<CalCalendar> lst=new ArrayList();
        Date date = getMonthStart(d);
        Date monthEnd = getMonthEnd(d);
        while (!date.after(monthEnd)) {
            CalCalendar cal = new CalCalendar();
            cal.setTheDay(sdf.format(date));
            lst.add(cal);
            date = getNext(date);
        }
        return lst;
    }

    /**
     * 获取指定日期前后N天的所有日期
     * @param date
     * @param num
     * @param pattern
     * @return
     */
    public static List<String> getDateStrList(Date date, int num, String pattern) {
        List<String> result = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        boolean flag = false;
        if (num < 0) {
            num = Math.abs(num);
            flag = true;
        }
        for (int i = 0; i < num; i++) {
            result.add(new SimpleDateFormat(pattern).format(c.getTimeInMillis()));
            c.add(Calendar.DATE, flag ? -1 : 1);
        }
        if (flag) {
            Collections.reverse(result);
        }
        return result;
    }
}
