package com.xiaou.xiaoueasyprojectbackend.module.support.monitorv2.util;

import com.xiaou.xiaoueasyprojectbackend.module.support.monitorv2.constant.CommonConstant;

public class oConvertUtils {
    public static String getString(String s) {
        return (getString(s, ""));
    }
    public static String getString(Object object) {
        if (isEmpty(object)) {
            return "";
        }
        return (object.toString().trim());
    }

    public static String getString(String s, String defval) {
        if (isEmpty(s)) {
            return (defval);
        }
        return (s.trim());
    }

    public static String getString(Object s, String defval) {
        if (isEmpty(s)) {
            return (defval);
        }
        return (s.toString().trim());
    }
    public static String getString(int i) {
        return (String.valueOf(i));
    }

    public static String getString(float i) {
        return (String.valueOf(i));
    }


    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if (CommonConstant.STRING_NULL.equals(object)) {
            return (true);
        }
        return (false);
    }


}
