/**
 * 
 */
package com.eason.html.test;

import java.util.List;

import com.eason.html.easyview.core.annotations.EasyView;

/**
 * @author dingluofeng
 *
 */
public class UserInfo {
	
	@EasyView(name = "用户名")
	String username;
	@EasyView(name = "年龄")
	int age;
	@EasyView(name = "地址")
	List<Address> address;
	
	@EasyView(name = "兴趣")
	private List<String[]> favs;
	
	@EasyView(name = "兴趣2")
	private List<String> favs2;
	
	public UserInfo(String username, int age, List<Address> address) {
		super();
		this.username = username;
		this.age = age;
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public List<String[]> getFavs() {
		return favs;
	}
	public void setFavs(List<String[]> favs) {
		this.favs = favs;
	}
	public List<String> getFavs2() {
		return favs2;
	}
	public void setFavs2(List<String> favs2) {
		this.favs2 = favs2;
	}
	
}
