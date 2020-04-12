package com.eason.html.easyview.core.form.provider;

import java.lang.reflect.Field;

import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormDateTimePicker;
import com.eason.html.easyview.core.form.FormInput;

public class DateTimePickerProvider implements IWidgetProvider {

	private final String name;

	public DateTimePickerProvider(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public FormInput<?> create(Field field, Object value, EasyViewData view) {
		return FormDateTimePicker.of(view.id(), view.field(), view.name(), name);
	}

}
