package com.eason.html.easyview.core.form.provider;

import java.lang.reflect.Field;

import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormInput;

public interface IWidgetProvider {

	public String getName();

	public FormInput<?> create(Field field, Object value, EasyViewData view);
}