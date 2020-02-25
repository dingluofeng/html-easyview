package com.eason.html.easyview.core.form.provider;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormInput;

public class WidgetsFactory {

	private static WidgetsFactory instance = new WidgetsFactory();

	private Map<String, IWidgetProvider> widgetProviders = new ConcurrentHashMap<>();

	public static WidgetsFactory getInstance() {
		return instance;
	}

	private WidgetsFactory() {
		addWidgetProvider(new TextProvider());
		addWidgetProvider(new NumberProvider());
		addWidgetProvider(new PasswordProvider());
		addWidgetProvider(new ComboProvider());
		addWidgetProvider(new DateTimePickerProvider("date"));
		addWidgetProvider(new DateTimePickerProvider("datetime"));
		addWidgetProvider(new DateTimePickerProvider("time"));
//		addWidgetProvider(new BitGroupProvider());
//		addWidgetProvider(new ButtonProvider());
//		addWidgetProvider(new CheckBoxProvider());
//		addWidgetProvider(new DateProvider());
//		addWidgetProvider(new FileProvider());
//		addWidgetProvider(new RadioBoxProvider());
//		addWidgetProvider(new SpinnerProvider());
//		addWidgetProvider(new TimeProvider());
//		addWidgetProvider(new CheckTextProvider());
//		addWidgetProvider(new DateTimeProvider());		
//		addWidgetProvider(new DateTimeSpanProvider());	
//		addWidgetProvider(new ButtonGroupsProvider());
	}

	public FormInput<?> create(Field field, Object value, EasyViewData view) {
		String type = view.type();
		IWidgetProvider widgetProvider = widgetProviders.get(type.toLowerCase());
		if (widgetProvider != null) {
			FormInput<?> create = widgetProvider.create(field, value, view);
			return create;
		}
		return null;
	}

	public void addWidgetProvider(IWidgetProvider widgetProvider) {
		widgetProviders.put(widgetProvider.getName().toLowerCase(), widgetProvider);
	}
}