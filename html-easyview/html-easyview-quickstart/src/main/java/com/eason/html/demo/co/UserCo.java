package com.eason.html.demo.co;

import com.eason.html.easyview.core.WidgetType;
import com.eason.html.easyview.core.annotations.EasyView;

@EasyView(name = "用户信息")
public class UserCo {

	@EasyView(name = "性别", type = WidgetType.Combo, mapping= "{-1:'全部',0:'女',1:'男'}")
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
