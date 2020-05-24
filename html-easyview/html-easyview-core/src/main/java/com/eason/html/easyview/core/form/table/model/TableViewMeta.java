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
public class TableViewMeta {

	public final List<TableColumn> columns;
	
	public int pagesize=10;

	public String searchHtml = "";

	public Set<DateTimeInfo> datetimeFields = new HashSet<>();

	public TableViewMeta(List<TableColumn> columns) {
		super();
		this.columns = columns;
	}

	/**
	 * @return the columns
	 */
	public List<TableColumn> getColumns() {
		return columns;
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
		builder.append(", searchHtml=");
		builder.append(searchHtml);
		builder.append(", datetimeFields=");
		builder.append(datetimeFields);
		builder.append("]");
		return builder.toString();
	}
}
