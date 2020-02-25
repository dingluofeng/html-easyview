package com.eason.html.easyview.core.form.table;

import com.eason.html.easyview.core.utils.StringUtils;
import com.eason.html.easyview.core.widget.Hn;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class TableTitle extends Node<TableTitle> {

	private TableTitle(String title) {
		this("", title);
	}

	private TableTitle(String id, String title) {
		super("div");
		Hn h4 = Hn.of(4).add(Text.of(title)).addStyle("line-height:40px;height:40px;margin-left: 8px;");
		if (StringUtils.isNotBlank(id)) {
			h4.setId(id);
		}
		addStyle("background-color: #f5f5f5;").addStyle(
				"-moz-border-radius: 3px;-webkit-border-radius: 3px;border-top-left-radius:3px;border-top-right-radius: 3px")
				.add(h4);
	}

	public static TableTitle of(String title) {
		return new TableTitle(title);
	}

	public static TableTitle of(String id, String title) {
		return new TableTitle(id, title);
	}

}
