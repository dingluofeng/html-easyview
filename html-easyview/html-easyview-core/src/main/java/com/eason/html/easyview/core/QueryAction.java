/**
 * 
 */
package com.eason.html.easyview.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.eason.html.easyview.core.annotations.EasyView;
import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.provider.WidgetsFactory;
import com.eason.html.easyview.core.utils.BeanRefectUtils;
import com.eason.html.easyview.core.utils.BeanRefectUtils.FieldCallback;
import com.eason.html.easyview.core.utils.DefaultFieldFilter;
import com.eason.html.easyview.core.utils.StringUtils;

/**
 * @author dingluofeng
 *
 */
public class QueryAction implements IAction {

	private String id;

	private String name;

	private String url;

	private Class<?> searchCondition;

	private String customBtnId;

	private List<FormInput<?>> searchInputs = new ArrayList<>();;

	public QueryAction(String id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public String id() {
		return id;
	}

	public void id(String id) {
		this.id = id;
	}

	public String name() {
		return name;
	}

	public void name(String name) {
		this.name = name;
	}

	public void url(String url) {
		this.url = url;
	}

	public String url() {
		return url;
	}

	public void setSearchCondition(Class<?> searchCondition) {
		this.searchCondition = searchCondition;
		parseSearchForm(searchCondition);
	}

	private final void parseSearchForm(Class<?> beanClass) {
		String btnPrefix = beanClass.getSimpleName().toLowerCase();
		customBtnId = btnPrefix + "_custom_btn_search";
		BeanRefectUtils.listClassFields(beanClass, new FieldCallback() {
			@Override
			public void doWith(Field field, Object fieldValue) {
				EasyView view = field.getAnnotation(EasyView.class);
				EasyViewData viewData = null;
				if (view != null) {
					String colField = view.field();
					if (StringUtils.isBlank(colField)) {
						colField = field.getName();
					}
					viewData = new EasyViewData(field.getName(), view);
				} else {
					if (field.getType().isPrimitive() || Number.class.isAssignableFrom(field.getType())) {
						viewData = new EasyViewData(field.getName(), "Number", field.getName());
					} else {
						viewData = new EasyViewData(field.getName(), "Text", field.getName());
					}
				}
				if (viewData.queryCondition) {
					viewData.searchView = true;
					FormInput<?> formInput = WidgetsFactory.getInstance().create(field, fieldValue, viewData);
					searchInputs.add(formInput);
				}
			}
		}, new DefaultFieldFilter());
	}

	@Override
	public String toString() {
		return "QueryAction [id=" + id + ", name=" + name + ", url=" + url + ", searchCondition=" + searchCondition
				+ "]";
	}

	public String getCustomBtnId() {
		return customBtnId;
	}

	public List<FormInput<?>> getSearchInputs() {
		return searchInputs;
	}

}
