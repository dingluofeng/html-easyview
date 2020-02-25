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
public enum Valign {
	TOP("top", "对内容进行上对齐"), MIDDLE("middle", "对内容进行居中对齐（默认值）"), BOTTOM("bottom", "对内容进行下对齐"),
	BASELINE("baseline", "与基线对齐");

	public String value;

	public String name;

	private Valign(String value, String name) {
		this.value = value;
		this.name = name;
	}

}
