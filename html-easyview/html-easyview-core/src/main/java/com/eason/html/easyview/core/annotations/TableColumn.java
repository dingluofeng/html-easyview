
package com.eason.html.easyview.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.eason.html.easyview.core.basecontroller.BaseTableViewerController;
import com.eason.html.easyview.core.enums.Align;
import com.eason.html.easyview.core.enums.Valign;
import com.eason.html.easyview.core.form.table.formatter.NoneTableColMappingFormatter;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatter;


@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Repeatable(TableColumns.class)
public @interface TableColumn {

    String title();
    /**
     * @author DingLuoFeng 2020年1月23日 下午1:44:04
     * @return 映射字段，(一般用作控件ID)
     */
    String field();

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
     * <p>
     * mapping 映射 json字符串
     * </p>
     * 
     * @author DingLuoFeng 2020年4月10日 上午9:53:30
     * @return
     */
    String mapping() default "";

    Class<? extends TableColMappingFormatter> mappingFormatter() default NoneTableColMappingFormatter.class;
}
