/**
 * 
 */
package com.eason.html.demo.vo;

import com.eason.html.easyview.core.form.provider.IComboDataProvider;

/**
 * @author dingluofeng
 *
 */
public class DeletedDataProvider implements IComboDataProvider {

	@Override
	public String[] getItem() {
		return new String[] {"全部","是","否"};
	}

	@Override
	public String[] getValue() {
		return new String[]{"","true","false"};
	}

	@Override
	public String getDefault() {
		return "";
	}

}
