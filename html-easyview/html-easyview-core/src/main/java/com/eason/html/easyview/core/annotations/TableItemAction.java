package com.eason.html.easyview.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eason.html.easyview.core.IconStyle;

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
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@RequestMapping
@ResponseBody
public @interface TableItemAction {

	/**
	 * <p>
	 * 按钮执行URL
	 * </p>
	 * 
	 * @return
	 */
	@AliasFor(annotation = RequestMapping.class, value = "value")
	String[] path();

	/**
	 * <p>
	 * 按钮显示文本
	 * </p>
	 * 
	 * @return
	 */
	String title() default "";

	/**
	 * <p>
	 * 按钮样式
	 * </p>
	 * 
	 * @return
	 */
	String styleClass() default IconStyle.HAND_DOWN;

}
