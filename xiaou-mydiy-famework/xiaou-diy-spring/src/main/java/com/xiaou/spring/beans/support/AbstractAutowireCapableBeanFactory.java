package com.xiaou.spring.beans.support;

import com.xiaou.spring.beans.config.BeanDefinition;
import com.xiaou.spring.beans.exception.BeansException;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
		return doCreateBean(beanName, beanDefinition);
	}

	protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
		Class beanClass = beanDefinition.getBeanClass();
		Object bean;
		try {
			bean = beanClass.newInstance(); // Java 8 写法，Spring 里会使用更安全的方式
		} catch (Exception e) {
			throw new BeansException("Instantiation of bean failed", e);
		}
		addSingleton(beanName, bean);
		return bean;
	}
}