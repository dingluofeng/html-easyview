package com.eason.html.easyview.core.widget;

public class Title extends Node<Title> {
	public Title() {
		super("title");
	}

	public static Title of() {
		return new Title();
	}
}
