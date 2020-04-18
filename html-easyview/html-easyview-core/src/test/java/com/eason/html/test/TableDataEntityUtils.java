package com.eason.html.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eason.html.easyview.core.annotations.EasyView;
import com.eason.html.easyview.core.statictable.model.StaticTableData;
import com.eason.html.easyview.core.statictable.model.StaticTableData.StaticTableColumn;
import com.eason.html.easyview.core.statictable.model.StaticTableData.StaticTableRow;
import com.eason.html.easyview.core.statictable.model.StaticTableData.TableCell;
import com.eason.html.easyview.core.statictable.model.StaticTableData.TableDataBuilder;
import com.eason.html.easyview.core.utils.AnnotationFieldFilter;
import com.eason.html.easyview.core.utils.BeanRefectUtils;
import com.eason.html.easyview.core.utils.BeanRefectUtils.FieldCallback;
import com.eason.html.easyview.core.utils.BeanRefectUtils.FieldFilter;
import com.eason.html.easyview.core.utils.CollectionUtils;
import com.eason.html.easyview.core.utils.DefaultFieldFilter;

public class TableDataEntityUtils {

	public static final StaticTableData parseRowEntities(List<?> rowEntities) {
		if (CollectionUtils.isEmpty(rowEntities)) {
			return null;
		}
		TableDataBuilder tableDataBuilder = TableDataBuilder.newBuilder();
		Class<? extends Object> rowClass = rowEntities.get(0).getClass();
		FieldFilter fieldFilter = new DefaultFieldFilter();
		// build columns
		if (BeanRefectUtils.isUserDefinedClass(rowClass)) {
			EasyView annotation = rowClass.getAnnotation(EasyView.class);
			if (annotation != null) {
				tableDataBuilder.title(annotation.name());
				fieldFilter = new AnnotationFieldFilter<>(EasyView.class);
			}
			List<StaticTableColumn> tableColumns = getTableColumns(rowClass, fieldFilter);
			tableDataBuilder.columns(tableColumns);
			for (Object rowData : rowEntities) {
				StaticTableRow tableRow = getRow(fieldFilter, rowData);
				tableDataBuilder.addRow(tableRow);
			}
		} else if (rowClass.isArray()) {
			int colspan = ((Object[]) rowEntities.get(0)).length;
			List<StaticTableColumn> tableColumns = new ArrayList<>();
			tableColumns.add(new StaticTableColumn("Value", colspan));
			tableDataBuilder.columns(tableColumns);
			for (Object rowData : rowEntities) {
				List<TableCell> tableCells = new ArrayList<>();
				for (Object cellData : (Object[]) rowData) {
					tableCells.add(new TableCell(cellData));
				}
				tableDataBuilder.addRow(new StaticTableRow(tableCells));
			}
		} else if (Map.class.isAssignableFrom(rowClass)) {
			@SuppressWarnings("unchecked")
			Map<Object, Object> map = ((Map<Object, Object>) rowEntities.get(0));
			Set<Object> keySet = map.keySet();
			String[] columnTitles = keySet.toArray(new String[keySet.size()]);
			tableDataBuilder.columnTitles(columnTitles);
			for (Object rowData : rowEntities) {
				@SuppressWarnings("unchecked")
				Map<Object, Object> rowMap = (Map<Object, Object>) rowData;
				List<TableCell> tableCells = new ArrayList<>();
				for (String cellData : columnTitles) {
					tableCells.add(new TableCell(rowMap.get(cellData)));
				}
				tableDataBuilder.addRow(new StaticTableRow(tableCells));
			}
		} else {
			List<StaticTableColumn> tableColumns = new ArrayList<>();
			tableColumns.add(new StaticTableColumn("Value", 1));
			tableDataBuilder.columns(tableColumns);
			for (Object rowData : rowEntities) {
				List<TableCell> tableCells = new ArrayList<>();
				tableCells.add(new TableCell(rowData));
				tableDataBuilder.addRow(new StaticTableRow(tableCells));
			}
		}
		return tableDataBuilder.build();
	}

	private static final StaticTableData parseRowEntity2KeyValue(Object rowEntity) {
		if (rowEntity == null) {
			return null;
		}
		final TableDataBuilder tableDataBuilder = TableDataBuilder.newBuilder();
		Class<? extends Object> beanClass = rowEntity.getClass();
		EasyView annotation = beanClass.getAnnotation(EasyView.class);
		FieldFilter fieldFilter = null;
		if (annotation != null) {
			tableDataBuilder.title(annotation.name());
			fieldFilter = new AnnotationFieldFilter<>(EasyView.class);
		} else {
			fieldFilter = new DefaultFieldFilter();
		}
		tableDataBuilder.columnTitles(new String[] { "FieldName", "FieldValue" });

		BeanRefectUtils.listObjectFields(rowEntity, new FieldCallback() {
			@Override
			public void doWith(Field field, Object fieldValue) {
				final List<TableCell> rowCells = new ArrayList<>();
				EasyView view = field.getAnnotation(EasyView.class);
				if (view != null) {
					rowCells.add(new TableCell(view.name()));
				} else {
					rowCells.add(new TableCell(field.getName()));
				}
				rowCells.add(new TableCell(fieldValue));
				tableDataBuilder.addRow(new StaticTableRow(rowCells));
			}
		}, fieldFilter);
		return tableDataBuilder.build();
	}

	private static StaticTableRow getRow(FieldFilter fieldFilter, Object rowData) {
		final List<TableCell> rowCells = new ArrayList<>();
		BeanRefectUtils.listObjectFields(rowData, new FieldCallback() {
			@Override
			public void doWith(Field field, Object fieldValue) {
				Class<?> type = field.getType();
				if (Collection.class.isAssignableFrom(type)) {
//					ParameterizedType genericType = (ParameterizedType)field.getGenericType();
//					Class<?> rowClass=Class.class.cast(genericType.getActualTypeArguments()[0]);
//					if (BeanRefectUtils.isUserDefinedClass(rowClass)) {
					StaticTableData innerTableData = parseRowEntities((List<?>) fieldValue);
					if (innerTableData != null) {
						rowCells.add(new TableCell(innerTableData));
					}
//					}else {
//						rowCells.add(new TableCell(fieldValue));
//					}
				} else if (BeanRefectUtils.isUserDefinedClass(type)) {
					StaticTableData innerTableData = parseRowEntity2KeyValue(fieldValue);
					if (innerTableData != null) {
						rowCells.add(new TableCell(innerTableData));
					}
				} else {
					rowCells.add(new TableCell(fieldValue));
				}
			}
		}, fieldFilter);
		return new StaticTableRow(rowCells);
	}

	private static List<StaticTableColumn> getTableColumns(Class<?> beanClass, FieldFilter fieldFilter) {
		final List<StaticTableColumn> columns = new ArrayList<>();
		BeanRefectUtils.listClassFields(beanClass, new FieldCallback() {
			@Override
			public void doWith(Field field, Object fieldValue) {
				EasyView column = field.getAnnotation(EasyView.class);
				if (column != null) {
					columns.add(new StaticTableColumn(column.name()));
				} else {
					columns.add(new StaticTableColumn(field.getName()));
				}
			}
		}, fieldFilter);
		return columns;
	}

}
