package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.singleton01.loser.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义一个注解 标注这个bean需要被我们的容器统一接管
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}
