package com.eason.html.easyview.core.form.table;

import com.eason.html.easyview.core.form.FormComponent;
import com.eason.html.easyview.core.form.table.model.TableData;
import com.eason.html.easyview.core.widget.Button;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Table;

/**
 * 
 * @author dingluofeng
 *
 */
public class BeanTableView extends Node<BeanTableView> {

	private BeanTableView() {
		super("div");
		addClass("container-fluid");
	}

	public static BeanTableView build(TableData tableData,int toolbarStyle,Button...buttons) {
		BeanTableView beanTableView= new BeanTableView();
		TableTitle tableTitle = TableTitle.of(tableData.tableTitle);
		beanTableView.add(tableTitle);
		// 查询表单
        FormComponent formComponent = FormComponent.form(12, tableData.btnPrefix, tableData.searchInputs);
        beanTableView.add(formComponent);
        //没有删除按钮，就不需要table checkbox
		//if ((toolbarStyle & WiggetStyle.DEL)==0) {
		//   tableData.checkbox=false;
		//}
        // toobar
        ToolBar tableToolbar = ToolBar.tableToolbar(tableData.tableToolbar, tableData.btnPrefix, toolbarStyle,buttons);
        beanTableView.add(tableToolbar);
        // table
        Table table = Table.of().setId(tableData.tableId).addClass("table table-hover");
        beanTableView.add(table);
		
		return beanTableView;
	}

}