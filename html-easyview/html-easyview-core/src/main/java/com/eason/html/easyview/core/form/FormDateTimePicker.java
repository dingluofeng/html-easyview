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
public class FormDateTimePicker extends FormInput<FormDateTimePicker> implements DatetimeInput {

	private Input input;

	private String type;

	public FormDateTimePicker(String id, String field, String lable, String type) {
		super("div", id, field);
		this.type = type;
		addClass("form-group");
		add(Label.of().setFor(id).add(Text.of(lable)).addStyle("min-width:120px;"));
		input = Input.of().setId(id).setType("text").setPlaceholder(lable).addClass("form-control");
		add(input);
	}

	public static FormDateTimePicker of(String id, String field, String lable, String type) {
		return new FormDateTimePicker(id, field, lable, type);
	}

	@Override
	public void addAttr(String name) {
		input.addAttribute(Attribute.of(name));
	}

	@Override
	public String type() {
		return type;
	}

}
