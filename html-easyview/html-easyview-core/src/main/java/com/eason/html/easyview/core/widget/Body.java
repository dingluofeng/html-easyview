package com.eason.html.easyview.core.widget;

public class Body extends Node<Body> {
	public Body() {
		super("body");
	}

	public static Body of() {
		return new Body();
	}
}
