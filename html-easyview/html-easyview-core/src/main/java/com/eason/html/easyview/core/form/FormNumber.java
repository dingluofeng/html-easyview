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
public class FormNumber extends FormInput<FormNumber> {

	private Input input;

	public FormNumber(String id, String field, String lable) {
		super("div", id, field);
		addClass("form-group");
		add(Label.of().setFor(id).add(Text.of(lable)));
		input = Input.of().setId(id).setType("number").setPlaceholder(lable).addClass("form-control");
		add(input);
	}

	public static FormNumber of(String id, String field, String lable) {
		return new FormNumber(id, field, lable);
	}

	@Override
	public void addAttr(String name) {
		input.addAttribute(Attribute.of(name));
	}
}
