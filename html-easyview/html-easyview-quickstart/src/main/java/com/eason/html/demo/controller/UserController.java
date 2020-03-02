package com.eason.html.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eason.html.demo.vo.UserDeviceVo;
import com.eason.html.demo.vo.UserInfoVo;
import com.eason.html.easyview.core.PageHolder;
import com.eason.html.easyview.core.WidgetStyle;
import com.eason.html.easyview.core.annotations.CustomQueryAction;
import com.eason.html.easyview.core.annotations.TableViewController;
import com.eason.html.easyview.core.basecontroller.BaseTableViewerController;
import com.eason.html.easyview.core.basecontroller.PageParams;

/**
 * 用户列表查询
 * 
 * @author dingluofeng
 *
 */
@TableViewController(value="/user",showDefaultItemOpt = false )
public class UserController extends BaseTableViewerController<UserInfoVo, UserInfoVo> {

	public UserController() {
		super("用户信息", WidgetStyle.IMPORT);
		setOnlineResource(false);
	}

    @CustomQueryAction(id = "userDevice", path = "/userDevice", text = "用户设备列表")
	public UserDeviceVo userDevice(String subSerial) {
		System.out.println("userDevice:subSerial:" + subSerial);
		List<UserDeviceVo> devices = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			UserDeviceVo userDevice = new UserDeviceVo();
			userDevice.setUserName(subSerial);
			userDevice.setDateTime(new Date());
			userDevice.setType(i % 2 == 0 ? 1 : 0);
			userDevice.setDeviceId("deviceId" + i);
			devices.add(userDevice);
		}
		return devices.get(0);
	}

	@Override
	protected PageHolder<UserInfoVo> list(PageParams pageParams, UserInfoVo co) {
		List<UserInfoVo> records = new ArrayList<UserInfoVo>();
		for (int i = 0; i < 10; i++) {
			UserInfoVo user = new UserInfoVo();
			user.setAddress("address" + i);
			user.setUserName("userName" + i);
			user.setSex(i == 0 ? "3" : (i % 2 == 0 ? "1" : "0"));
			user.setAge(i * i);
			Map<String, String> options = new HashMap<>();
			if (i % 2 == 0) {
				for (int j = 0; j < 5; j++) {
					options.put("key" + j, "value" + j);
				}
				user.setOptions(options);
			}
			records.add(user);
		}
		logger.infof("查询用户列表：%s", records);
		return new PageHolder<>(21, records);
	}

}
