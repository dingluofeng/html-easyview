/**
 * 
 */
package com.eason.html.easyview.core.form;

import com.eason.html.easyview.core.form.validate.Validate;
import com.eason.html.easyview.core.widget.Node;

/**
 * @author dingluofeng
 *
 */
@SuppressWarnings("rawtypes")
public abstract class FormInput<T extends FormInput> extends Node<T> {

	String field;

	private String id;

	private Validate validate;

	private boolean readonly;

	public FormInput(String tagName, String id, String field) {
		super(tagName);
		this.field = field;
		this.id = id;
		addStyle("margin: 5px;");
	}

	public String getDefaultValue() {
		return "";
	}

	public String getField() {
		return field;
	}

	public String getId() {
		return id;
	}

	public String getValueScript() {
		return "$('#" + getId() + "').val();";
	}

	public Validate getValidate() {
		return validate;
	}

	public void setValidate(Validate validate) {
		this.validate = validate;
	}

	public abstract void addAttr(String name);

	public void readonly(boolean b) {
		readonly = b;
	}

	public boolean readonly() {
		return readonly;
	}
}
