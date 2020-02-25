package com.eason.html.easyview.core.form.provider;

import java.lang.reflect.Field;

import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.FormPassword;

public class PasswordProvider implements IWidgetProvider {

	@Override
	public String getName() {
		return "password";
	}

	@Override
	public FormInput<?> create(Field field, Object value, EasyViewData view) {
		return FormPassword.of(view.id(), view.field(), view.name());
	}

}
