package com.eason.html.easyview.core.widget;

/**
 * <p>
 * H1~H6 标签集合
 * </p>
 * 
 * @author DingLuoFeng 2020年1月24日 上午1:26:31
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月24日
 * @modify by reason:{方法名}:{原因}
 */
public class Hn extends Node<Hn> {
	public Hn(int headerNum) {
		super("h" + headerNum);
	}

	public static Hn of(int headerNum) {
		return new Hn(headerNum);
	}
}