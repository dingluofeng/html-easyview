package com.eason.html.easyview.core.widget;

public class Script extends Node<Script> {
	public Script() {
		super("script");
	}

	public static Script of(Text... texts) {
		return new Script().add(texts);
	}
}