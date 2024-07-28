package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.core;

import cn.hutool.core.lang.ClassScanner;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.biz.Context;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.biz.ContextObserver;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("all")
public class ApplicationContext {

    private static Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    public void start() {
        Set<Class<?>> classes = ClassScanner.scanPackage();
        for (Class<?> aClass : classes) {
            if (aClass.isAnnotationPresent(Component.class)) {
                try {
                    beanMap.put(aClass, aClass.newInstance());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public <T> T getBean(Class<T> targetClass) {
        return (T) beanMap.get(targetClass);
    }

    public <T> List<T> getBeans(Class<T> targetClass) {
        List<T> result = new ArrayList<>();
        for (Class<?> aClass : beanMap.keySet()) {
            if (targetClass.isAssignableFrom(aClass)) {
                Object bean = beanMap.get(aClass);
                result.add((T) bean);
            }
        }
        return result;
    }

    public void pushConext(Context context) {

        List<ContextObserver> beans = getBeans(ContextObserver.class);
        if (Objects.nonNull(beans)) {
            for (ContextObserver bean : beans) {
                try {
                    bean.doContext(context);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

    }

}
