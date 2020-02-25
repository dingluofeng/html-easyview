/**
 * 
 */
package com.eason.html.easyview.core.form.search;


import com.eason.html.easyview.core.form.layout.SpanLayout;
import com.eason.html.easyview.core.widget.Node;

/**
 * @author dingluofeng
 *
 */
public class CustomSearchForm extends Node<CustomSearchForm> {
    
    SpanLayout layout;

    private CustomSearchForm(int spanColumns,String id) {
        this(SpanLayout.of(spanColumns).setId(id));
	}

    private CustomSearchForm(SpanLayout layout) {
        super("div");
        this.layout = layout;
        addClass("row-fluid");
        add(layout);
    }

    public static CustomSearchForm form(int spanColumns, String id) {
        CustomSearchForm formComponent = new CustomSearchForm(spanColumns,id);
        return formComponent;
    }

    public SpanLayout layout() {
        return layout;
    }
}
