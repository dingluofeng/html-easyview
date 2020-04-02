/**
 * 
 */
package com.eason.html.easyview.core;

/**
 * @author dingluofeng
 *
 */
public class ToolBarAction implements IAction{

	private String id;

	private String name;

	private String url;
	
	private String classStyle = IconStyle.CHECKED;

	private String customBtnId;

	public ToolBarAction(String id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public String id() {
		return id;
	}

	public void id(String id) {
		this.id = id;
	}

	public String name() {
		return name;
	}

	public void name(String name) {
		this.name = name;
	}

	public void url(String url) {
		this.url = url;
	}

	public String url() {
		return url;
	}

	public String getCustomBtnId() {
		return customBtnId;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	@Override
	public String toString() {
		return "ToolBarAction [id=" + id + ", name=" + name + ", url=" + url + ", classStyle=" + classStyle
				+ ", customBtnId=" + customBtnId + "]";
	}

}
