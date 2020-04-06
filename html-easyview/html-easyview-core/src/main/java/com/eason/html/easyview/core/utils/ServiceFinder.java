package com.eason.html.easyview.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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

	private ApplicationContext applicationContext;

	public Object getBean(Class<?> type, String beanName) {
		if (applicationContext == null) {
			throw new IllegalStateException("ServiceFinder not initialized");
		}
		if (applicationContext.containsBean(beanName)) {
			return applicationContext.getBean(beanName);
		} else {
			return applicationContext.getBean(type);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void injectExtension(Object dataProvider) {
		Class<?> clazz = dataProvider.getClass();
		for (Method method : clazz.getMethods()) {
			if (method.getName().startsWith("set") && method.getParameterTypes().length == 1
					&& Modifier.isPublic(method.getModifiers())) {
				String property = method.getName().length() > 3
						? method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4)
						: "";
				Class<?>[] parameterTypes = method.getParameterTypes();
				try {
					Object bean = getBean(parameterTypes[0], property);
					makeAccessible(method);
					method.invoke(dataProvider, bean);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private final void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

}
