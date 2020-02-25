package com.eason.html.easyview.core.widget;

public class Table extends Node<Table> {
    public Table() {
		super("table");
	}

	public static Table of() {
        return new Table();
    }
}
