package com.eason.html.demo.co;

import com.eason.html.easyview.core.WidgetType;
import com.eason.html.easyview.core.annotations.EasyView;

public class DevSuspectedBlacklistCo {

	@EasyView(name = "注册时间", type = WidgetType.DateTime, dateRange = "~")
	private String regTime;

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DevRegBlacklistCo [regTime=");
		builder.append(regTime);
		builder.append("]");
		return builder.toString();
	}

}
