package com.eason.html.test;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

public class MainTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(" ");
		list.add("e");

		// System.out.println(list.stream().collect(Collectors.joining()));
		System.out.println(Joiner.on("").join(list));
		System.out.println(Joiner.on("").join(list));
	}
}