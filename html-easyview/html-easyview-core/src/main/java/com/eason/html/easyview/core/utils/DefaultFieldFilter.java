package com.eason.html.easyview.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.eason.html.easyview.core.utils.BeanRefectUtils.FieldFilter;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月10日 下午1:06:41
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月10日
 * @modify by reason:{方法名}:{原因}
 */
public class DefaultFieldFilter implements FieldFilter {

	public DefaultFieldFilter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hikvision.core.reflect.BeanUtils.FieldFilter#accept(java.lang.reflect.
	 * Field, java.lang.Object)
	 */
	@Override
	public boolean accept(Field field, Object fieldValue) {
		if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
			return false;
		}
		return true;
	}

}
