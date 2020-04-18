package com.eason.html.easyview.core.form.provider;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormCombo;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.utils.JacksonUtils;
import com.eason.html.easyview.core.utils.ServiceFinder;
import com.eason.html.easyview.core.utils.StringUtils;

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
		Class<?> dataProvider = view.dataProvider();
		String jsonMapping=view.mapping();
		if (dataProvider != Object.class) {
			try {
				Object instance = dataProvider.newInstance();
				if (serviceFinder != null) {
					serviceFinder.injectExtension(instance);
				}
				if (instance instanceof IComboDataProvider) {
					IComboDataProvider comboDataProvider = (IComboDataProvider) instance;
					return FormCombo.of(view.id(), view.field(), view.name(), comboDataProvider);
				}
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			}
		} else if(StringUtils.isNotBlank(jsonMapping)) {
			Map<String, Object> parseObject = JacksonUtils.parseObject(jsonMapping);
			int index=0;
			names=new String[parseObject.size()];
			values=new String[parseObject.size()];
			for (Entry<String, Object> entry : parseObject.entrySet()) {
				values[index]=entry.getKey();
				names[index]=entry.getValue()+"";
				index++;
			}
		} else {
			if (view.list() != null) {
				names = view.list();
			}
			if (view.values() != null) {
				values = view.values();
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
