/**
 * 
 */
package com.eason.html.easyview.core.form.table;

import com.eason.html.easyview.core.IAction;
import com.eason.html.easyview.core.utils.StringUtils;
import com.eason.html.easyview.core.widget.A;
import com.eason.html.easyview.core.widget.I;

/**
 * @author dingluofeng
 *
 */
public class TableItemLink implements IAction {

	String id;

	String href = "javascript:void(0)";

	String title;

	String classStyle = "glyphicon glyphicon-hand-down";

	String url;

	private TableItemLink(String id, String href, String title, String classStyle, String url) {
		super();
		this.id = id;
		this.href = href;
		this.title = title;
		if (StringUtils.isNotBlank(classStyle)) {
			this.classStyle = classStyle;
		}
		this.url = url;
	}

	public static TableItemLink of(String id, String title, String url) {
		return of(id, title, "glyphicon glyphicon-hand-down", url);
	}

	public static TableItemLink of(String id, String title, String classStyle, String url) {
		return of(id, title, "javascript:void(0)", classStyle, url);
	}

	public static TableItemLink of(String id, String title, String href, String classStyle, String url) {
		return new TableItemLink(id, href, title, classStyle, url);
	}

	public String buildLink() {
		return A.of().setId(id).setHref(href).addAttribute("title", title)
				.add(I.of().addClass(classStyle).addStyle("padding-left:5px;")).html();
	}

	public String id() {
		return id;
	}

	public String title() {
		return title;
	}

	public String url() {
		return url;
	}

}
