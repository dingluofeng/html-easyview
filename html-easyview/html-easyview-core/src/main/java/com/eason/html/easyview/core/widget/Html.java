package com.eason.html.easyview.core.widget;

public class Html extends Node<Html> {
	public Html() {
		super("html");
	}

	public static Html of() {
		return new Html();
	}
}
