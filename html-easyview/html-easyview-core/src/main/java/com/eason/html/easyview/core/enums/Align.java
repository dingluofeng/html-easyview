package com.eason.html.easyview.core.enums;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月23日 下午12:58:10
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月23日
 * @modify by reason:{方法名}:{原因}
 */
public enum Align {
	LEFT("left", "左对齐内容（默认值）"), RIGHT("right", "右对齐内容"), CENTER("center", "居中对齐内容"),
	JUSTIFY("justify", "对行进行伸展，这样每行都可以有相等的长度（就像在报纸和杂志中）"), CHAR("char", "将内容对准指定字符");

	public String value;

	public String name;

	private Align(String value, String name) {
		this.value = value;
		this.name = name;
	}

}
