package com.eason.html.easyview.core.form.table.js;

import java.util.List;

import com.eason.html.easyview.core.DateTimeInfo;
import com.eason.html.easyview.core.form.CustomButton;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.ToolItemButton;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatter;
import com.eason.html.easyview.core.form.table.formatter.TableColumnFormatterFunction;
import com.eason.html.easyview.core.form.table.js.functions.AjaxQueryDataFunction;
import com.eason.html.easyview.core.form.table.js.functions.BeanTableViewInitFunction;
import com.eason.html.easyview.core.form.table.js.functions.CustomButtonOnClickEventFunction;
import com.eason.html.easyview.core.form.table.js.functions.ShowCustomDataTableFunction;
import com.eason.html.easyview.core.form.table.model.TableData;
import com.eason.html.easyview.core.form.table.model.UploadWidgetInfo;
import com.eason.html.easyview.core.form.validate.Validate;
import com.eason.html.easyview.core.utils.CollectionUtils;
import com.eason.html.easyview.core.utils.StringUtils;
import com.eason.html.easyview.core.widget.Script;
import com.eason.html.easyview.core.widget.Text;

public class TableJsScript {

	public static Script of(TableData tableData, UploadWidgetInfo uploadWidgetInfo,
			List<ToolItemButton> toolItemActions, List<CustomButton> customBtns,
			List<TableColMappingFormatter> columnFormatters) {
		String tableId = tableData.tableId;
		String baseUrl = tableData.baseUrl;
		Script script = Script.of();
		script.add(Text.of("var $table = $('#" + tableId + "');"));
		script.add(Text.of("//bootstrapTable使用中文"));
		script.add(Text.of("$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);"));
		script.add(Text.of("//防止表头与表格不对齐"));
		script.add(Text.of("$(window).resize(function () {"));
		script.add(Text.of("    $table.bootstrapTable('resetView');"));
		script.add(Text.of("});"));
		script.add(Text.of("$(function () {"));
		script.add(Text.of("    //使用严格模式"));
		script.add(Text.of("    \"use strict\";"));
		script.add(Text.of("    tableInit();"));
		script.add(Text.of("    $('#" + tableId + "').bootstrapTable('hideLoading');"));
		script.add(Text.of("})"));

		// table init
		BeanTableViewInitFunction.tableInit(tableData, script);

		// url table column Formatter
		TableColumnFormatterFunction.columnFormatterFunction(script, tableData, columnFormatters);

		script.add(Text.of("// 查询条件与分页数据"));
		script.add(Text.of("function queryParams(params) {"));
		script.add(Text.of("    //第几页"));
		script.add(Text.of("    params.curPage = this.pageNumber;"));
		for (FormInput<?> input : tableData.searchInputs) {
			script.add(Text.of("    params." + input.getField() + " = " + input.getValueScript()));
		}
		script.add(Text.of("    return params;"));
		script.add(Text.of("}"));

		script.add(Text.of("// 刷新table"));
		script.add(Text.of("function refresh() {"));
		script.add(Text.of("    $table.bootstrapTable('refresh');"));
		script.add(Text.of("}"));

		script.add(Text.of("// 查询按钮点击事件"));
		script.add(Text.of("$(\"#" + tableData.btnPrefix + "_btn_search\").on(\"click\", function () {"));
		script.add(Text.of("    refresh();"));
		script.add(Text.of("});"));

		script.add(Text.of("// 清空条件按钮点击事件"));
		script.add(Text.of("$(\"#" + tableData.btnPrefix + "_btn_clean_search\").on(\"click\", function () {"));
		for (FormInput<?> input : tableData.searchInputs) {
			script.add(Text.of("    $('#" + input.getId() + "').val(\"" + input.getDefaultValue() + "\");"));
		}
		script.add(Text.of("    refresh();"));
		script.add(Text.of("});"));

		script.add(Text.of("// 新增按钮点击事件"));
		script.add(Text.of("$(\"#" + tableData.btnPrefix + "_btn_add\").on(\"click\", function () {"));
		for (FormInput<?> formInput : tableData.formInputs) {
			if (formInput.readonly()) {
				script.add(Text.of("    $('#" + formInput.getId() + "').attr('readonly',false);"));
			}
		}
		script.add(Text.of(
				"    $('#" + tableData.btnPrefix + "_addAndUpdateLabel').text(\"新增" + tableData.tableTitle + "\");"));
		script.add(Text.of("    $('#" + tableData.btnPrefix + "_opt_type').val(\"add\");"));
		script.add(Text.of("    $('#" + tableData.btnPrefix + "_addAndUpdate').modal({"));
		script.add(Text.of("        keyboard: true"));// 点击ESC键,模态窗口即会退出。
		script.add(Text.of("    });"));
		script.add(Text.of("});"));

		script.add(Text.of("// 刷新按钮点击事件"));
		script.add(Text.of("$(\"#" + tableData.btnPrefix + "_btn_refresh\").on(\"click\", function () {"));
		script.add(Text.of("    refresh();"));
		script.add(Text.of("});"));

		// http://thevectorlab.net/flatlab/toastr.html
		script.add(Text.of("// 删除按钮点击事件"));
		script.add(Text.of("$(\"#" + tableData.btnPrefix + "_btn_delete\").on(\"click\", function () {"));
		script.add(Text.of("    var datas = $table.bootstrapTable('getSelections');"));
		script.add(Text.of("    if (datas.length < 1) {"));
		script.add(Text.of("        layer.alert('请选择一条或多条数据进行删除！', {icon: 2});"));
		script.add(Text.of("    } else {"));
		script.add(Text.of("        var id = [];"));
		script.add(Text.of("        for (var i = 0; i < datas.length; i++) {"));
		script.add(Text.of("            id.push(datas[i]." + tableData.uniqueId + ");"));
		script.add(Text.of("        }"));
		script.add(Text.of("        delData(id, \"batch\");"));
		script.add(Text.of("    }"));
		script.add(Text.of("});"));

		script.add(Text.of("// 清除弹窗原数据"));
		script.add(Text.of("$(\"#" + tableData.btnPrefix + "_addAndUpdate\").on(\"hidden.bs.modal\", function () {"));
		for (FormInput<?> input : tableData.formInputs) {
			script.add(Text.of("    $('#" + input.getId() + "').val(\"" + input.getDefaultValue() + "\");"));
		}
		script.add(Text.of("});"));

		script.add(Text.of("// 弹框保存按钮点击事件"));
		script.add(Text.of("$(\"#" + tableData.btnPrefix + "_btn_add_update_submit\").off().on('click', function () {"));
		script.add(Text.of("    var opt_type = $('#" + tableData.btnPrefix + "_opt_type').val()"));
		for (FormInput<?> formInput : tableData.formInputs) {
			script.add(Text.of("    var " + formInput.getField() + " = " + formInput.getValueScript()));
		}
		// 验证数据
		for (FormInput<?> formInput : tableData.formInputs) {
			// 判断是否需要验证
			Validate validate = formInput.getValidate();
			if (validate != null) {
				script.add(Text.of("    if (!" + formInput.getField() + ") {"));
				script.add(Text.of("        layer.msg('" + formInput.getField() + " cannot be empty!', {icon: 2, time: 1500});"));
				script.add(Text.of("        return false;"));
				script.add(Text.of("    }"));
			}
		}
		
		script.add(Text.of("        var paramdata = {"));
		script.add(Text.of("            opt_type:opt_type,"));
		for (FormInput<?> formInput : tableData.formInputs) {
			script.add(Text.of("            " + formInput.getField() + ": " + formInput.getField() + ","));
		}
		script.add(Text.of("        };"));
		script.add(Text.of("    formQuery('"+baseUrl+"/'+opt_type,paramdata,refresh);"));
		script.add(Text.of("});"));

		script.add(Text.of("//tr中编辑按钮点击事件"));
		script.add(Text.of("function editData(row) {"));
		script.add(Text.of("    //向模态框中传值"));
		for (FormInput<?> formInput : tableData.formInputs) {
			if (formInput.readonly()) {
				script.add(Text.of("    $('#" + formInput.getId() + "').attr('readonly',true);"));
			}
			script.add(Text.of("    $('#" + formInput.getId() + "').val(row." + formInput.getField() + ");"));
		}
		script.add(Text.of("    $('#" + tableData.btnPrefix + "_opt_type').val(\"update\");"));
		script.add(Text.of(
				"    $('#" + tableData.btnPrefix + "_addAndUpdateLabel').text(\"修改" + tableData.tableTitle + "\");"));
		script.add(Text.of("    //显示模态窗口"));
		script.add(Text.of("    $('#" + tableData.btnPrefix + "_addAndUpdate').modal({"));
		script.add(Text.of("        //点击ESC键,模态窗口即会退出。"));
		script.add(Text.of("        keyboard: true"));
		script.add(Text.of("    });"));
		script.add(Text.of("}"));

		script.add(Text.of("//tr中删除按钮点击事件"));
		script.add(Text.of("function delData(id, type) {"));
		script.add(Text.of("    layer.confirm('确定要删除用户编号为' + id+ '数据?', {icon: 3, title: 'Confirm'}, function () {"));
		script.add(Text.of("        var deldata= {id: id.toString(),type:type};"));
		script.add(Text.of("    	formQuery('"+baseUrl+"/delete',deldata,refresh);"));
		script.add(Text.of("    });"));
		script.add(Text.of("}"));

		script.add(Text.of("//tr中自定义扩展按钮点击事件"));
		script.add(Text.of("function customOpt(text,url,msgType,data) {"));
		script.add(Text.of("    if (msgType==5) {"));
		script.add(Text.of("      	showJsonQueryForm(url,data);"));
		script.add(Text.of("    } else {"));
		script.add(Text.of("    	layer.confirm('确定要执行' + text+ '操作?', {icon: 3, title: 'Confirm'}, function () {"));
		script.add(Text.of("	  		//showJsonQueryForm"));
		script.add(Text.of("      		showJsonQueryForm(url,data);"));
		script.add(Text.of("    	});"));
		script.add(Text.of("    }"));
		script.add(Text.of("}"));

		// CustomButton onClickEventFunction
		CustomButtonOnClickEventFunction.onClickEventFunction(script, tableData, toolItemActions, customBtns);

		// ajax query function
		AjaxQueryDataFunction.queryData(script);

		// show Custom table function
		ShowCustomDataTableFunction.showCustomDataTable(script);

		// layer
		script.add(Text.of("// layer 组件对象定义"));
		script.add(Text.of("layui.use('layer', function(){"));
		script.add(Text.of("    var layer = layui.layer;"));
		script.add(Text.of("});"));
		// laydate
		script.add(Text.of("// laydate 组件对象定义"));
		script.add(Text.of("layui.use('laydate', function(){"));
		script.add(Text.of("    var laydate = layui.laydate;"));
		if (CollectionUtils.isNotEmpty(tableData.datetimeFields)) {
			for (DateTimeInfo dateField : tableData.datetimeFields) {
				script.add(Text.of("    laydate.render({"));
				script.add(Text.of("        type: '" + dateField.type + "',"));
				if (StringUtils.isNotBlank(dateField.range)) {
					script.add(Text.of("        range: '" + dateField.range + "',"));
				}
				script.add(Text.of("        elem: '#" + dateField.id + "'"));
				script.add(Text.of("    });"));
			}
		}
		if (CollectionUtils.isNotEmpty(customBtns)) {
			for (CustomButton customButton : customBtns) {
				List<DateTimeInfo> dateTimeInfos = customButton.getDatetimeFields();
				if (CollectionUtils.isNotEmpty(dateTimeInfos)) {
					for (DateTimeInfo dateField : dateTimeInfos) {
						script.add(Text.of("    laydate.render({"));
						script.add(Text.of("        type: '" + dateField.type + "',"));
						if (StringUtils.isNotBlank(dateField.range)) {
							script.add(Text.of("        range: '" + dateField.range + "',"));
						}
						script.add(Text.of("        elem: '#" + dateField.id + "'"));
						script.add(Text.of("    });"));
					}
				}
			}
		}

		script.add(Text.of("});"));

		if (uploadWidgetInfo != null) {
			script.add(Text.of("// layer upload组件对象定义"));
			script.add(Text.of("layui.use('upload', function(){"));
			script.add(Text.of("    var upload = layui.upload;"));
			script.add(Text.of("    upload.render({"));
			script.add(Text.of("       elem: '#" + uploadWidgetInfo.getUploadId() + "'"));
			script.add(Text.of("       ,url: '" + uploadWidgetInfo.getUploadUrl() + "'"));
			script.add(Text.of("       ,accept: '" + uploadWidgetInfo.getAcceptType() + "'"));
			script.add(Text.of("       ,exts: '" + uploadWidgetInfo.getFileExts() + "'"));
			script.add(Text.of("       ,size: " + uploadWidgetInfo.getLimitSize()));
			script.add(Text.of("       ,done: function(res){"));
			script.add(Text.of("           layer.msg('Upload Success',{icon: 1, time: 2000});"));
			script.add(Text.of("           console.log(res);"));
			script.add(Text.of("           refresh();"));
			script.add(Text.of("       }"));
			script.add(Text.of("    });"));
			script.add(Text.of("});"));
		}
		return script;
	}
}
