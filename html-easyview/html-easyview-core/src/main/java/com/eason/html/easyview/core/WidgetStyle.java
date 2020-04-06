package com.eason.html.easyview.core;

/**
 * <p>
 * tableview 工具栏固定功能按钮是否启用常量类
 * </p>
 * 
 * @author DingLuoFeng 2020年1月22日 下午7:47:24
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月22日
 * @modify by reason:{方法名}:{原因}
 */
public interface WidgetStyle {

	public final static int NONE = 0;

	public final static int ADD = 1;

	public final static int DEL = 2;

	public final static int REFLUSH = 4;

	public final static int IMPORT = 8;

	// public final static int EXPORT = 16;

	public final static int ALL = IMPORT | REFLUSH | DEL | ADD;

}
