package com.eason.html.easyview.core.widget;

import java.util.LinkedHashSet;
import java.util.Set;

public class Attribute {
	private String name;
	private Set<String> valueSet = new LinkedHashSet<>();

	public static Attribute of(String name) {
		return new Attribute().setName(name);
	}

	public static Attribute of(String name, String value) {
		return new Attribute().setName(name).addValue(value);
	}

	public Attribute addValue(String value) {
		this.valueSet.add(value);
		return this;
	}

	public Set<String> getValueSet() {
		return this.valueSet;
	}

	public String getName() {
		return this.name;
	}

	public Attribute setName(String name) {
		this.name = name;
		return this;
	}

	public String toString() {
		if (this.valueSet.size() == 0) {
			return this.name;
		}

		String value = Utils.joiningWithSpace(this.valueSet);
		return this.name + "=" + Utils.wrapWithDoubleQuotation(value);
	}
}
