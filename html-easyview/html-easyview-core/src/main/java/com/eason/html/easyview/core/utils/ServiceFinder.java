package com.eason.html.easyview.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月26日 下午8:08:47
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月26日
 * @modify by reason:{方法名}:{原因}
 */
public class ServiceFinder implements ApplicationContextAware {

	private static ApplicationContext s_applicationContext;

	public static Object getBean(String beanName) {
		if (s_applicationContext == null) {
			throw new IllegalStateException("ServiceFinder not initialized");
		}
		return s_applicationContext.getBean(beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		s_applicationContext = applicationContext;
	}

}
