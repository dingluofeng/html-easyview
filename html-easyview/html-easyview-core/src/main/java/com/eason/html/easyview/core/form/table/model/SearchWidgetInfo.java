package com.eason.html.easyview.core.form.table.model;

import java.util.HashSet;
import java.util.Set;

import com.eason.html.easyview.core.DateTimeInfo;

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

	public String searchHtml = "";

	public final Set<DateTimeInfo> datetimeFields = new HashSet<>();

}
