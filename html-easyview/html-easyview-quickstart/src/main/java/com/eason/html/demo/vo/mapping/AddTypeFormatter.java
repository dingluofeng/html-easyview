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

import com.eason.html.demo.service.BlacklistService;
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
public class AddTypeFormatter extends AbstractTableColMappingFormatter {
	
	private BlacklistService blacklistService;

	@Override
	public String functionName() {
		return "addTypeFormatter";
	}

	@Override
	public Map<String, String> mapping() {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("1", "手动");
		hashMap.put("0", "自动");
		blacklistService.delete("123456789");
		return hashMap;
	}

	public BlacklistService getBlacklistService() {
		return blacklistService;
	}

	public void setBlacklistService(BlacklistService blacklistService) {
		this.blacklistService = blacklistService;
	}

}
