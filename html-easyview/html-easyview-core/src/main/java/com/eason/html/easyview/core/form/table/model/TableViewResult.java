package com.eason.html.easyview.core.form.table.model;

import java.util.LinkedHashMap;

import com.eason.html.easyview.core.basecontroller.ResponseResult;
import com.eason.html.easyview.core.utils.AssertUtils;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月22日 下午2:06:09
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月22日
 * @modify by reason:{方法名}:{原因}
 */
public class TableViewResult extends LinkedHashMap<String, Object> {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	public TableViewResult() {
		this(0, "OK", 0, null);
	}

	public TableViewResult(ResponseResult result) {
		this(result.getStatus(), result.getMsg(), 0, null);
	}

	public TableViewResult(int status, String msg) {
		this(status, msg, 0, null);
	}

	public TableViewResult(int status, int total, Object data) {
		this(0, "OK", total, data);
	}

	public TableViewResult(int status, String msg, int total, Object data) {
		super();
		addAttribute("status", status);
		addAttribute("total", total);
		addAttribute("msg", msg);
		if (data != null) {
			addAttribute("data", data);
		}
	}

	public void setMsgType(int type) {
		addAttribute("type", type);
	}

	public void setMsgTitle(String title) {
		addAttribute("title", title);
	}

	public void setArea(String area) {
		addAttribute("area", area);
	}

	public void setTotal(int total) {
		addAttribute("total", total);
	}

	public void setData(Object data) {
		if (data != null) {
			addAttribute("data", data);
		}
	}

	private TableViewResult addAttribute(String attributeName, Object attributeValue) {
		AssertUtils.notNull(attributeName, "Model attribute name must not be null");
		put(attributeName, attributeValue);
		return this;
	}

}
