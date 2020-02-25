package com.eason.html.easyview.core.widget;

public class Label extends Node<Label> {
    public Label() {
		super("label");
	}

	public static Label of() {
        return new Label();
    }
}