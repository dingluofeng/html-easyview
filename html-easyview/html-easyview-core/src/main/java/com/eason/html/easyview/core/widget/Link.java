package com.eason.html.easyview.core.widget;

public class Link extends Node<Link> {
	public Link() {
		super("link");
	}

	public static Link of() {
		return new Link();
	}
}
