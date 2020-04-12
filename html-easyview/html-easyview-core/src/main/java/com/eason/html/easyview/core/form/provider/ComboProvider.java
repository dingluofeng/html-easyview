package com.eason.html.easyview.core.form.provider;

import java.lang.reflect.Field;

import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormCombo;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.utils.ServiceFinder;

public class ComboProvider implements IWidgetProvider {

	private ServiceFinder serviceFinder;

	@Override
	public String getName() {
		return "combo";
	}

	@Override
	public FormInput<?> create(Field field, Object value, EasyViewData view) {
		String[] names = null;
		String[] values = null;
		if (view.dataProvider() != Object.class) {
			Class<?> cls = view.dataProvider();
			try {
				Object instance = cls.newInstance();
				if (serviceFinder != null) {
					serviceFinder.injectExtension(instance);
				}
				if (instance instanceof IComboDataProvider) {
					IComboDataProvider dataProvider = (IComboDataProvider) instance;
					return FormCombo.of(view.id(), view.field(), view.name(), dataProvider);
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

	public ServiceFinder getServiceFinder() {
		return serviceFinder;
	}

	public void setServiceFinder(ServiceFinder serviceFinder) {
		this.serviceFinder = serviceFinder;
	}

}
