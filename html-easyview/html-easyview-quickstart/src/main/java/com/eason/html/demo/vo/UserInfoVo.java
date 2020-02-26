package com.eason.html.demo.vo;

import java.io.Serializable;
import java.util.Map;

import com.eason.html.demo.vo.mapping.SexFormatter;
import com.eason.html.easyview.core.WidgetType;
import com.eason.html.easyview.core.annotations.EasyView;

@EasyView(name = "用户信息", sortable = true)
public class UserInfoVo implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	@EasyView(name = "用户名称", uniqueId = true, type = WidgetType.Text)
	private String userName;

	@EasyView(name = "住址", columnHidden = true)
	private String address;

	@EasyView(name = "性别", type = WidgetType.Combo, values = { "-1", "1", "0" }, list = { "全部", "男",
			"女" }, mappingFormatter = SexFormatter.class)
	private String sex;

	@EasyView(name = "年龄", type = "Number", queryCondition = true)
	private int age;

	@EasyView(name = "住址", columnHidden = true, queryCondition = false)
	private Map<String, String> options;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", address=" + address + ", sex=" + sex + ", age=" + age
				+ ", options=" + options + "]";
	}

}
