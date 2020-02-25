package com.eason.html.easyview.core.form.search;

import java.util.List;

import com.eason.html.easyview.core.form.FormInput;
import com.eason.html.easyview.core.widget.Attribute;
import com.eason.html.easyview.core.widget.Button;
import com.eason.html.easyview.core.widget.Node;
import com.eason.html.easyview.core.widget.Span;
import com.eason.html.easyview.core.widget.Text;
/**
 * 
 * @author dingluofeng
 *
 */
public class SearchFormGroup extends Node<SearchFormGroup> {

	private SearchFormGroup() {
		super("div");
		addClass("control-group form-inline").addStyle("border: 1px solid #ccc;padding: 10px; border-radius: 3px;");
	}

	public static SearchFormGroup searchFormGroup(String btnPrefix, List<FormInput<?>> searchInputs) {
		SearchFormGroup searchFormGroup = new SearchFormGroup();
		// FormInput
		for (FormInput<?> formInput : searchInputs) {
			searchFormGroup.add(formInput);
		}

		String id = btnPrefix + "_custom_btn_search";
		Button queryBtn = Button.of().addAttribute(Attribute.of("onclick", id+"onclick();")).setId(id).setType("button").addClass("btn btn-primary btn-sm");
		queryBtn.add(Span.of().addClass("glyphicon glyphicon-search").addAttribute(Attribute.of("aria-hidden", "true")));
		queryBtn.add(Text.of("查询"));
		searchFormGroup.add(queryBtn);
//		if (searchInputs.size() > 0) {
//			// 固定两按钮
//			Button cleanBtn = Button.of().setId(btnPrefix + "_btn_clean_search").setType("button")
//					.addClass("btn btn-danger btn-sm").add(Text.of("清空条件"));
//			searchFormGroup.add(cleanBtn);
//		}
		return searchFormGroup;
	}
}