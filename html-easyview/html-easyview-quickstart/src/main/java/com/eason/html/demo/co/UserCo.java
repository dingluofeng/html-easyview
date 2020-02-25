package com.eason.html.demo.co;

import com.eason.html.demo.vo.mapping.SexFormatter;
import com.eason.html.easyview.core.WidgetType;
import com.eason.html.easyview.core.annotations.EasyView;

@EasyView(name = "用户信息")
public class UserCo {

	@EasyView(name = "性别", type = WidgetType.Combo, values = { "-1", "1", "0" }, list = { "全部", "男",
			"女" }, mappingFormatter = SexFormatter.class)
	private String sexExt;

	public String getSexExt() {
		return sexExt;
	}

	public void setSexExt(String sexExt) {
		this.sexExt = sexExt;
	}

	@Override
	public String toString() {
		return "UserCo [sexExt=" + sexExt + "]";
	}
}
