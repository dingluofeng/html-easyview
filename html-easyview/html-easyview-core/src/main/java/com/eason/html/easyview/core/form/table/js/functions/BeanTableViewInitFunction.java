/**
 * 
 */
package com.eason.html.easyview.core.form.table.js.functions;

import java.util.List;

import com.eason.html.easyview.core.form.table.model.TableColumnBuilder.TableColumn;
import com.eason.html.easyview.core.form.table.model.TableData;
import com.eason.html.easyview.core.utils.StringUtils;
import com.eason.html.easyview.core.widget.Script;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class BeanTableViewInitFunction {

	public static void tableInit(TableData tableData, Script script) {
		script.add(Text.of("//初始化Table"));
		script.add(Text.of("function tableInit() {"));
		script.add(Text.of("    //先销毁表格"));
		script.add(Text.of("    $table.bootstrapTable('destroy');"));
		script.add(Text.of("    $table.bootstrapTable({"));
		script.add(Text.of("        //请求地址"));
		script.add(Text.of("        url: '" + tableData.baseUrl + "/list',"));
		script.add(Text.of("        //请求方式"));
		script.add(Text.of("        method: 'post',"));
		script.add(Text.of("        //请求内容类型"));
		script.add(Text.of("        contentType: \"application/x-www-form-urlencoded\","));
		script.add(Text.of("        //数据类型"));
		script.add(Text.of("        dataType: \"json\","));
		// script.add(Text.of(" //table高度，"));
		// script.add(Text.of(" //如果没有设置height属性，"));
		// script.add(Text.of(" //表格自动根据记录条数觉得表格高度"));
		// script.add(Text.of(" height: '482',"));
		script.add(Text.of("        //是否显示行间隔色"));
		script.add(Text.of("        striped: true,"));
		script.add(Text.of("        //是否启用排序"));
		script.add(Text.of("        sortable: true,"));
		script.add(Text.of("        //排序方式"));
		script.add(Text.of("        sortOrder: \"asc\","));
		script.add(Text.of("        //设置表头样式"));
		script.add(Text.of("        theadClasses: \"thead-light\","));
		script.add(Text.of("        //是否使用缓存"));
		script.add(Text.of("        cache: false,"));
		script.add(Text.of("        //每行的唯一标识"));
		script.add(Text.of("        uniqueId: \"" + tableData.uniqueId + "\","));
		script.add(Text.of("        //指定工具栏"));
		script.add(Text.of("        toolbar: \"#" + tableData.tableToolbar + "\","));
		script.add(Text.of("        //显示隐藏列"));
		script.add(Text.of("        showColumns: true,"));
		script.add(Text.of("        //显示刷新按钮"));
		script.add(Text.of("        showRefresh: true,"));
		script.add(Text.of("        //转义HTML字符串，替换 &, <, >, \", `, 和 ' 字符."));
		script.add(Text.of("        escape: " + tableData.escape + ","));
		script.add(Text.of("        //切换显示样式"));
		script.add(Text.of("        showToggle: true,"));
		script.add(Text.of("        //显示Table脚部"));
		script.add(Text.of("        showFooter: false,"));
		script.add(Text.of("        //是否显示详细视图"));
		if (tableData.cardView) {
			script.add(Text.of("        cardView: true,"));
		} else {
			script.add(Text.of("        cardView: false,"));
		}
		script.add(Text.of("        //是否显示父子表"));
		script.add(Text.of("        detailView: true,"));
		script.add(Text.of("        //detail格式化"));
		script.add(Text.of("        detailFormatter: genderDetail,"));
		script.add(Text.of("        //是否显示分页"));
		script.add(Text.of("        pagination: true,"));
		script.add(Text.of("        //隐藏分页详情信息"));
		script.add(Text.of("        paginationDetailHAlign:'right',"));
		script.add(Text.of("        //是否显示分页按钮"));
		script.add(Text.of("        showPaginationSwitch: false,"));
		script.add(Text.of("        //是否启用点击选中行"));
		script.add(Text.of("        clickToSelect: false,"));
		script.add(Text.of("        //最少要显示的列数"));
		script.add(Text.of("        minimumCountColumns: 2,"));
		script.add(Text.of("        //cell没有值时显示"));
		script.add(Text.of("        undefinedText: '-',"));
		script.add(Text.of("        //分页方式：client客户端分页，server服务端分页"));
		script.add(Text.of("        sidePagination: \"server\","));
		script.add(Text.of("        //每页的记录行数"));
		script.add(Text.of("        pageSize: " + tableData.pageSize + ","));
		script.add(Text.of("        //初始化加载第1页，默认第1页"));
		script.add(Text.of("        pageNumber: 1,"));
		script.add(Text.of("        //可供选择的每页的行数"));
		// script.add(Text.of(" pageList: \"[10, 25, 50, 80, 100]\","));
//        script.add(Text.of("        paginationFirstText: \"首页\","));
//        script.add(Text.of("        paginationPreText: \"上一页\","));
//        script.add(Text.of("        paginationNextText: \"下一页\","));
//        script.add(Text.of("        paginationLastText: \"末页\","));
		script.add(Text.of("        buttonsClass: 'default',"));
		script.add(Text.of("        iconsPrefix: 'glyphicon',"));
		script.add(Text.of("        queryParams: queryParams,"));
		script.add(Text.of("        icons: {"));
		script.add(Text.of("            paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',"));
		script.add(Text.of("            paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',"));
		script.add(Text.of("            refresh: 'glyphicon-refresh icon-refresh',"));
		script.add(Text.of("            toggleOff: 'glyphicon-list-alt icon-list-alt',"));
		script.add(Text.of("            toggleOn: 'glyphicon-list-alt icon-list-alt',"));
		script.add(Text.of("            columns: 'glyphicon-th icon-th',"));
		script.add(Text.of("            detailOpen: 'glyphicon-plus icon-plus',"));
		script.add(Text.of("            detailClose: 'glyphicon-minus icon-minus'"));
		script.add(Text.of("        }, columns: ["));
		//buildColumns
		buildColumns(script, tableData.columns);
		script.add(Text.of("        ],"));
		//responseHandler
		script.add(Text.of("        responseHandler: function (res) {"));
		script.add(Text.of("            if (res.status == 0||res.status == 200) {"));
		script.add(Text.of("                var obj = {"));
		script.add(Text.of("                    \"total\": res.total,"));
		script.add(Text.of("                    \"rows\": res.data,"));
		script.add(Text.of("                };"));
		script.add(Text.of("            } else {"));
		script.add(Text.of("                var obj = {"));
		script.add(Text.of("                    \"total\": 0,"));
		script.add(Text.of("                    \"rows\": [],"));
		script.add(Text.of("                }"));
		script.add(Text.of("            }"));
		script.add(Text.of("            return obj;"));
		script.add(Text.of("        }, onLoadSuccess: function () {"));
		script.add(Text.of("            //加载成功时执行"));
		script.add(Text.of("            console.log(\"LoadSuccess!\");"));
		script.add(Text.of("        }, onLoadError: function () {"));
		script.add(Text.of("            //加载失败时执行"));
		script.add(Text.of("            layui.layer.msg(\"加载失败!\", {icon: 2, time: 2000});"));
		script.add(Text.of("        }, formatLoadingMessage: function () {"));
		script.add(Text.of("            //正在加载"));
		script.add(Text.of("            return \"please wait，Loading...\";"));
		script.add(Text.of("        }, formatNoMatches: function () {"));
		script.add(Text.of("            //没有匹配的结果"));
		script.add(Text.of("            return 'No Data';"));
		script.add(Text.of("        }"));
		script.add(Text.of("    })"));
		script.add(Text.of("}"));
	}
	
	private static void buildColumns(Script script, List<TableColumn> columns){
		int index = 0;
		for (TableColumn tableColumn : columns) {
			script.add(Text.of("        {"));
			script.add(Text.of("            title: '" + tableColumn.title + "',"));
			// checkbox
			if (tableColumn.checkbox) {
				script.add(Text.of("            checkbox: " + tableColumn.checkbox + ","));
			}
			script.add(Text.of("            field: '" + tableColumn.field + "',"));
			script.add(Text.of("            sortable: '" + tableColumn.sortable + "',"));
			script.add(Text.of("            align: '" + tableColumn.align + "',"));
			// events
			if (StringUtils.isNotBlank(tableColumn.events)) {
				script.add(Text.of("            events: " + tableColumn.events + ","));
			}
			// formatter
			if (StringUtils.isNotBlank(tableColumn.formatter)) {
				script.add(Text.of("            formatter: " + tableColumn.formatter + ","));
			}
			script.add(Text.of("            valign: '" + tableColumn.valign + "'"));
			if (++index < columns.size()) {
				script.add(Text.of("        },"));
			} else {
				script.add(Text.of("        }"));
			}
		}
	}

}
