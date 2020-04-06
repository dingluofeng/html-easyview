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
public interface WidgetStyle extends IMessageForm {

	int NONE = 0;

	int ADD = 1;

	int DEL = 2;

	int REFLUSH = 4;

	int IMPORT = 8;

	// int EXPORT = 16;

	int ALL = IMPORT | REFLUSH | DEL | ADD;

}
