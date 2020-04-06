package com.eason.html.easyview.core.widget;

public class A extends Node<A> {
	public A() {
		super("a");
	}

	public static A of() {
		return new A();
	}
}