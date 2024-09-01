package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.constants;

public class UserConstants {
    /**
     * 排班日历的查询方式
     */
    public static final String CAL_QUERY_BY_TYPE = "TYPE";
    public static final String CAL_QUERY_BY_TEAM = "TEAM";
    public static final String CAL_QUERY_BY_USER = "USER";
    /**
     * 校验返回结果码
     */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";

    /**
     * 轮班方式
     */
    public static final String CAL_SHIFT_TYPE_SINGLE = "SINGLE";
    public static final String CAL_SHIFT_TYPE_TWO = "SHIFT_TWO";
    public static final String CAL_SHIFT_TYPE_THREE = "SHIFT_THREE";
    public static final String CAL_SHIFT_NAME_DAY = "白班";
    public static final String CAL_SHIFT_NAME_NIGHT = "夜班";
    public static final String CAL_SHIFT_NAME_MID = "中班";
    public static final String CAL_SHIFT_METHOD_QUARTER = "QUARTER";
    public static final String CAL_SHIFT_METHOD_MONTH = "MONTH";
    public static final String CAL_SHIFT_METHOD_WEEK = "WEEK";
    public static final String CAL_SHIFT_METHOD_DAY = "DAY";

    public static final String ORDER_STATUS_CONFIRMED = "CONFIRMED";// confirmed 证实
    public static final String ORDER_STATUS_PREPARE = "PREPARE"; // prepare 准备
}
