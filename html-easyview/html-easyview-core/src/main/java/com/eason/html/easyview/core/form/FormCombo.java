/**
 * 
 */
package com.eason.html.easyview.core.form;

import com.eason.html.easyview.core.widget.Attribute;
import com.eason.html.easyview.core.widget.Label;
import com.eason.html.easyview.core.widget.Option;
import com.eason.html.easyview.core.widget.Select;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class FormCombo extends FormInput<FormCombo> {

	private Select select;

	public FormCombo(String id, String field, String lable, String[] names, String[] values) {
		super("div", id, field);
//		addClass("input-group input-group-sm");
		addClass("form-group");
		add(Label.of().setFor(id).add(Text.of(lable)).addStyle("min-width:120px;"));
		select = Select.of().setId(id).addClass("form-control");
		add(select);
		for (int i = 0; i < names.length; i++) {
			select.add(Option.of().setValue(values[i]).add(Text.of(names[i])));
		}
	}

	public static FormCombo of(String id, String field, String lable, String[] names, String[] values) {
		return new FormCombo(id, field, lable, names, values);
	}

	@Override
	public String getDefaultValue() {
		return "-1";
	}

	@Override
	public String getValueScript() {
		return "$('#" + getId() + " option:selected').val();";
	}

	@Override
	public void addAttr(String name) {
		select.addAttribute(Attribute.of(name));
	}

}
