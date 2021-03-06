/**
 * 
 */
package com.eason.html.easyview.core.form.table.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.eason.html.easyview.core.DateTimeInfo;
import com.eason.html.easyview.core.form.table.model.TableColumnBuilder.TableColumn;
import com.eason.html.easyview.core.utils.BeanRefectUtils;

/**
 * @author dingluofeng
 *
 */
public class TableInfo {

	public final List<TableColumn> columns;

	public List<?> data;

	public String searchHtml = "";

	public final Set<DateTimeInfo> datetimeFields = new HashSet<>();

	public TableInfo(List<?> data, List<TableColumn> columns) {
		super();
		this.data = data;
		this.columns = columns;
	}

//    public TableInfo(List<?> data) {
//		super();
//		this.data=data;
//        if (CollectionUtils.isNotEmpty(this.data) && this.data.get(0) != null) {
//			Class<? extends Object> beanClass = this.data.get(0).getClass();
//            if (Map.class.isAssignableFrom(beanClass)) {
//                @SuppressWarnings("unchecked")
//                Map<Object, Object> dataMap = (Map<Object, Object>) data.get(0);
//                columns = BeanRefectUtils.parseColumnsfromMap(dataMap);
//            } else {
//                columns = BeanRefectUtils.parseColumns(beanClass, null);
//            }
//        } else {
//            columns = new ArrayList<>();
//		}
//	}

	/**
	 * @return the columns
	 */
	public List<TableColumn> getColumns() {
		return columns;
	}

	/**
	 * @return the data
	 */
	public List<?> getData() {
		return data;
	}

	/**
	 * @return the searchHtml
	 */
	public String getSearchHtml() {
		return searchHtml;
	}

	public void setConditionClass(Class<?> conditionClass) {
		SearchWidgetInfo searchWidgetInfo = BeanRefectUtils.parseSearchForm(conditionClass);
		if (searchWidgetInfo != null) {
			searchHtml = searchWidgetInfo.searchHtml;
			datetimeFields.addAll(searchWidgetInfo.datetimeFields);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TableInfo [columns=");
		builder.append(columns);
		builder.append(", data=");
		builder.append(data);
		builder.append(", searchHtml=");
		builder.append(searchHtml);
		builder.append(", datetimeFields=");
		builder.append(datetimeFields);
		builder.append("]");
		return builder.toString();
	}
}
