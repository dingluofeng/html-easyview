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
public class FormText extends FormInput<FormText> {

	private Input input;

	public FormText(String id, String field, String lable) {
		this(id, field, lable, "text");
	}

	public FormText(String id, String field, String lable, String type) {
		super("div", id, field);
		addClass("form-group");
		add(Label.of().setFor(id).add(Text.of(lable)).addStyle("min-width:100px;"));
		input = Input.of().setId(id).setType(type).setPlaceholder(lable).addClass("form-control");
		add(input);
	}

	public static FormText of(String id, String field, String lable, String type) {
		return new FormText(id, field, lable, type);
	}

	public static FormText of(String id, String field, String lable) {
		return new FormText(id, field, lable);
	}

	@Override
	public void addAttr(String name) {
		input.addAttribute(Attribute.of(name));
	}

}
