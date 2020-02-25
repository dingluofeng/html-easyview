package com.eason.html.easyview.core.widget;

public class Div extends Node<Div> {

	protected Div() {
		super("div");
	}

	public static Div of() {
		return new Div();
	}
}
