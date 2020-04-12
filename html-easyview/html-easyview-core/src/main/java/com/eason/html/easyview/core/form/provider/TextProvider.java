package com.eason.html.easyview.core.form.provider;

import java.lang.reflect.Field;

import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.FormText;

public class TextProvider implements IWidgetProvider {

	@Override
	public String getName() {
		return "text";
	}

	@Override
	public FormInput<?> create(Field field, Object value, EasyViewData view) {
		return FormText.of(view.id(), view.field(), view.name());
	}

}
