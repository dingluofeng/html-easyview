package com.eason.html.easyview.core.widget.css;

import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Text;

public class CssStyle extends Node<CssStyle> {

	private CssStyle() {
		super("style");
	}

	public static CssStyle of(String cssText) {
		return new CssStyle().setType("text/css").add(Text.of(cssText));
	}

}
