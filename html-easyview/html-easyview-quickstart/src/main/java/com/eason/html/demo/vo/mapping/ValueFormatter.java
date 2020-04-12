/**
 * @ProjectName: 民用软件平台软件
 * @Copyright: 2012 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2020年2月1日 下午7:31:34
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.eason.html.demo.vo.mapping;

import java.util.HashMap;
import java.util.Map;

import com.eason.html.easyview.core.form.table.formatter.AbstractTableColMappingFormatter;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年2月1日 下午7:31:34
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年2月1日
 * @modify by reason:{方法名}:{原因}
 */
public class ValueFormatter extends AbstractTableColMappingFormatter {
	
	@Override
	public String functionName() {
        return "valueFormatter";
	}

	@Override
	public Map<String, String> mapping() {
		HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("value1", "手动");
        hashMap.put("value2", "自动");
		return hashMap;
	}

}
