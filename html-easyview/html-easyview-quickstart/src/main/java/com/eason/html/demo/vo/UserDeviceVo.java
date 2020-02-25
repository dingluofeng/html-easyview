/**
 * 
 */
package com.eason.html.demo.vo;

import java.util.Date;

import com.eason.html.demo.vo.mapping.AddTypeFormatter;
import com.eason.html.easyview.core.annotations.EasyView;

/**
 * @author dingluofeng
 *
 */
public class UserDeviceVo {

	private String userName;

	private String deviceId;

	private Date dateTime;

	@EasyView(name = "添加类型", mappingFormatter = AddTypeFormatter.class)
	private int type;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserDevice [userName=" + userName + ", deviceId=" + deviceId + ", dateTime=" + dateTime + ", type="
				+ type + "]";
	}

}
