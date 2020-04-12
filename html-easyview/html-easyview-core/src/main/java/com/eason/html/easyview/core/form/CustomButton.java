package com.eason.html.easyview.core.form;

import java.util.List;

import com.eason.html.easyview.core.DateTimeInfo;
import com.eason.html.easyview.core.QueryAction;
import com.eason.html.easyview.core.widget.Attribute;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Span;
import com.eason.html.easyview.core.widget.Text;

public class CustomButton extends Node<CustomButton> {

	private QueryAction queryAction;

	private CustomButton(QueryAction action) {
		super("button");
		this.queryAction = action;
		setId(queryAction.id()).setType("button").addClass("btn btn-primary btn-sm");
		add(Span.of().addClass("glyphicon glyphicon-search").addAttribute(Attribute.of("aria-hidden", "true")));
		addStyle("margin: 10px;");
		add(Text.of(queryAction.name()));
	}

	public static CustomButton of(QueryAction action) {
		CustomButton customButton = new CustomButton(action);
		return customButton;
	}

	public String getTitle() {
		return queryAction.name();
	}

	public String getUrl() {
		return queryAction.url();
	}

	public String getId() {
		return queryAction.id();
	}

	/**
	 * @return
	 * @see com.eason.html.easyview.core.widget.table.model.QueryAction#getCustomBtnId()
	 */
	public String getCustomBtnId() {
		return queryAction.getCustomBtnId();
	}

	/**
	 * @return
	 * @see com.eason.html.easyview.core.widget.table.model.QueryAction#getSearchInputs()
	 */
	public List<FormInput<?>> getSearchInputs() {
		return queryAction.getSearchInputs();
	}

	public List<DateTimeInfo> getDatetimeFields() {
		return queryAction.getDatetimeFields();
	}

	@Override
	public String toString() {
		return "CustomButton [queryAction=" + queryAction + "]";
	}

}