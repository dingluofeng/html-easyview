package com.eason.html.easyview.core.statictable.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eason.html.easyview.core.annotations.EasyView;
import com.eason.html.easyview.core.utils.AnnotationFieldFilter;
import com.eason.html.easyview.core.utils.BeanRefectUtils;
import com.eason.html.easyview.core.utils.BeanRefectUtils.FieldCallback;
import com.eason.html.easyview.core.utils.BeanRefectUtils.FieldFilter;
import com.eason.html.easyview.core.utils.CollectionUtils;
import com.eason.html.easyview.core.utils.DefaultFieldFilter;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月10日 下午12:56:15
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月10日
 * @modify by reason:{方法名}:{原因}
 */
public class StaticTableData {

	private String tableTitle;

	private final List<StaticTableColumn> columnTitles=new ArrayList<>();

	private final List<StaticTableRow> rows = new ArrayList<>();

	private StaticTableData() {
		super();
	}

	public List<StaticTableRow> getRows() {
		return rows;
	}
	
	public void addRow(StaticTableRow tableRow) {
		this.rows.add(tableRow);
	}

	public String getTableTitle() {
		return tableTitle;
	}

	public List<StaticTableColumn> getColumnTitles() {
		return columnTitles;
	}
	
	public void addTableColumn(StaticTableColumn tableColumn) {
		columnTitles.add(tableColumn);
	}

	public boolean hasData() {
		return rows != null && !rows.isEmpty();
	}
	
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("StaticTableData [tableTitle=");
		builder.append(tableTitle);
		builder.append(", columnTitles=");
		builder.append(columnTitles != null ? columnTitles.subList(0, Math.min(columnTitles.size(), maxLen)) : null);
		builder.append(", rows=");
		builder.append(rows != null ? rows.subList(0, Math.min(rows.size(), maxLen)) : null);
		builder.append("]");
		return builder.toString();
	}

	public final static class TableDataBuilder {

		StaticTableData tableData;

        private String undefinedText = "-";

		private TableDataBuilder() {
			super();
		}

		public static TableDataBuilder newBuilder() {
			TableDataBuilder builder = new TableDataBuilder();
			builder.tableData = new StaticTableData();
			return builder;
		}

		public static TableDataBuilder newBuilderWithRowEntities(List<?> rowEntities) {
			TableDataBuilder builder = new TableDataBuilder();
			StaticTableData tableData = builder.parseRowEntities(rowEntities);
			if (tableData!=null) {
				builder.tableData=tableData;
			}else {
				throw new IllegalArgumentException("rowEntities is empty");
			}
			return builder;
		}

        public TableDataBuilder undefinedText(String undefinedText) {
            this.undefinedText = undefinedText;
            return this;
        }

		public TableDataBuilder title(String tableTitle) {
			this.tableData.tableTitle = tableTitle;
			return this;
		}

		public TableDataBuilder columnTitles(Object[] columnTitles) {
			for (Object columnTitle : columnTitles) {
				this.tableData.addTableColumn(new StaticTableColumn(String.valueOf(columnTitle)));
			}
			return this;
		}
		
		public TableDataBuilder columns(List<StaticTableColumn> columns) {
			for (StaticTableColumn tableColumn : columns) {
				this.tableData.addTableColumn(tableColumn);
			}
			return this;
		}
		
		public TableDataBuilder rows(List<Object[]> rows) {
			for (Object[] rowData : rows) {
				List<TableCell> tableCells=new ArrayList<>();
				for (Object cellData : rowData) {
					tableCells.add(new TableCell(cellData));
				}
				StaticTableRow row=new StaticTableRow(tableCells);
				this.tableData.addRow(row);
			}
			return this;
		}

		public TableDataBuilder tableRows(List<StaticTableRow> rows) {
			for (StaticTableRow row : rows) {
				this.tableData.addRow(row);
			}
			return this;
		}
		
		public TableDataBuilder addRow(StaticTableRow row) {
			this.tableData.addRow(row);
			return this;
		}
		
		public StaticTableData build() {
			return tableData;
		}
		
		public final StaticTableData parseRowEntities(List<?> rowEntities) {
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
                // calc max Colunms
                int colspan = 1;
                for (Object rowData_ : rowEntities) {
                    int maxLength = ((Object[]) rowData_).length;
                    if (maxLength > colspan) {
                        colspan = maxLength;
                    }
                }

				List<StaticTableColumn> tableColumns = new ArrayList<>();
				tableColumns.add(new StaticTableColumn("Value", colspan));
				tableDataBuilder.columns(tableColumns);
				for (Object rowData : rowEntities) {
					List<TableCell> tableCells = new ArrayList<>();
                    Object[] rowItems = (Object[]) rowData;
                    for (int i = 0; i < colspan; i++) {
                        if (i < rowItems.length) {
                            tableCells.add(new TableCell(rowItems[i]));
                        } else {
                            tableCells.add(new TableCell(undefinedText));
                        }
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

		private final StaticTableData parseRowEntity2KeyValue(Object rowEntity) {
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

		private final StaticTableRow getRow(FieldFilter fieldFilter, Object rowData) {
			final List<TableCell> rowCells = new ArrayList<>();
			BeanRefectUtils.listObjectFields(rowData, new FieldCallback() {
				@Override
				public void doWith(Field field, Object fieldValue) {
					Class<?> type = field.getType();
					if (Collection.class.isAssignableFrom(type)) {
//						ParameterizedType genericType = (ParameterizedType)field.getGenericType();
//						Class<?> rowClass=Class.class.cast(genericType.getActualTypeArguments()[0]);
//						if (BeanRefectUtils.isUserDefinedClass(rowClass)) {
						StaticTableData innerTableData = parseRowEntities((List<?>) fieldValue);
						if (innerTableData != null) {
							rowCells.add(new TableCell(innerTableData));
						}
//						}else {
//							rowCells.add(new TableCell(fieldValue));
//						}
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

		private final List<StaticTableColumn> getTableColumns(Class<?> beanClass, FieldFilter fieldFilter) {
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
	
	public static class StaticTableColumn{
		
		public int colspan=1;
		
        public String align = "left";

        public String valign;

		public String title;

		public StaticTableColumn(String title) {
			this(title, 1);
		}
		
		public StaticTableColumn(String title,int colspan) {
			super();
			this.title = title;
			this.colspan=colspan;
		}

		@Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("StaticTableColumn [colspan=");
            builder.append(colspan);
            builder.append(", align=");
            builder.append(align);
            builder.append(", valign=");
            builder.append(valign);
            builder.append(", title=");
            builder.append(title);
            builder.append("]");
            return builder.toString();
        }
	}
	
	public static class StaticTableRow{
		
		public TableCell[] tableCells;

		public StaticTableRow(List<TableCell> tableCells) {
			super();
			if (tableCells==null) {
				return;
			}
			int length = tableCells.size();
			this.tableCells =tableCells.toArray(new TableCell[length]) ;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("StaticTableRow [tableCells=");
			builder.append(Arrays.toString(tableCells));
			builder.append("]");
			return builder.toString();
		}
		
	}
	
	public static class TableCell{
		public Object value;

		public TableCell(Object value) {
			super();
			this.value = value;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("TableCell [value=");
			builder.append(value);
			builder.append("]");
			return builder.toString();
		}
	}

}
