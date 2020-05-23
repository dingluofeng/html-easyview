package com.eason.html.demo;

import com.eason.html.easyview.core.annotations.EasyView;

public class Address {
	@EasyView(name = "国家")
	String contry;
	String provice;
	String city;
	public Address(String contry, String provice, String city) {
		super();
		this.contry = contry;
		this.provice = provice;
		this.city = city;
	}
	public String getContry() {
		return contry;
	}
	public void setContry(String contry) {
		this.contry = contry;
	}
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [contry=");
		builder.append(contry);
		builder.append(", provice=");
		builder.append(provice);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

}
