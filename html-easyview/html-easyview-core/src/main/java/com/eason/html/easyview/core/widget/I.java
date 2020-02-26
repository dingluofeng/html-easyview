package com.eason.html.easyview.core.widget;

public class I extends Node<I> {
    public I() {
		super("i");
	}

	public static I of() {
        return new I();
    }
}