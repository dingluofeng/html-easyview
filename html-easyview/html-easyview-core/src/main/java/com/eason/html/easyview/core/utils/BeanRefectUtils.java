/**
 * 
 */
package com.eason.html.easyview.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.eason.html.easyview.core.DateTimeInfo;
import com.eason.html.easyview.core.annotations.EasyView;
import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.annotations.TableColumns;
import com.eason.html.easyview.core.enums.Align;
import com.eason.html.easyview.core.enums.Valign;
import com.eason.html.easyview.core.form.DatetimeInput;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.provider.WidgetsFactory;
import com.eason.html.easyview.core.form.search.SearchFormGroup;
import com.eason.html.easyview.core.form.table.formatter.NoneTableColMappingFormatter;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatter;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatterManager;
import com.eason.html.easyview.core.form.table.model.SearchWidgetInfo;
import com.eason.html.easyview.core.form.table.model.TableColumnBuilder;
import com.eason.html.easyview.core.form.table.model.TableColumnBuilder.TableColumn;
import com.eason.html.easyview.core.logging.Log;
import com.eason.html.easyview.core.logging.LogFactory;

/**
 * @author dingluofeng
 *
 */
public class BeanRefectUtils {

	static final Log logger = LogFactory.getLog(BeanRefectUtils.class);

	private static Map<Method, List<TableColumn>> tableEntitiesMap = new ConcurrentHashMap<>();

	private static Map<Class<?>, SearchWidgetInfo> searchHtmlMap = new ConcurrentHashMap<>();

	public static void listClassFields(Class<?> beanClass, FieldCallback fieldCallback, FieldFilter fieldFilter) {
		Class<?> clazz = beanClass;
		// 判断是否存在父类
		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null) {
			// 遍历父类对象
			listFields(superclass, null, fieldCallback, fieldFilter);
		}
		// 遍历本身对象
		listFields(clazz, null, fieldCallback, fieldFilter);
	}

	private static void listFields(Class<?> clazz, Object data, FieldCallback fieldCallback, FieldFilter fieldFilter) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			Class<?> subClass = field.getType();
			Object value = null;
			if (data != null) {
				value = getValue(data, field);
			}
			if (isUserDefinedClass(subClass)) {
				listFields(subClass, value, fieldCallback, fieldFilter);
			} else if (fieldFilter == null || fieldFilter.accept(field, value)) {
				fieldCallback.doWith(field, value);
			}
		}
	}

	public static void listObjectFields(Object data, FieldCallback fieldCallback, FieldFilter fieldFilter) {
		AssertUtils.notNull(data, "data must not be null");
		Class<?> clazz = data.getClass();
		// 判断是否存在父类
		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null) {
			// 遍历父类对象
			listFields(superclass, data, fieldCallback, fieldFilter);
		}
		// 遍历本身对象
		listFields(clazz, data, fieldCallback, fieldFilter);
	}

	public static final List<TableColumn> parseColumns(Method method,
			final TableColMappingFormatterManager colMappingFormatterManager) {
		List<TableColumn> columns = tableEntitiesMap.get(method);
		if (CollectionUtils.isNotEmpty(columns)) {
			return columns;
		} else {
			logger.debugf("parseColumns:init method= %s", method);
			List<TableColumn> cols = new ArrayList<>();
			tableEntitiesMap.put(method, cols);
			TableColumns tableColumns = method.getAnnotation(TableColumns.class);
			if (tableColumns != null) {
				cols.addAll(parseColumnsFromAnnotations(tableColumns, colMappingFormatterManager));
			} else {
				Class<?> returnClass = getReturnClassFromMethod(method);
				if (isUserDefinedClass(returnClass)) {
					cols.addAll(parseColumnsFromReturnType(returnClass, colMappingFormatterManager));
				}
			}
			return cols;
		}
	}

	public final static List<TableColumn> parseColumnsfromMap(Method method, Map<Object, Object> dataMap) {
		List<TableColumn> columns = tableEntitiesMap.get(method);
		if (CollectionUtils.isNotEmpty(columns)) {
			return columns;
		} else {
			logger.debugf("parseColumnsfromMap init method= %s", method);
			final List<TableColumn> cols = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(dataMap)) {
                for (Entry<Object, Object> entry : dataMap.entrySet()) {
                    TableColumnBuilder tableColumn = TableColumnBuilder.newBuild();
                    String field = String.valueOf(entry.getKey());
                    tableColumn.field(field);
                    tableColumn.align(Align.CENTER.value);
                    tableColumn.title(field);
                    tableColumn.valign(Valign.MIDDLE.value);
                    cols.add(tableColumn.build());
                }
            }
			return cols;
		}
	}

	private final static List<TableColumn> parseColumnsFromAnnotations(TableColumns tableColumns,
			final TableColMappingFormatterManager colMappingFormatterManager) {
		List<TableColumn> cols = new ArrayList<>();
		com.eason.html.easyview.core.annotations.TableColumn[] columns_ = tableColumns.value();
		for (com.eason.html.easyview.core.annotations.TableColumn tableColumn : columns_) {
			TableColumnBuilder columnBuilder = TableColumnBuilder.newBuildWith(tableColumn);
			String mapping = tableColumn.mapping();
			if (StringUtils.isNotBlank(mapping)) {
				String formatter = colMappingFormatterManager.addMappingFormatter(tableColumn.field(), mapping);
				columnBuilder.formatter(formatter);
			}
			Class<? extends TableColMappingFormatter> mappingFormatter = tableColumn.mappingFormatter();
			if ((mappingFormatter != NoneTableColMappingFormatter.class) && colMappingFormatterManager != null) {
				String formatter = colMappingFormatterManager.addMappingFormatter(mappingFormatter);
				columnBuilder.formatter(formatter);
			}
			cols.add(columnBuilder.build());
		}
		return cols;
	}

	private final static List<TableColumn> parseColumnsFromReturnType(Class<?> returnClass,
			final TableColMappingFormatterManager colMappingFormatterManager) {
		final List<TableColumn> cols = new ArrayList<>();
		BeanRefectUtils.listClassFields(returnClass, new FieldCallback() {
			@Override
			public void doWith(Field field, Object fieldValue) {
				TableColumnBuilder tableColumn = TableColumnBuilder.newBuild();
				tableColumn.field(field.getName());
				EasyView view = field.getAnnotation(EasyView.class);
				if (view != null) {
					tableColumn.title(view.name());
					tableColumn.align(view.align().value);
					tableColumn.valign(view.valign().value);
					String mapping = view.mapping();
					if (StringUtils.isNotBlank(mapping)) {
						String formatter = colMappingFormatterManager.addMappingFormatter(field.getName(), mapping);
						tableColumn.formatter(formatter);
					}
					if ((view.mappingFormatter() != NoneTableColMappingFormatter.class)
							&& colMappingFormatterManager != null) {
						String formatter = colMappingFormatterManager.addMappingFormatter(view.mappingFormatter());
						tableColumn.formatter(formatter);
					}
					String colField = view.field();
					if (StringUtils.isBlank(colField)) {
						colField = field.getName();
					}
					tableColumn.field(field.getName());
					tableColumn.sortable(view.sortable());
				} else {
					tableColumn.title(field.getName());
				}
				if (view == null || !view.columnHidden()) {
					cols.add(tableColumn.build());
				}
			}
		}, new DefaultFieldFilter());
		return cols;
	}

	public final static Class<?> getReturnClassFromMethod(Method method) {
		Class<?> returnType = method.getReturnType();
		if (List.class.isAssignableFrom(returnType)) {
			Type genericReturnType = method.getGenericReturnType();
			if (genericReturnType instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				return Class.class.cast(actualTypeArguments[0]);
			}
		} else {
			return returnType;
		}
		return returnType;
	}

	public static final SearchWidgetInfo parseSearchForm(Class<?> beanClass) {
		if (beanClass == null) {
			return null;
		}
		SearchWidgetInfo searchWidgetInfo = searchHtmlMap.get(beanClass);
		if (searchWidgetInfo != null) {
			return searchWidgetInfo;
		} else {
			searchWidgetInfo = new SearchWidgetInfo();
			searchHtmlMap.put(beanClass, searchWidgetInfo);
		}
		final Set<DateTimeInfo> datetimeFields = new HashSet<>();
		final List<FormInput<?>> searchInputs = new ArrayList<>();
		String prefixBtn = beanClass.getSimpleName().toLowerCase();
		BeanRefectUtils.listClassFields(beanClass, new FieldCallback() {
			@Override
			public void doWith(Field field, Object fieldValue) {
				EasyView view = field.getAnnotation(EasyView.class);
				EasyViewData control = null;
				if (view != null) {
					String colField = view.field();
					if (StringUtils.isBlank(colField)) {
						colField = field.getName();
					}
					control = new EasyViewData(field, view);
				} else {
					if (field.getType().isPrimitive() || Number.class.isAssignableFrom(field.getType())) {
						control = new EasyViewData(field, "Number", field.getName());
					} else {
						control = new EasyViewData(field, "Text", field.getName());
					}
				}
				// if (control.queryCondition) {
				control.searchView = true;
				FormInput<?> formInput = WidgetsFactory.getInstance().create(field, fieldValue, control);
				searchInputs.add(formInput);
				if (formInput instanceof DatetimeInput) {
					String type = ((DatetimeInput) formInput).type();
					DateTimeInfo dateTimeInfo = new DateTimeInfo(formInput.getId(), type, control.dateRange);
					datetimeFields.add(dateTimeInfo);
				}
				// }
			}
		}, new DefaultFieldFilter());
		String html = SearchFormGroup.searchFormGroup(prefixBtn, searchInputs).html();
		searchWidgetInfo.searchHtml = html;
		searchWidgetInfo.datetimeFields.addAll(datetimeFields);
		return searchWidgetInfo;
	}

	public static String toSandardJsonString(String jsonString) {
		return JacksonUtils.toJsonString(JacksonUtils.parseObject(jsonString));
	}

	public static void main(String[] args) {
		System.out.println(toSandardJsonString("{'1':'男','0':'女'}"));
		System.out.println(toSandardJsonString("{1:'男',0:'女'}"));
	}

	public static Object getValue(Object data, Field field) {
		if (Modifier.isFinal(field.getModifiers()))
			return null;
		field.setAccessible(true);
		try {
			return field.get(data);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return getValue(data, field.getName());
	}

	public static Object getValue(Object data, String fieldName) {
		if (data == null) {
			return null;
		}
		String getMethod = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Method method;
		Object value = null;
		try {
			method = data.getClass().getMethod(getMethod);
			value = method.invoke(data);
		} catch (Exception e) {
			throw new RuntimeException((value != null ? value.getClass().getName() : fieldName) + "获取值错误。", e);
		}
		return value;
	}

	public final static boolean isUserDefinedClass(Class<?> clazz) {

		// isPrimitive
		if (clazz.isPrimitive()) {
			return false;
		}

		// Wrap Number
		if ((null != clazz.getSuperclass()) && Number.class.isAssignableFrom(clazz.getSuperclass())) {
			return false;
		}

		// 日期
		if ((Date.class == clazz) || Date.class.isAssignableFrom(clazz)) {
			return false;
		}
		// 字符串
		if (String.class == clazz) {
			return false;
		}

		// 数组
		if (clazz.isArray()) {
			return false;
		}

		// 布尔值
		if ((Boolean.class == clazz) || Boolean.class.isAssignableFrom(clazz)) {
			return false;
		}

		// Collection
		if (Collection.class.isAssignableFrom(clazz)) {
			return false;
		}
		// Map
		if (Map.class.isAssignableFrom(clazz)) {
			return false;
		}
		// 枚举
		if (clazz.isEnum()) {
			return false;
		}

		return true;
	}

	/**
	 * Callback interface invoked on each field in the hierarchy.
	 */
	public interface FieldCallback {

		/**
		 * Perform an operation using the given field.
		 * 
		 * @param field      the field to operate on
		 * @param fieldValue the value of the field
		 */
		void doWith(Field field, Object fieldValue);
	}

	/**
	 * Callback optionally used to filter fields to be operated on by a field
	 * callback.
	 */
	public interface FieldFilter {

		/**
		 * Determine whether the given field matches.
		 * 
		 * @param field      the field to check
		 * @param fieldValue the value of the field
		 */
		boolean accept(Field field, Object fieldValue);
	}
}
