package com.xiaou.spring.beans.config;

public interface SingletonBeanRegistry {
	Object getSingleton(String beanName);
}