package com.xiaou.spring.beans.config;

public class BeanDefinition {
	private Class beanClass;

	public BeanDefinition(Class beanClass) {
		this.beanClass = beanClass;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}
}