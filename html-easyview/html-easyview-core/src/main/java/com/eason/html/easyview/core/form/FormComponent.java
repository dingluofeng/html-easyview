/**
 * 
 */
package com.eason.html.easyview.core.form;

import java.util.List;

import com.eason.html.easyview.core.form.layout.SpanLayout;
import com.eason.html.easyview.core.widget.Attribute;
import com.eason.html.easyview.core.widget.Button;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Span;
import com.eason.html.easyview.core.widget.Text;

/**
 * @author dingluofeng
 *
 */
public class FormComponent extends Node<FormComponent> {

	SpanLayout layout;

	private FormComponent(int spanColumns) {
		this(SpanLayout.of(spanColumns));
	}

	private FormComponent(SpanLayout layout) {
		super("div");
		this.layout = layout;
		addClass("row-fluid");
		add(layout);
	}

	public static FormComponent form(int spanColumns, String btnPrefix, List<FormInput<?>> searchInputs) {
		FormComponent formComponent = new FormComponent(spanColumns);
		FormGroup formGroup = FormGroup.formGroup();
		formComponent.layout.add(formGroup);

		// FormInput
		for (FormInput<?> formInput : searchInputs) {
			formGroup.add(formInput);
		}

		Button queryBtn = Button.of().setId(btnPrefix + "_btn_search").setType("button")
				.addClass("btn btn-primary btn-sm").addStyle("margin: 5px;");
		queryBtn.add(
				Span.of().addClass("glyphicon glyphicon-search").addAttribute(Attribute.of("aria-hidden", "true")));
		queryBtn.add(Text.of("查询"));
		formGroup.add(queryBtn);
		if (searchInputs.size() > 0) {
			// 固定两按钮
			Button cleanBtn = Button.of().setId(btnPrefix + "_btn_clean_search").setType("button")
					.addClass("btn btn-danger btn-sm").add(Text.of("清空条件")).addStyle("margin: 5px;");
			formGroup.add(cleanBtn);
		}
		return formComponent;
	}

	public static FormComponent of(SpanLayout layout, List<FormInput<?>> inputs, Button... buttons) {
		return new FormComponent(layout);
	}

	public SpanLayout layout() {
		return layout;
	}

	private static class FormGroup extends Node<FormGroup> {

		private FormGroup() {
			super("div");
			addClass("control-group form-inline").addStyle("border: 1px solid #ccc;padding: 10px; border-radius: 3px;");
		}

		private static FormGroup formGroup() {
			return new FormGroup();
		}

	}
}
