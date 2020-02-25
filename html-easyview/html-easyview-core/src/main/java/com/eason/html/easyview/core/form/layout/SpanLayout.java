package com.eason.html.easyview.core.form.layout;

import com.eason.html.easyview.core.widget.Node;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月21日 下午1:27:33
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月21日
 * @modify by reason:{方法名}:{原因}
 */
public class SpanLayout extends Node<SpanLayout> {

	private SpanLayout(int spanColumns) {
		super("div");
		addClass("span" + spanColumns);
	}

	public static SpanLayout of(int spanColumns) {
		return new SpanLayout(spanColumns);
	}

}
