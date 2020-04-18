package com.eason.html.easyview.core.widget;

public class OL extends Node<OL> {
	public OL() {
		super("ol");
	}

	public static OL of(String type) {
		return new OL().addAttribute("type", type);
	}
	
	public OL setList(String...items) {
		for (String text : items) {
			this.add(Li.of(text));
		}
		return this;
	}
	
	public static class Li extends Node<Li>{
		
		public Li() {
			super("li");
		}
		
		public static Li of(String text) {
			return new Li().add(Text.of(text));
		}
		
	}
}