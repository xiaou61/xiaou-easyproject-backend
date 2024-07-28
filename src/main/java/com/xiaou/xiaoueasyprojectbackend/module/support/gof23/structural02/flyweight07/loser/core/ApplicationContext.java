package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.core;

import cn.hutool.core.lang.ClassScanner;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("all")
public class ApplicationContext {

    private static Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    public void start() {
        Set<Class<?>> classes = ClassScanner.scanPackage();
        synchronized (ApplicationContext.class) {
            for (Class<?> aClass : classes) {
                if (aClass.isAnnotationPresent(Component.class)) {
                    try {
                        if (beanMap.containsKey(aClass)) {
                            continue;
                        }
                        doGetBean(aClass);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            ContextUtils.setApplicationContext(this);
        }

    }

    private Object doGetBean(Class<?> aClass) {

        try {
            Object o = aClass.newInstance();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object fileObject = beanMap.get(field.getType());
                    if (Objects.isNull(fileObject)) {
                        fileObject = doGetBean(field.getType());
                    }
                    field.setAccessible(true);
                    field.set(o, fileObject);
                }
            }
            beanMap.put(aClass, o);
            return o;
        } catch (Exception e) {
        }
        return null;

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
