package com.eason.html.easyview.core.widget;

public class Option extends Node<Option> {
	public Option() {
		super("option");
	}

	public static Option of() {
		return new Option();
	}
}