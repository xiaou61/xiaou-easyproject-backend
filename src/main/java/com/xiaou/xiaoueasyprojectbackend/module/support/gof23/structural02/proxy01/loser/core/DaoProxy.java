package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.proxy01.loser.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DaoProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Select select = method.getAnnotation(Select.class);
        String sql = select.value();
        System.out.println("获取sql: " + sql);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        int index = 0;
        for (Annotation[] annotation : parameterAnnotations) {
            for (Annotation paramAnno : annotation) {
                if (paramAnno instanceof Param) {
                    String value = ((Param) paramAnno).value();
                    System.out.println("获取参数: " + value + " 值: " + args[index]);
                }
            }
            index++;
        }
        Class<?> returnType = method.getReturnType();
        System.out.println("获取返回值类型: " + returnType.getName());
        return returnType.newInstance();

    }

}
