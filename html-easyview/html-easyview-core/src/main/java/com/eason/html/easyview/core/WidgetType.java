package com.eason.html.easyview.core;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年2月2日 下午9:37:19
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年2月2日
 * @modify by reason:{方法名}:{原因}
 */
public interface WidgetType {
	/**
	 * 日期时间控件
	 */
	String DateTime = "datetime";

	/**
	 * 日期控件
	 */
	String Date = "date";

	/**
	 * 时间控件
	 */
	String Time = "time";

	/**
	 * 文本控件
	 */
	String Text = "text";

	/**
	 * 数字控件
	 */
	String Number = "number";

	/**
	 * 密码控件
	 */
	String Password = "password";

	/**
	 * 下拉框控件
	 */
	String Combo = "combo";
}
