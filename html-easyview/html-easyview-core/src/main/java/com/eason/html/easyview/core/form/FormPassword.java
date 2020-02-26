/**
 * 
 */
package com.eason.html.easyview.core.form;

import com.eason.html.easyview.core.widget.Attribute;
import com.eason.html.easyview.core.widget.Input;
import com.eason.html.easyview.core.widget.Label;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class FormPassword extends FormInput<FormPassword> {

	private Input input;

	public FormPassword(String id, String field, String lable) {
		super("div", id, field);
		addClass("form-group");
		add(Label.of().setFor(id).add(Text.of(lable)).addStyle("min-width:120px;"));
		input = Input.of().setId(id).setType("password").setPlaceholder(lable).addClass("form-control");
		add(input);
	}

	public static FormPassword of(String id, String field, String lable) {
		return new FormPassword(id, field, lable);
	}

	@Override
	public void addAttr(String name) {
		input.addAttribute(Attribute.of(name));
	}

}
