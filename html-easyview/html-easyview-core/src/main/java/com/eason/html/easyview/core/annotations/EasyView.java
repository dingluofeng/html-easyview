package com.eason.html.easyview.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.eason.html.easyview.core.WidgetType;
import com.eason.html.easyview.core.basecontroller.BaseTableViewerController;
import com.eason.html.easyview.core.enums.Align;
import com.eason.html.easyview.core.enums.Valign;
import com.eason.html.easyview.core.form.table.formatter.NoneTableColMappingFormatter;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatter;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月10日 下午12:51:21
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月10日
 * @modify by reason:{方法名}:{原因}
 */
public @interface EasyView {

	/**
	 * @author DingLuoFeng 2020年1月23日 下午1:44:28
	 * @return 显示名称，(注解在对象上，表示对象名称，注解在属性上作为控件显示名称)
	 */
	String name();

	/**
	 * @author DingLuoFeng 2020年1月23日 下午1:44:04
	 * @return 映射字段，(一般用作控件ID)
	 */
	String field() default "";

	/**
	 * @author DingLuoFeng 2020年1月23日 下午1:43:38
	 * @return 文本水平对齐方式(<font color="red">表格显示使用</font>)
	 */
	Align align() default Align.CENTER;

	/**
	 * @author DingLuoFeng 2020年1月23日 下午1:42:54
	 * @return 文本垂直对齐方式，(<font color="red">表格显示使用</font>)
	 */
	Valign valign() default Valign.MIDDLE;

	/**
	 * @author DingLuoFeng 2020年1月23日 下午1:42:34
	 * @return 是否支持列排序，(<font color="red">表格显示使用</font>)
	 */
	boolean sortable() default false;

	/**
	 * @author DingLuoFeng 2020年1月23日 下午1:41:42
	 * @return 标记是否是唯一主键， <font color="red">在表格删除，修改是需要做索引</font>
	 * @see BaseTableViewerController#update(Object)
	 * @see BaseTableViewerController#delete(String[])
	 */
	boolean uniqueId() default false;

	/**
	 * @author DingLuoFeng 2020年1月26日 下午7:16:53
	 * @return 是否隐藏显示列，(<font color="red">表格显示使用</font>)
	 */
	boolean columnHidden() default false;

	/**
	 * @author DingLuoFeng 2020年1月23日 下午1:45:30
	 * @return 表格第一列是否显示选择框，(<font color="red">表格显示使用</font>)
	 */
	boolean checkbox() default true;

	/**
	 * @author DingLuoFeng 2020年1月23日 下午1:45:55
	 * @return 表格是否显示行号，(<font color="red">表格显示使用</font>)
	 */
	boolean indexed() default true;

	/**
	 * @author DingLuoFeng 2020年1月23日 下午1:46:17
	 * @return 表格最后是否显示操作列(修改|删除)，(<font color="red">表格显示使用</font>)
	 * @see BaseTableViewerController#update(Object)
	 * @see BaseTableViewerController#delete(String[])
	 */
	boolean itemOpt() default false;

	/**
	 * ============================================================================
	 * =============================控件注解方法类=================================
	 * ============================================================================
	 */
	/**
	 * @author DingLuoFeng 2020年1月26日 下午6:20:08
	 * @return 控件类型名称
	 */
	String type() default WidgetType.Text;

	/**
	 * @return key的列表。
	 */
	String[] values() default {};

	/**
	 * @return 对应上面values中key的要显示的lable 例如{"成功",失败",异常"}
	 */
	String[] list() default {};

	/**
	 * @return 数据提供器
	 */
	Class<?> dataProvider() default Object.class;

	/**
	 * @author DingLuoFeng 2020年1月26日 下午6:25:22
	 * @return 是否作为查询条件
	 */
	boolean queryCondition() default true;

    /**
     * @return
     */
    Class<? extends TableColMappingFormatter> mappingFormatter() default NoneTableColMappingFormatter.class;

	/**
	 * @author DingLuoFeng 2020年2月2日 下午1:43:56
	 * @return 是否需要空值验证（先实现简单的空值验证）
	 */
	boolean validate() default false;

	/**
	 * @author DingLuoFeng 2020年2月2日 下午3:41:16
	 * @return 修改记录是标记是否允许修改
	 */
	boolean readonly() default false;

	String dateRange() default "";

	/**
	 * 
	 * @return 表示该字段不在当前表格展示，需要扩展展示
	 */
	boolean expandRowView() default false;

    /**
     * <p>
     * 转义HTML字符串，替换 &, <, >, ", `, 和 ' 字符.
     * </p>
     * 
     * @author DingLuoFeng 2020年3月4日 下午12:40:21
     * @return
     */
    boolean escape() default true;

    /**
     * <p>
     * 是否以卡片的形式展示
     * </p>
     * 
     * @author DingLuoFeng 2020年4月3日 下午8:39:42
     * @return
     */
    boolean cardView() default false;

}
