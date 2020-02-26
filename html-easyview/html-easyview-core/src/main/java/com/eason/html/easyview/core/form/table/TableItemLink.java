/**
 * 
 */
package com.eason.html.easyview.core.form.table;

import com.eason.html.easyview.core.widget.A;
import com.eason.html.easyview.core.widget.I;

/**
 * @author dingluofeng
 *
 */
public class TableItemLink {
	
	public String build(String id, String title,String classStyle) {
		return A.of().setId(id).setHref("javascript:void(0)").addAttribute("title", title).add(I.of().addClass(classStyle)).html();
	}

}
