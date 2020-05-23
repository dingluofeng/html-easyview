/**
 * 
 */
package com.eason.html.easyview.core;

import java.util.List;

import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.table.model.SearchWidgetInfo;
import com.eason.html.easyview.core.form.table.model.TableColumnBuilder.TableColumn;
import com.eason.html.easyview.core.form.table.model.TableViewMeta;
import com.eason.html.easyview.core.utils.BeanRefectUtils;
import com.eason.html.easyview.core.utils.JacksonUtils;
import com.google.common.collect.Lists;

/**
 * @author dingluofeng
 *
 */
public class QueryAction implements IAction {

	private String id;

	private String name;

	private String url;

	private String customBtnId;

	private SearchWidgetInfo searchWidgetInfo;
	
	private List<TableColumn> viewColumns;

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
		parseSearchForm(searchCondition);
	}

	private final void parseSearchForm(Class<?> beanClass) {
		searchWidgetInfo = BeanRefectUtils.parseSearchForm(beanClass);
		if (searchWidgetInfo != null) {
			customBtnId = searchWidgetInfo.searchBtn;
		}
	}
	
	public String getJsonTableMeta(){
		TableViewMeta tableViewMeta=new TableViewMeta(viewColumns);
		if (searchWidgetInfo!=null) {
			tableViewMeta.searchHtml=searchWidgetInfo.searchHtml;
			tableViewMeta.datetimeFields=searchWidgetInfo.datetimeFields;
		}
		return JacksonUtils.toJsonString(tableViewMeta);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryAction [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", url=");
		builder.append(url);
		builder.append(", customBtnId=");
		builder.append(customBtnId);
		builder.append(", searchWidgetInfo=");
		builder.append(searchWidgetInfo);
		builder.append(", viewColumns=");
		builder.append(viewColumns);
		builder.append("]");
		return builder.toString();
	}

	public String getCustomBtnId() {
		return customBtnId;
	}

	public List<FormInput<?>> getSearchInputs() {
		if (searchWidgetInfo==null) {
			return  Lists.newArrayList();
		}
		return Lists.newArrayList(searchWidgetInfo.searchInputs);
	}

	public List<DateTimeInfo> getDatetimeFields() {
		if (searchWidgetInfo==null) {
			return  Lists.newArrayList();
		}
		return Lists.newArrayList(searchWidgetInfo.datetimeFields);
	}

	public SearchWidgetInfo getSearchWidgetInfo() {
		return searchWidgetInfo;
	}

	public void setSearchWidgetInfo(SearchWidgetInfo searchWidgetInfo) {
		this.searchWidgetInfo = searchWidgetInfo;
	}

	public List<TableColumn> getViewColumns() {
		return viewColumns;
	}

	public void setViewColumns(List<TableColumn> viewColumns) {
		this.viewColumns = viewColumns;
	}

}
