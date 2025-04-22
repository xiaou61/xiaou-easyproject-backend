package com.xiaou.spring.beans.factory;

import com.xiaou.spring.beans.exception.BeansException;

public interface BeanFactory {

	/**
	 * 获取bean
	 *
	 * @param name
	 * @return
	 * @throws BeansException bean不存在时
	 */
	Object getBean(String name) throws BeansException;
}
