/**
 * 
 */
package com.eason.html.easyview.core.form.table.js.functions;

import java.util.List;

import com.eason.html.easyview.core.form.CustomButton;
import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.form.ToolItemButton;
import com.eason.html.easyview.core.form.table.model.TableData;
import com.eason.html.easyview.core.utils.CollectionUtils;
import com.eason.html.easyview.core.widget.Script;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class CustomButtonOnClickEventFunction {

	public static void onClickEventFunction(Script script, TableData tableData, List<ToolItemButton> toolItemActions,
			List<CustomButton> customBtns) {
		script.add(Text.of("//扩展查询按钮点击事件"));
		if (CollectionUtils.isNotEmpty(customBtns)) {
			for (CustomButton customBtn : customBtns) {
				script.add(Text.of("$(\"#" + customBtn.getId() + "\").on(\"click\", function () {"));
				// getSearchFromData
				getSearchFromData(script, tableData, customBtn);
				script.add(Text.of("     var tableData ="+customBtn.getQueryAction().getJsonTableMeta()+";"));
				script.add(Text.of("     $('#custom_title').text(\"" + customBtn.getTitle() + "\");"));
				script.add(Text.of("     queryData('" + customBtn.getUrl() + "', jsonData,true,tableData);"));
				script.add(Text.of("});"));

				if (CollectionUtils.isNotEmpty(customBtn.getSearchInputs())) {
					script.add(Text.of("//自定义扩展查询按钮click事件"));
					script.add(Text.of("function " + customBtn.getCustomBtnId() + "onclick() {"));
					// getSearchFromData
					getSearchFromData(script, tableData, customBtn);
					script.add(Text.of("     var tableData ="+customBtn.getQueryAction().getJsonTableMeta()+";"));
					script.add(Text.of("     queryData('" + customBtn.getUrl() + "', jsonData,false,tableData);"));
					script.add(Text.of("}"));
				}
			}
		}

		if (CollectionUtils.isNotEmpty(toolItemActions)) {
			for (ToolItemButton customBtn : toolItemActions) {
				script.add(Text.of("$(\"#" + customBtn.getId() + "\").on(\"click\", function () {"));
				getSearchFromData(script, tableData, null);
				script.add(Text.of("     showMessage('" + customBtn.getUrl() + "', jsonData);"));
				script.add(Text.of("});"));
			}
		}
	}

	private final static void getSearchFromData(Script script, TableData tableData, CustomButton customBtn) {
		for (FormInput<?> formInput : tableData.searchInputs) {
			script.add(Text.of("     var " + formInput.getField() + " = " + formInput.getValueScript()));
		}
		// cutom search
		if (customBtn != null && CollectionUtils.isNotEmpty(customBtn.getSearchInputs())) {
			for (FormInput<?> input : customBtn.getSearchInputs()) {
				script.add(Text.of("     var " + input.getField() + " = " + input.getValueScript()));
			}
		}
		script.add(Text.of("     var jsonData={"));
		for (FormInput<?> formInput : tableData.searchInputs) {
			script.add(Text.of("		" + formInput.getField() + ": " + formInput.getField() + ","));
		}
		// cutom search
		if (customBtn != null && CollectionUtils.isNotEmpty(customBtn.getSearchInputs())) {
			for (FormInput<?> input : customBtn.getSearchInputs()) {
				script.add(Text.of("		" + input.getField() + ": " + input.getField() + ","));
			}
		}
		script.add(Text.of("	 }"));
	}

}
