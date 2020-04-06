package com.eason.html.easyview.core.widget;

public class HiddenInput extends Node<HiddenInput> {

	private HiddenInput(String id, String value) {
		super("input");
		setType("hidden").setName(id).addClass("form-control").setId(id);
		this.addAttribute(Attribute.of("value", value));
	}

	public static HiddenInput of(String id, String value) {
		return new HiddenInput(id, value).setRequiresEndTag(false);
	}
}