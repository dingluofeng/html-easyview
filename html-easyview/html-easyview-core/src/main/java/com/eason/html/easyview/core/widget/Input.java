package com.eason.html.easyview.core.widget;

public class Input extends Node<Input> {
	public Input() {
		super("input");
	}

	public static Input of() {
		return new Input().setRequiresEndTag(false);
	}
}