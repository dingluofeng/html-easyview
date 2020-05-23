package com.eason.html.easyview.core.form.table.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.eason.html.easyview.core.DateTimeInfo;
import com.eason.html.easyview.core.form.FormInput;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年2月2日 下午6:42:41
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年2月2日
 * @modify by reason:{方法名}:{原因}
 */
public class SearchWidgetInfo {

	public String searchBtn;
	
	public String searchHtml = "";
	
	public final List<FormInput<?>> searchInputs = new ArrayList<>();

	public final Set<DateTimeInfo> datetimeFields = new HashSet<>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchWidgetInfo [searchBtn=");
		builder.append(searchBtn);
		builder.append(", searchHtml=");
		builder.append(searchHtml);
		builder.append(", searchInputs=");
		builder.append(searchInputs);
		builder.append(", datetimeFields=");
		builder.append(datetimeFields);
		builder.append("]");
		return builder.toString();
	}
}
