package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.singleton01.loser.core;

import lombok.Data;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟一个简单的spring容器
 * 体验ioc控制反转的思想
 */
@Data
public class ApplicationContext {

    /**
     * bean的定义新手
     */
    private final Map<Class<?>, Definition> definitionMap = new ConcurrentHashMap<>();

    /**
     * 已经初始化号的bean容器
     */
    private final Map<Class<?>, Object> targetMap = new ConcurrentHashMap<>();

    /**
     * 扫描需要注册的bean
     */
    public ApplicationContext(Set<Class<?>> classes, boolean isLazy) {

        for (Class<?> target : classes) {
            if (target.isAnnotationPresent(Component.class)) {
                Definition definition = new Definition();
                definition.setTargetClazz(target);
                definition.setTargetName(target.getSimpleName());
                this.register(target, definition, isLazy);
            }
        }

    }

    /**
     * 将bean注册到容器中
     */
    public void register(Class<?> clazz, Definition definition, boolean isLazy) {

        if (definitionMap.containsKey(clazz)) {
            System.out.println("class is exist");
            return;
        }
        definitionMap.put(clazz, definition);
        if (!isLazy) {
            getBean(clazz);
        }

    }

    /**
     * 从容器中获取一个bean
     */
    public <T> T getBean(Class<T> tClass) {

        Object obj = targetMap.get(tClass);
        if (Objects.nonNull(obj)) {
            return (T) obj;
        }
        return (T) doCreateBean(tClass);

    }

    /**
     * 初始化一个bean
     */
    public Object doCreateBean(Class<?> tClass) {

        synchronized (ApplicationContext.class) {
            Object target = targetMap.get(tClass);
            if (Objects.nonNull(target)) {
                return target;
            }
            try {
                target = tClass.newInstance();
                targetMap.put(tClass, target);
                return target;
            } catch (Exception ignore) {
            }
            throw new RuntimeException("newInstance error");
        }

    }

}
