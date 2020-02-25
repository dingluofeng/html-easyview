package com.eason.html.easyview.core.widget;

import java.util.List;
import java.util.Set;

import com.google.common.base.Joiner;

public class Utils {
	public static String wrapWithDoubleQuotation(String text) {
		return wrap("\"", text, "\"");
	}

	public static String wrap(String left, String text, String right) {
		return left + text + right;
	}

	public static String startTag(String text) {
		return wrap("<", text, ">");
	}

	public static String endTag(String text) {
		return wrap("</", text, ">");
	}

	public static String joiningWithSpace(Set<String> set) {
		return Joiner.on(" ").join(set);
		// return set.stream().collect(Collectors.joining(" "));
	}

	public static String joiningWithLineSeparator(List<String> list) {
		return Joiner.on(System.lineSeparator()).join(list);
		// return list.stream().collect(Collectors.joining(System.lineSeparator()));
	}
}
