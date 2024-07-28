package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.core;



import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.biz.Context;

import java.util.List;

public class ContextUtils {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static <T> T getBean(Class<T> targetClass) {
        return applicationContext.getBean(targetClass);
    }

    public static <T> List<T> getBeans(Class<T> targetClass) {
        return applicationContext.getBeans(targetClass);
    }

    private ContextUtils() {
    }

    public static void pushContext(Context context) {
        applicationContext.pushConext(context);
    }

}
