package com.eason.html.easyview.core.form.provider;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.annotation.Resource;

import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormCombo;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.utils.ServiceFinder;
import com.eason.html.easyview.core.utils.StringUtils;

public class ComboProvider implements IWidgetProvider {

	@Override
	public String getName() {
		return "combo";
	}

	@Override
	public FormInput<?> create(Field field, Object value,EasyViewData view) {
		String[] names = null;
		String[] values = null;
		if (view.dataProvider() != Object.class) {
			Class<?> cls = view.dataProvider();
			try {
				Object obj = cls.newInstance();
				if (obj instanceof IComboDataProvider) {
					IComboDataProvider dataProvider = (IComboDataProvider) obj;
					if (dataProvider.getItem() != null) {
						names = dataProvider.getItem();
					}
					if (dataProvider.getValue() != null) {
						values = (dataProvider.getValue());
					}
				}
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			}
		} else {
			if (view.list() != null) {
				names = (view.list());
			}
			if (view.values() != null) {
				values = (view.values());
			}
		}

		return FormCombo.of(view.id(), view.field(), view.name(), names, values);
	}

	public void injectExtension(Object dataProvider) {
		Class<?> clazz = dataProvider.getClass();
		for (Method method : clazz.getMethods()) {
			String property = getProperty(method);
			if (StringUtils.isNotBlank(property) && method.getParameterTypes().length == 1
					&& Modifier.isPublic(method.getModifiers())) {
				Object bean = ServiceFinder.getBean(property);
				makeAccessible(method);
				try {
					method.invoke(dataProvider, bean);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private String getProperty(Method method) {
		Resource resource = method.getAnnotation(Resource.class);
		String property = null;
		if (resource != null) {
			property = resource.name();
			if (StringUtils.isBlank(property)) {
				property = method.getName().length() > 3
						? method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4)
						: "";
			}
		}
		return property;
	}

	private final void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

}
