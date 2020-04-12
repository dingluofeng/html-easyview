package com.eason.html.easyview.core.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Multiple {@link TableColumn} {@link Annotation}
 *
 * @see TableColumn
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableColumns {

	/**
	 * The value of {@link TableColumns}
	 *
	 * @return non-null
	 */
	TableColumn[] value();

}
