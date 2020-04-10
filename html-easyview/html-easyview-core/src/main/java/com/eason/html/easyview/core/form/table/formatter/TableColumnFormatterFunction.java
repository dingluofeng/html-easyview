/**
 * 
 */
package com.eason.html.easyview.core.form.table.formatter;

import java.util.List;

import com.eason.html.easyview.core.form.table.TableItemLink;
import com.eason.html.easyview.core.form.table.model.TableData;
import com.eason.html.easyview.core.utils.CollectionUtils;
import com.eason.html.easyview.core.utils.StringUtils;
import com.eason.html.easyview.core.widget.Script;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class TableColumnFormatterFunction {

	public static void columnFormatterFunction(Script script, TableData tableData,
			List<TableColMappingFormatter> columnFormatters) {
		script.add(Text.of("//生成详细视图"));
		script.add(Text.of("function genderDetail(index, row) {"));
		script.add(Text.of("    var html = [];"));
		script.add(Text.of("    $.each(row, function (key, value) {"));
		script.add(Text.of("        if($.isPlainObject(value)){"));
		script.add(Text.of("        	html.push('<b>' + key + ':</b><br>');"));
		script.add(Text.of("        	html.push('<table class=\"table\"><tr><th>Key</th><th>Value</th></tr>');"));
		script.add(Text.of("        	$.each(value, function (k, v) {"));
		script.add(Text.of("        		html.push('<tr><td><b>' + k + ':</b></td><td>' + v + '</td></tr>');"));
		script.add(Text.of("        	});"));
		script.add(Text.of("        	html.push('</table>');"));
		script.add(Text.of("        }else{"));
		script.add(Text.of("        	html.push('<b>' + key + ':</b> ' + value + '<br>');"));
		script.add(Text.of("        }"));
		script.add(Text.of("    });"));
		script.add(Text.of("    return html.join('');"));
		script.add(Text.of("}"));

		script.add(Text.of("//生成序号"));
		script.add(Text.of("function genderIndex(value, row, index) {"));
		script.add(Text.of(
				"    var sidePagination=$('#" + tableData.tableId + "').bootstrapTable('getOptions').sidePagination;"));
		script.add(Text.of("    if(sidePagination=='client'){"));
		script.add(Text.of("    	return index + 1;"));
		script.add(Text.of("    }else{"));
		script.add(
				Text.of("    	var pageSize=$('#" + tableData.tableId + "').bootstrapTable('getOptions').pageSize;"));
		script.add(Text
				.of("    	var pageNumber=$('#" + tableData.tableId + "').bootstrapTable('getOptions').pageNumber;"));
		script.add(Text.of("    	if(pageSize==undefined){"));
		script.add(Text.of("    		return index + 1;"));
		script.add(Text.of("    	}"));
		script.add(Text.of("    	return pageSize * (pageNumber - 1) + index + 1;"));
		script.add(Text.of("	}"));
		script.add(Text.of("}"));

		script.add(Text.of("//自定义列内容"));
		script.add(Text.of("function genderOpt() {"));
		script.add(Text.of("    return ["));
		script.add(Text.of("        ''"));
		// 自定义操作列link
		List<TableItemLink> itemLinks = tableData.customItemLinks;
		for (TableItemLink itemLink : itemLinks) {
			script.add(Text.of("        ,'" + itemLink.buildLink() + "'"));
		}
		script.add(Text.of("    ].join('');"));
		script.add(Text.of("}"));

		script.add(Text.of("//自定义列内容事件"));
		script.add(Text.of("window.operateEvents = {"));
		// 自定义操作列绑定事件
		for (TableItemLink itemLink : itemLinks) {
			if (StringUtils.isNotBlank(itemLink.url())) {
				script.add(Text.of("    'click #" + itemLink.id() + "': function (e, value, row, index) {"));
				script.add(Text.of("        customOpt('" + itemLink.title() + "','" + itemLink.url() + "',row);"));
				script.add(Text.of("    },"));
			}
		}
		// 默认tableItem事件
		script.add(Text.of("    'click #edit': function (e, value, row, index) {"));
		script.add(Text.of("        editData(row);"));
		script.add(Text.of("    },"));
		script.add(Text.of("    'click #remove': function (e, value, row, index) {"));
		script.add(Text.of("        delData(row." + tableData.uniqueId + ",\"one\");"));
		script.add(Text.of("    }"));
		script.add(Text.of("};"));

		// column formatter
		if (CollectionUtils.isNotEmpty(columnFormatters)) {
			for (TableColMappingFormatter tableColMappingFormatter : columnFormatters) {
				script.add(Text.of("//" + tableColMappingFormatter.functionName() + ""));
				script.add(Text.of("function " + tableColMappingFormatter.functionName() + "(value, row, index) {"));
				script.add(Text.of("     if (value == null || value == undefined) {"));
				script.add(Text.of("        return \"-\";"));
				script.add(Text.of("     }"));
				script.add(Text
                        .of("     var mapping=" + tableColMappingFormatter.jsonMapping() + ";"));
				script.add(Text.of("     var ret=mapping[value];"));
				script.add(Text.of("     if (ret== undefined) {"));
				script.add(Text.of("        ret= '<span style=\"color:#FF0000\">Unknown</span>';"));
				script.add(Text.of("     }"));
				script.add(Text.of("     return ret+'('+value+')';"));
				script.add(Text.of("}"));
			}
		}

	}
}
