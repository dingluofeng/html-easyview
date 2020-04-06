package com.eason.html.easyview.core.form.table;

import java.util.List;

import com.eason.html.easyview.core.WidgetStyle;
import com.eason.html.easyview.core.form.CustomButton;
import com.eason.html.easyview.core.form.ToolItemButton;
import com.eason.html.easyview.core.widget.Attribute;
import com.eason.html.easyview.core.widget.Button;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Span;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class ToolBar extends Node<ToolBar> implements WidgetStyle {

	public ToolBar() {
		super("div");
		addClass("btn-group");
	}

	public static ToolBar tableToolbar(String toolbarId, String btnPrefix, int btnStyle, List<ToolItemButton> buttons) {
		ToolBar toolBar = new ToolBar();
		toolBar.setId(toolbarId);
		if ((ADD & btnStyle) != 0) {
			Button addBtn = Button.of().setId(btnPrefix + "_btn_add").setType("button")
					.addClass("btn btn-default btn-sm").add(Span.of().addClass("glyphicon glyphicon-plus")
							.addAttribute(Attribute.of("aria-hidden", "true")))
					.add(Text.of("新增"));
			toolBar.add(addBtn);
		}
		if ((DEL & btnStyle) != 0) {
			Button delBtn = Button.of().setId(btnPrefix + "_btn_delete").setType("button")
					.addClass("btn btn-default btn-sm").add(Span.of().addClass("glyphicon glyphicon-trash")
							.addAttribute(Attribute.of("aria-hidden", "true")))
					.add(Text.of("删除"));
			toolBar.add(delBtn);
		}
		if ((REFLUSH & btnStyle) != 0) {
			Button delBtn = Button.of().setId(btnPrefix + "_btn_refresh").setType("button")
					.addClass("btn btn-default btn-sm").add(Span.of().addClass("glyphicon glyphicon-refresh")
							.addAttribute(Attribute.of("aria-hidden", "true")))
					.add(Text.of("刷新"));
			toolBar.add(delBtn);
		}
		if ((IMPORT & btnStyle) != 0) {
			Button delBtn = Button.of().setId(btnPrefix + "_btn_upload").setType("button")
					.addClass("btn btn-default btn-sm").add(Span.of().addClass("glyphicon glyphicon-import")
							.addAttribute(Attribute.of("aria-hidden", "true")))
					.add(Text.of("导入"));
			toolBar.add(delBtn);
		}

		for (ToolItemButton button : buttons) {
			toolBar.add(button);
		}
		return toolBar;
	}

	public static ToolBar customTableToolbar(String toolbarId, List<CustomButton> buttons) {
		ToolBar toolBar = new ToolBar();
		toolBar.setId(toolbarId);

		for (CustomButton button : buttons) {
			toolBar.add(button);
		}
		return toolBar;
	}

	public static void main(String[] args) {
		System.out.println((DEL | REFLUSH) & ADD);
	}

}
