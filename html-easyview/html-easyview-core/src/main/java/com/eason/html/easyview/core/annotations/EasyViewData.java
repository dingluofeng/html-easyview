package com.eason.html.easyview.core.annotations;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月26日 下午6:32:05
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月26日
 * @modify by reason:{方法名}:{原因}
 */
public class EasyViewData {

	public final Field field;

	public boolean searchView;

	public String type;

	public String name;

	/**
	 * @return key的列表。
	 */
	public String[] values;

	/**
	 * @return 对应上面values中key的要显示的lable 例如{"成功",失败",异常"}
	 */
	public String[] list;

	/**
	 * @return 数据提供器
	 */
	public Class<?> dataProvider;

	public boolean validate;

	public boolean readonly = false;

	public String dateRange;

	public boolean expandRowView = false;

	public boolean queryCondition = false;

	public EasyViewData(Field field, EasyView view) {
		super();
		this.field = field;
		this.type = view.type();
		this.name = view.name();
		this.list = view.list();
		this.values = view.values();
		this.validate = view.validate();
		this.readonly = view.readonly();
		this.dateRange = view.dateRange();
		this.dataProvider = view.dataProvider();
		this.expandRowView = view.expandRowView();
		this.queryCondition = view.queryCondition();
	}

	public EasyViewData(Field field, String type, String name) {
		super();
		this.field = field;
		this.type = type;
		this.name = name;
	}

	public String type() {
		return type;
	}

	public void type(String type) {
		this.type = type;
	}

	public String name() {
		return name;
	}

	public void name(String name) {
		this.name = name;
	}

	public String[] values() {
		return values;
	}

	public void values(String[] values) {
		this.values = values;
	}

	public String[] list() {
		return list;
	}

	public void list(String[] list) {
		this.list = list;
	}

	public Class<?> dataProvider() {
		return dataProvider;
	}

	public void dataProvider(Class<?> dataProvider) {
		this.dataProvider = dataProvider;
	}

	public String field() {
		return field.getName();
	}

	protected String getSearchId() {
		return "txt_search_" + field.getDeclaringClass().getSimpleName().toLowerCase() + "_" + field.getName();
	}

	protected String getInputId() {
		return "txt_input_" + field.getDeclaringClass().getSimpleName().toLowerCase() + "_" + field.getName();
	}

	public String id() {
		if (searchView) {
			return getSearchId();
		} else {
			return getInputId();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ViewControl [id=");
		builder.append(id());
		builder.append(", type=");
		builder.append(type);
		builder.append(", name=");
		builder.append(name);
		builder.append(", values=");
		builder.append(Arrays.toString(values));
		builder.append(", list=");
		builder.append(Arrays.toString(list));
		builder.append(", dataProvider=");
		builder.append(dataProvider);
		builder.append("]");
		return builder.toString();
	}

}
