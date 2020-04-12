package com.eason.html.easyview.core.form.table.formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public abstract class AbstractTableColMappingFormatter implements TableColMappingFormatter {

	private List<String> keyList = new ArrayList<>();

	private Map<String, String> colValueMapping = new HashMap<>();

	@Override
	public final String jsonMapping() {
		return JacksonUtils.toJsonString(mapping());
	}

	protected Map<String, String> mapping() {
		return colValueMapping;
	}

	protected void add(String value, String viewLabel) {
		keyList.add(value);
		colValueMapping.put(value, viewLabel);
	}

	public List<String> keys() {
		return keyList;
	}

	public List<String> values() {
		List<String> values = new ArrayList<>();
		for (String key : keyList) {
			values.add(colValueMapping.get(key));
		}
		return values;
	}
}
