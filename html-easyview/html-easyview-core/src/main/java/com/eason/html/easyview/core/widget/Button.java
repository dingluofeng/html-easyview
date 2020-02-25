package com.eason.html.easyview.core.widget;

public class Button extends Node<Button> {
	public Button() {
		super("button");
	}

	public static Button of() {
		return new Button();
	}
}