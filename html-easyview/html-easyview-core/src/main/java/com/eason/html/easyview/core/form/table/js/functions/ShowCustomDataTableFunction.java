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

	private static final String VAR_INDEXCOLUMN = "var INDEXCOLUMN = {title: '#',field: 'index',align: 'center',valign: 'middle',formatter: customGenderIndex};";

	public static void showCustomDataTable(Script script) {
		script.add(Text.of(VAR_INDEXCOLUMN));
		script.add(Text.of("//tableView"));
		script.add(Text.of("function showTable(tableData,needCreadsearchForm) {"));
		script.add(Text.of("    tableData.columns.unshift(INDEXCOLUMN);"));
		script.add(Text.of("    //动态增加查询条件"));
		script.add(Text.of("    if(needCreadsearchForm){"));
		script.add(Text.of("    	$('#custom_form_div').html(tableData.searchHtml);"));
		script.add(Text.of("    	//初始化laydate组件"));
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
		script.add(Text.of("        pageList: [10, 25, 50, 100, 'all'],"));
		script.add(Text.of("        buttonsClass: 'default',"));
		script.add(Text.of("        columns: tableData.columns,"));
		script.add(Text.of("        formatNoMatches: function () {"));
		script.add(Text.of("            //没有匹配的结果"));
		script.add(Text.of("            return 'No Data';"));
		script.add(Text.of("        }"));
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
		
		script.add(Text.of("//初始化tableQueryView"));
		script.add(Text.of("function tableQueryView(url,customParams,tableData,needCreadsearchForm) {"));
		script.add(Text.of("    tableData.columns.unshift(INDEXCOLUMN);"));
		script.add(Text.of("	//动态增加查询条件"));
		script.add(Text.of("    if(needCreadsearchForm){"));
		script.add(Text.of("    	$('#custom_form_div').html(tableData.searchHtml);"));
		script.add(Text.of("    	//初始化laydate组件"));
		script.add(Text.of("        for (var i = 0; i < tableData.datetimeFields.length; i++) {"));
		script.add(Text.of("            var date_info=tableData.datetimeFields[i];"));
		script.add(Text.of("            layui.laydate.render({"));
		script.add(Text.of("                type: date_info.type,"));
		script.add(Text.of("                range: date_info.range,"));
		script.add(Text.of("                elem: '#'+date_info.id"));
		script.add(Text.of("            });"));
		script.add(Text.of("        }"));
		script.add(Text.of("    }"));
		script.add(Text.of("	//先销毁表格"));
		script.add(Text.of("	$('#custom_table').bootstrapTable('destroy');"));
		script.add(Text.of("	$('#custom_table').bootstrapTable({"));
		script.add(Text.of("		//请求地址"));
		script.add(Text.of("		url: url,"));
		script.add(Text.of("		//请求方式"));
		script.add(Text.of("		method: 'post',"));
		script.add(Text.of("		//请求内容类型"));
		script.add(Text.of("		contentType: 'application/x-www-form-urlencoded',"));
		script.add(Text.of("		//数据类型"));
		script.add(Text.of("		dataType: 'json',"));
		script.add(Text.of("		//是否显示行间隔色"));
		script.add(Text.of("		striped: true,"));
		script.add(Text.of("		//是否启用排序"));
		script.add(Text.of("		sortable: true,"));
		script.add(Text.of("		//排序方式"));
		script.add(Text.of("		sortOrder: 'asc',"));
		script.add(Text.of("		//设置表头样式"));
		script.add(Text.of("		theadClasses: 'thead-light',"));
		script.add(Text.of("		//是否使用缓存"));
		script.add(Text.of("		cache: false,"));
		script.add(Text.of("		//显示隐藏列"));
		script.add(Text.of("		showColumns: true,"));
		script.add(Text.of("		//显示刷新按钮"));
		script.add(Text.of("		showRefresh: true,"));
		script.add(Text.of("		//转义HTML字符串，替换 &, <, >, \", `, 和 ' 字符."));
		script.add(Text.of("		escape: true,"));
		script.add(Text.of("		//切换显示样式"));
		script.add(Text.of("		showToggle: true,"));
		script.add(Text.of("		//显示Table脚部"));
		script.add(Text.of("		showFooter: false,"));
		script.add(Text.of("		//是否显示详细视图"));
		script.add(Text.of("		cardView: false,"));
		script.add(Text.of("		//是否显示父子表"));
		script.add(Text.of("		detailView: true,"));
		script.add(Text.of("		//detail格式化"));
		script.add(Text.of("		detailFormatter: genderDetail,"));
		script.add(Text.of("		//是否显示分页"));
		script.add(Text.of("		pagination: true,"));
		script.add(Text.of("		smartDisplay:false,"));
		script.add(Text.of("		pageList: [10,50,100,500,1000],"));
		script.add(Text.of("		paginationHAlign: 'right',"));
		script.add(Text.of("		paginationVAlign: 'bottom',"));
		script.add(Text.of("		paginationDetailHAlign: 'left',"));
		script.add(Text.of("		//隐藏分页详情信息,设置 'right'"));
//		script.add(Text.of("		paginationDetailHAlign:'right',"));
		script.add(Text.of("		//是否显示分页按钮"));
		script.add(Text.of("		showPaginationSwitch: false,"));
		script.add(Text.of("		//是否启用点击选中行"));
		script.add(Text.of("		clickToSelect: false,"));
		script.add(Text.of("		//最少要显示的列数"));
		script.add(Text.of("		minimumCountColumns: 2,"));
		script.add(Text.of("		//cell没有值时显示"));
		script.add(Text.of("		undefinedText: '-',"));
		script.add(Text.of("		//分页方式：client客户端分页，server服务端分页"));
		script.add(Text.of("		sidePagination: 'server',"));
		script.add(Text.of("		//每页的记录行数"));
		script.add(Text.of("		pageSize: tableData.pagesize,"));
		script.add(Text.of("		//初始化加载第1页，默认第1页"));
		script.add(Text.of("		pageNumber: 1,"));
		script.add(Text.of("		//可供选择的每页的行数"));
		script.add(Text.of("		buttonsClass: 'default',"));
		script.add(Text.of("		iconsPrefix: 'glyphicon',"));
		script.add(Text.of("		queryParams : function(params) {//上传服务器的参数"));
		script.add(Text.of("		    customParams.limit=params.limit;"));
		script.add(Text.of("		    customParams.offset=params.offset;"));
		script.add(Text.of("		    customParams.curPage = this.pageNumber;"));
		script.add(Text.of("		    return customParams;"));
		script.add(Text.of("		 },"));
		script.add(Text.of("		icons: {"));
		script.add(Text.of("			paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',"));
		script.add(Text.of("			paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',"));
		script.add(Text.of("			refresh: 'glyphicon-refresh icon-refresh',"));
		script.add(Text.of("			toggleOff: 'glyphicon-list-alt icon-list-alt',"));
		script.add(Text.of("			toggleOn: 'glyphicon-list-alt icon-list-alt',"));
		script.add(Text.of("			columns: 'glyphicon-th icon-th',"));
		script.add(Text.of("			detailOpen: 'glyphicon-plus icon-plus',"));
		script.add(Text.of("			detailClose: 'glyphicon-minus icon-minus'"));
		script.add(Text.of("		}, columns: tableData.columns,"));
		script.add(Text.of("		responseHandler: function (res) {"));
		script.add(Text.of("			if (res.status == 0||res.status == 200) {"));
		script.add(Text.of("				var obj = {"));
		script.add(Text.of("					\"total\": res.total,"));
		script.add(Text.of("					\"rows\": res.data.data,"));
		script.add(Text.of("				};"));
		script.add(Text.of("			} else {"));
		script.add(Text.of("				var obj = {"));
		script.add(Text.of("					\"total\": 0,"));
		script.add(Text.of("					\"rows\": [],"));
		script.add(Text.of("				}"));
		script.add(Text.of("			}"));
		script.add(Text.of("			return obj;"));
		script.add(Text.of("		}, onLoadSuccess: function () {"));
		script.add(Text.of("			//加载成功时执行"));
		script.add(Text.of("			console.log('LoadSuccess!');"));
		script.add(Text.of("		}, onLoadError: function () {"));
		script.add(Text.of("			//加载失败时执行"));
		script.add(Text.of("			layui.layer.msg('Load error!', {icon: 2, time: 2000});"));
		script.add(Text.of("		}, formatLoadingMessage: function () {"));
		script.add(Text.of("			//正在加载"));
		script.add(Text.of("			return 'please wait，Loading...';"));
		script.add(Text.of("		}, formatNoMatches: function () {"));
		script.add(Text.of("			//没有匹配的结果"));
		script.add(Text.of("			return 'No Data';"));
		script.add(Text.of("		}"));
		script.add(Text.of("	})"));
		script.add(Text.of("}"));

	}

}
