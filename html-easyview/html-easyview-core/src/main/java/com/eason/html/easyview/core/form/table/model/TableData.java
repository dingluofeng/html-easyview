package com.eason.html.easyview.core.form.table.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eason.html.easyview.core.DateTimeInfo;
import com.eason.html.easyview.core.annotations.EasyView;
import com.eason.html.easyview.core.annotations.EasyViewData;
import com.eason.html.easyview.core.form.DatetimeInput;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.provider.WidgetsFactory;
import com.eason.html.easyview.core.form.table.TableItemLink;
import com.eason.html.easyview.core.form.table.formatter.NoneTableColMappingFormatter;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatterManager;
import com.eason.html.easyview.core.form.table.model.TableColumnBuilder.TableColumn;
import com.eason.html.easyview.core.form.validate.Validate;
import com.eason.html.easyview.core.utils.BeanRefectUtils;
import com.eason.html.easyview.core.utils.BeanRefectUtils.FieldCallback;
import com.eason.html.easyview.core.utils.DefaultFieldFilter;
import com.eason.html.easyview.core.utils.StringUtils;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月22日 上午11:29:05
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月22日
 * @modify by reason:{方法名}:{原因}
 */
public class TableData {

	public String tableTitle;

	public boolean escape;

	public String btnPrefix;

	public String tableToolbar;

	public String tableId;

	public String baseUrl;

	public String uniqueId;
	
	public int pageSize = 10;

	public final List<TableColumn> columns = new ArrayList<>();

	public final List<FormInput<?>> searchInputs = new ArrayList<>();

	public final List<FormInput<?>> formInputs = new ArrayList<>();

	public final Set<DateTimeInfo> datetimeFields = new HashSet<>();

	public final List<TableItemLink> customItemLinks = new ArrayList<>();

	public List<?> rows;

	public boolean cardView = false;

	private TableColMappingFormatterManager formatterManager;

	public TableData(Class<?> coClass, Class<?> beanClass, TableColMappingFormatterManager formatterManager) {
		super();
		this.formatterManager = formatterManager;
		if (coClass != null) {
			parseSearch(coClass);
		}
		if (beanClass != null) {
			parseColumns(beanClass);
		}
	}

	private final void parseSearch(Class<?> beanClass) {
		BeanRefectUtils.listClassFields(beanClass, new FieldCallback() {
			@Override
			public void doWith(Field field, Object fieldValue) {
				EasyView view = field.getAnnotation(EasyView.class);
				EasyViewData control = null;
				if (view != null) {
					control = new EasyViewData(field, view);
				} else {
					if (field.getType().isPrimitive() || Number.class.isAssignableFrom(field.getType())) {
						control = new EasyViewData(field, "Number", field.getName());
					} else if (Map.class.isAssignableFrom(field.getType())
							|| List.class.isAssignableFrom(field.getType())) {
						return;
					} else {
						control = new EasyViewData(field, "Text", field.getName());
					}
				}
				if (control.queryCondition) {
					control.searchView = true;
					FormInput<?> formInput = WidgetsFactory.getInstance().create(field, fieldValue, control);
					searchInputs.add(formInput);
					if (formInput instanceof DatetimeInput) {
						String type = ((DatetimeInput) formInput).type();
						DateTimeInfo dateTimeInfo = new DateTimeInfo(formInput.getId(), type, control.dateRange);
						datetimeFields.add(dateTimeInfo);
					}
				}
			}
		}, new DefaultFieldFilter());
	}

	private final void parseColumns(Class<?> beanClass) {
		btnPrefix = beanClass.getSimpleName().toLowerCase();
		tableId = btnPrefix + "_table";
		tableToolbar = btnPrefix + "_toolbar";
		EasyView easyView = beanClass.getAnnotation(EasyView.class);
		if (easyView != null) {
			tableTitle = easyView.name();
			cardView = easyView.cardView();
			if (!cardView && easyView.checkbox()) {
				columns.add(TableColumnBuilder.newCheckColumn());
			}
			if (!cardView && easyView.indexed()) {
				columns.add(TableColumnBuilder.newIndexColumn());
			}
			escape = easyView.escape();
			// sortable = easyView.sortable();
		} else {
			tableTitle = beanClass.getSimpleName().toLowerCase();
		}
		BeanRefectUtils.listClassFields(beanClass, new FieldCallback() {
			@Override
			public void doWith(Field field, Object fieldValue) {
				TableColumnBuilder tableColumn = TableColumnBuilder.newBuild();
				tableColumn.field(field.getName());
				EasyView view = field.getAnnotation(EasyView.class);
				EasyViewData control = null;
				boolean hiddenColumn = false;
				if (uniqueId == null) {
					uniqueId = field.getName();
				}
				if (view != null) {
					tableColumn.title(view.name());
					tableColumn.align(view.align().value);
					tableColumn.valign(view.valign().value);
					if ((view.mappingFormatter() != NoneTableColMappingFormatter.class) && formatterManager != null) {
						String formatter = formatterManager.addMappingFormatter(view.mappingFormatter());
						tableColumn.formatter(formatter);
					}
					String colField = view.field();
					if (StringUtils.isBlank(colField)) {
						colField = field.getName();
					}
					tableColumn.field(field.getName());
					tableColumn.sortable(view.sortable());
					if (view.uniqueId()) {
						uniqueId = colField;
					}
					if (!view.columnHidden()) {
						control = new EasyViewData(field, view);
					} else {
						hiddenColumn = true;
					}
					if (view.expandRowView()) {
						hiddenColumn = true;
					}
				} else {
					tableColumn.title(field.getName());
					if (field.getType().isPrimitive() || Number.class.isAssignableFrom(field.getType())) {
						control = new EasyViewData(field, "Number", field.getName());
					} else {
						control = new EasyViewData(field, "Text", field.getName());
						if (Map.class.isAssignableFrom(field.getType())) {
							control.expandRowView = true;
							hiddenColumn = true;
						}
					}
				}
				if (!hiddenColumn) {
					columns.add(tableColumn.build());
					control.searchView = false;
					FormInput<?> formInput = WidgetsFactory.getInstance().create(field, fieldValue, control);
					if (control.validate && formInput != null) {
						formInput.setValidate(new Validate());
					}
					if (control.readonly || StringUtils.equalsIgnoreCase(uniqueId, field.getName())) {
						formInput.readonly(true);
					}
					formInputs.add(formInput);
					if (formInput instanceof DatetimeInput) {
						String type = ((DatetimeInput) formInput).type();
						DateTimeInfo dateTimeInfo = new DateTimeInfo(formInput.getId(), type, control.dateRange);
						datetimeFields.add(dateTimeInfo);
					}
				}
			}
		}, new DefaultFieldFilter());
		// itemOpt
		if (easyView != null && easyView.itemOpt()) {
			columns.add(TableColumnBuilder.newItemOptColumn());
		}
	}
}
