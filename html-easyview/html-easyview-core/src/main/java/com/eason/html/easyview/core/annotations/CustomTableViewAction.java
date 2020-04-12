package com.eason.html.easyview.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

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
@Inherited
@CustomQueryAction
public @interface CustomTableViewAction {

	@AliasFor(annotation = CustomQueryAction.class)
	String[] path() default "";

	@AliasFor(annotation = CustomQueryAction.class)
	Class<?> conditionForm() default Object.class;

	@AliasFor(annotation = CustomQueryAction.class)
	String title() default "";

	@AliasFor(annotation = CustomQueryAction.class)
	String id() default "";

}
