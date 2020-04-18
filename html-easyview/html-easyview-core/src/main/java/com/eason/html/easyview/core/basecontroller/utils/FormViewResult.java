/**
 * 
 */
package com.eason.html.easyview.core.basecontroller.utils;

import java.util.List;

import com.eason.html.easyview.core.statictable.HtmlStaticTableBuilder;
import com.eason.html.easyview.core.statictable.model.StaticTableData;
import com.eason.html.easyview.core.statictable.model.StaticTableData.TableDataBuilder;

/**
 * @author dingluofeng
 *
 */
public class FormViewResult {
	
	public static String formView(String[] columnTitles, List<Object[]> rows){
		StaticTableData tableData=TableDataBuilder.newBuilder().columnTitles(columnTitles).rows(rows).build();
		HtmlStaticTableBuilder builder=new HtmlStaticTableBuilder();
		builder.headBuider();
		builder.bodyBuilder().newTableBuilder(tableData);
		return builder.build();
	}
	
	public static String formView(List<?> rowEntities){
		StaticTableData tableData=TableDataBuilder.newBuilder().parseRowEntities(rowEntities);
		HtmlStaticTableBuilder builder=new HtmlStaticTableBuilder();
		builder.headBuider();
		builder.bodyBuilder().newTableBuilder(tableData);
		return builder.build();
	}

}
