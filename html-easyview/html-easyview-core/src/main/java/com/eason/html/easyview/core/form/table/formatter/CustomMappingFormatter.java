package com.eason.html.easyview.core.form.table.formatter;

import java.util.Map;

import com.eason.html.easyview.core.utils.JacksonUtils;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年2月1日 下午7:12:47
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年2月1日
 * @modify by reason:{方法名}:{原因}
 */
class CustomMappingFormatter implements TableColMappingFormatter {

	private final String functionName;

	private final String mapping;

	public CustomMappingFormatter(String functionName, String mapping) {
		super();
		this.functionName = functionName;
		Map<String, Object> parseObject = JacksonUtils.parseObject(mapping);
		this.mapping = JacksonUtils.toJsonString(parseObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.html.widget.table.js.functions.TableColMappingFormatter#functionName()
	 */
	@Override
	public String functionName() {
		return functionName;
	}

	@Override
	public String jsonMapping() {
		return mapping;
	}

}
