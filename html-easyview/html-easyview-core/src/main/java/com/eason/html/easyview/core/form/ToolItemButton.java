package com.eason.html.easyview.core.form;

import com.eason.html.easyview.core.ToolBarAction;
import com.eason.html.easyview.core.widget.Attribute;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Span;
import com.eason.html.easyview.core.widget.Text;

public class ToolItemButton extends Node<ToolItemButton> {

	private ToolBarAction toolBarAction;

	private ToolItemButton(ToolBarAction action) {
		super("button");
		this.toolBarAction = action;
		setId(toolBarAction.id()).setType("button").addClass("btn btn-default btn-sm");
		add(Span.of().addClass(action.getClassStyle()).addAttribute(Attribute.of("aria-hidden", "true")));
		add(Text.of(toolBarAction.name()));
	}

	public static ToolItemButton of(ToolBarAction action) {
		ToolItemButton customButton = new ToolItemButton(action);
		return customButton;
	}

	public String getTitle() {
		return toolBarAction.name();
	}

	public String getUrl() {
		return toolBarAction.url();
	}

	public String getId() {
		return toolBarAction.id();
	}

	public String getCustomBtnId() {
		return toolBarAction.getCustomBtnId();
	}

	@Override
	public String toString() {
		return "ToolItemButton [toolBarAction=" + toolBarAction + "]";
	}

}