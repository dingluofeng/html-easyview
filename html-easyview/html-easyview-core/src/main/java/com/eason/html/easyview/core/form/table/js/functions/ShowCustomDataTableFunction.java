/**
 * 
 */
package com.eason.html.easyview.core.form.table.js.functions;

import com.eason.html.easyview.core.widget.Script;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class ShowCustomDataTableFunction {

	public static void showCustomDataTable(Script script) {
		script.add(Text.of(
				"var INDEXCOLUMN = {title: '#',field: 'index',align: 'center',valign: 'middle',formatter: customGenderIndex};"));
		script.add(Text.of("//tableView"));
		script.add(Text.of("function showTable(tableData,needCreadsearchForm) {"));
		script.add(Text.of("    tableData.columns.unshift(INDEXCOLUMN);"));
		script.add(Text.of("    //动态增加查询条件"));
		script.add(Text.of("    if(needCreadsearchForm){"));
		script.add(Text.of("    	$('#custom_form_div').html(tableData.searchHtml);"));
		script.add(Text.of("    //初始化laydate组件"));
		script.add(Text.of("        for (var i = 0; i < tableData.datetimeFields.length; i++) {"));
		script.add(Text.of("            var date_info=tableData.datetimeFields[i];"));
		script.add(Text.of("            layui.laydate.render({"));
		script.add(Text.of("                type: date_info.type,"));
		script.add(Text.of("                range: date_info.range,"));
		script.add(Text.of("                elem: '#'+date_info.id"));
		script.add(Text.of("            });"));
		script.add(Text.of("        }"));
		script.add(Text.of("    }"));
		script.add(Text.of("    //先销毁表格"));
		script.add(Text.of("    $('#custom_table').bootstrapTable('destroy');"));
		script.add(Text.of("    $('#custom_table').bootstrapTable({"));
		script.add(Text.of("        //请求地址"));
		script.add(Text.of("        data: tableData.data,"));
		script.add(Text.of("        //是否显示行间隔色"));
		script.add(Text.of("        striped: true,"));
		script.add(Text.of("        //是否显示父子表"));
		script.add(Text.of("        detailView: true,"));
		script.add(Text.of("        //显示隐藏列"));
		script.add(Text.of("        showColumns: true,"));
		script.add(Text.of("        //显示刷新按钮"));
		script.add(Text.of("        showRefresh: true,"));
		script.add(Text.of("        //转义HTML字符串，替换 &, <, >, \", `, 和 ' 字符."));
		script.add(Text.of("        escape: true,"));
		script.add(Text.of("        //切换显示样式"));
		script.add(Text.of("        showToggle: true,"));
		script.add(Text.of("        //显示Table脚部"));
		script.add(Text.of("        showFooter: false,"));
		script.add(Text.of("        //detail格式化"));
		script.add(Text.of("        detailFormatter: genderDetail,"));
		script.add(Text.of("        //是否显示分页"));
		script.add(Text.of("        pagination: true,"));
		script.add(Text.of("        paginationDetailHAlign:'right',"));
		script.add(Text.of("        //cell没有值时显示"));
		script.add(Text.of("        undefinedText: '-',"));
		script.add(Text.of("        //分页方式：client客户端分页，server服务端分页"));
		script.add(Text.of("        sidePagination: \"client\","));
		script.add(Text.of("        //每页的记录行数"));
		script.add(Text.of("        pageSize: 10,"));
		script.add(Text.of("        //初始化加载第1页，默认第1页"));
		script.add(Text.of("        pageNumber: 1,"));
		script.add(Text.of("        //可供选择的每页的行数"));
		script.add(Text.of("        pageList: \"[10, 25, 50, 100, all]\","));
		script.add(Text.of("        buttonsClass: 'default',"));
		script.add(Text.of("        columns: tableData.columns"));
		script.add(Text.of("    })"));
		script.add(Text.of("}"));

		script.add(Text.of("//生成序号"));
		script.add(Text.of("function customGenderIndex(value, row, index) {"));
		script.add(Text.of("    var sidePagination=$('#custom_table').bootstrapTable('getOptions').sidePagination;"));
		script.add(Text.of("    if(sidePagination=='client'){"));
		script.add(Text.of("    	return index + 1;"));
		script.add(Text.of("    }else{"));
		script.add(Text.of("    	var pageSize=$('#custom_table').bootstrapTable('getOptions').pageSize;"));
		script.add(Text.of("    	var pageNumber=$('#custom_table').bootstrapTable('getOptions').pageNumber;"));
		script.add(Text.of("    	if(pageSize==undefined){"));
		script.add(Text.of("    		return index + 1;"));
		script.add(Text.of("    	}"));
		script.add(Text.of("    	return pageSize * (pageNumber - 1) + index + 1;"));
		script.add(Text.of("	}"));
		script.add(Text.of("}"));
	}

}
