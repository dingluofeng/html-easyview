package com.eason.html.easyview.core.form.table.formatter;

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
public final class NoneTableColMappingFormatter implements TableColMappingFormatter {

	private static final String NONE = "NONE";

	private static final String STRING = "{}";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.html.widget.table.js.functions.TableColMappingFormatter#functionName()
	 */
	@Override
	public String functionName() {
		return NONE;
	}

	@Override
	public String jsonMapping() {
		return STRING;
	}

}
