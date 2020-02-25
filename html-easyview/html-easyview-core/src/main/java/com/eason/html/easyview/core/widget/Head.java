package com.eason.html.easyview.core.widget;

public class Head extends Node<Head> {
	public Head() {
		super("head");
	}

	public static Head of() {
		return new Head();
	}
}
