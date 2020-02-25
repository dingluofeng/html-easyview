package com.eason.html.easyview.core.form.provider;

import java.lang.reflect.Field;

import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.FormNumber;

public class NumberProvider implements IWidgetProvider {

	@Override
	public String getName() {
		return "number";
	}

	@Override
	public FormInput<?> create(Field field, Object value, EasyViewData view) {
		return FormNumber.of(view.id(), view.field(), view.name());
	}

}
