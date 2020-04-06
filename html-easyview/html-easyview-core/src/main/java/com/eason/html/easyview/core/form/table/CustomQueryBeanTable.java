package com.eason.html.easyview.core.form.table;

import java.util.List;

import com.eason.html.easyview.core.form.CustomButton;
import com.eason.html.easyview.core.form.search.CustomSearchForm;
import com.eason.html.easyview.core.form.table.model.TableData;
import com.eason.html.easyview.core.utils.CollectionUtils;
import com.eason.html.easyview.core.widget.Div;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Table;

/**
 * 增删改查的表格组件
 * 
 * @author dingluofeng
 *
 */
public class CustomQueryBeanTable extends Node<CustomQueryBeanTable> {

	public CustomQueryBeanTable() {
		super("div");
		addClass("container-fluid");
	}

	public static CustomQueryBeanTable build(TableData tableData, List<CustomButton> customButtons) {
		if (CollectionUtils.isNotEmpty(customButtons)) {
			CustomQueryBeanTable beanTableView = new CustomQueryBeanTable();
			// 创建容器 Container
			TableTitle tableTitle = TableTitle.of("更多查询");
			beanTableView.add(tableTitle);
			// customToolbar
			Div tbDiv = Div.of().addStyle("border-radius:3px;margin: 0px;border:1px solid #ccc");
			ToolBar customTableToolbar = ToolBar.customTableToolbar("custom_toolbar", customButtons)
					.addStyle("margin: 4px;");
			tbDiv.add(customTableToolbar);
			beanTableView.add(tbDiv);
			// table title
			TableTitle title = TableTitle.of("custom_title", "信息");
			beanTableView.add(title);

			// 查询表单
			CustomSearchForm customSearchForm = CustomSearchForm.form(12, "custom_form_div")
					.addStyle("margin-bottom: 10px;");
			beanTableView.add(customSearchForm);

			Table customTable = Table.of().setId("custom_table").addClass("table table-hover");
			beanTableView.add(customTable);
			return beanTableView;
		}
		return null;
	}

}
