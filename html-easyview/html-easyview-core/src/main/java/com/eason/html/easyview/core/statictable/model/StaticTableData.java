package com.eason.html.easyview.core.statictable.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

    private String[] columnTitles;

    private List<Object[]> rows = new ArrayList<>();

    private StaticTableData() {
        super();
    }

    public List<Object[]> getRows() {
        return rows;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public String[] getColumnTitles() {
        return columnTitles;
    }

    public boolean hasData() {
        return rows != null && !rows.isEmpty();
    }

    public final static class TableDataBuilder {

        StaticTableData tableData;

        List<?> rowEntities;

        private TableDataBuilder() {
            super();
        }

        public static TableDataBuilder newBuilder() {
            TableDataBuilder builder = new TableDataBuilder();
            builder.tableData = new StaticTableData();
            return builder;
        }

        public TableDataBuilder rowEntities(List<?> rowEntities) {
            this.rowEntities = rowEntities;
            return this;
        }

        public TableDataBuilder title(String tableTitle) {
            this.tableData.tableTitle = tableTitle;
            return this;
        }

        public TableDataBuilder columnTitles(String[] columnTitles) {
            this.tableData.columnTitles = columnTitles;
            return this;
        }

        public TableDataBuilder rows(List<Object[]> rows) {
            this.tableData.rows = rows;
            return this;
        }

        private final TableDataBuilder parseRows(List<?> rowEntities) {
            if (CollectionUtils.isEmpty(rowEntities)) {
                return this;
            }
            EasyView annotation = rowEntities.get(0).getClass().getAnnotation(EasyView.class);
            FieldFilter fieldFilter = null;
            final boolean hasViewAnnotation = (annotation != null);
            if (hasViewAnnotation) {
                tableData.tableTitle = annotation.name();
                fieldFilter = new AnnotationFieldFilter<>(EasyView.class);
            } else {
                fieldFilter = new DefaultFieldFilter();
            }
            final List<String> names = new ArrayList<>();
            int rowIndex = 0;
            for (Object rowData : rowEntities) {
                final List<Object> row = new ArrayList<>();
                final int countIndex = rowIndex;
                BeanRefectUtils.listObjectFields(rowData, new FieldCallback() {
                    @Override
                    public void doWith(Field field, Object fieldValue) {
                        if (countIndex == 0) {
                            if (hasViewAnnotation) {
                                EasyView column = field.getAnnotation(EasyView.class);
                                names.add(column.name());
                            } else {
                                names.add(field.getName());
                            }
                        }
                        row.add(fieldValue);

                    }
                }, fieldFilter);
                rowIndex++;
                tableData.rows.add(row.toArray());
            }
            tableData.columnTitles = names.toArray(new String[names.size()]);
            return this;
        }

        public StaticTableData build() {
            parseRows(rowEntities);
            return tableData;
        }
    }

}
