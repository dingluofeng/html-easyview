/**
 * 
 */
package com.eason.html.easyview.core.form;

import com.eason.html.easyview.core.form.provider.IComboDataProvider;
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

	private String defaultValue = "-1";

	public FormCombo(String id, String field, String lable, String[] names, String[] values) {
		super("div", id, field);
		addClass("form-group");
		add(Label.of().setFor(id).add(Text.of(lable)).addStyle("min-width:100px;"));
		select = Select.of().setId(id).addClass("form-control").addStyle("min-width:172px;margin-right:0px;");
		add(select);
		for (int i = 0; i < names.length; i++) {
			select.add(Option.of().setValue(values[i]).add(Text.of(names[i])));
		}
	}

	public static FormCombo of(String id, String field, String lable, String[] names, String[] values) {
		return new FormCombo(id, field, lable, names, values);
	}

	public static FormCombo of(String id, String field, String lable, IComboDataProvider dataProvider) {
		FormCombo formCombo = new FormCombo(id, field, lable, dataProvider.getItem(), dataProvider.getValue());
		formCombo.defaultValue = dataProvider.getDefault();
		return formCombo;
	}

	@Override
	public String getDefaultValue() {
		return defaultValue;
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
