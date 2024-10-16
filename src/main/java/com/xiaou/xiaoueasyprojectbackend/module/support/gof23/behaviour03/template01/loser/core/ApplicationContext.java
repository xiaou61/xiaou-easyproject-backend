package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.core;

import cn.hutool.core.lang.ClassScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("all")
public class ApplicationContext {

    private Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    public void start() {
        Set<Class<?>> classes = ClassScanner.scanPackage();
        for (Class<?> aClass : classes) {
            if (aClass.isAnnotationPresent(Component.class)) {
                try {
                    beanMap.put(aClass, aClass.newInstance());
                } catch (Exception ignore) {
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

}
