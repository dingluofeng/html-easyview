package com.eason.html.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eason.html.demo.co.DevSuspectedBlacklistCo;
import com.eason.html.demo.service.BlacklistService;
import com.eason.html.demo.vo.DevRegBlacklistVo;
import com.eason.html.demo.vo.DevSuspectedBlacklistVo;
import com.eason.html.demo.vo.UserDeviceVo;
import com.eason.html.demo.vo.mapping.AddTypeFormatter;
import com.eason.html.easyview.core.PageHolder;
import com.eason.html.easyview.core.WidgetStyle;
import com.eason.html.easyview.core.annotations.CustomQueryAction;
import com.eason.html.easyview.core.basecontroller.BaseTableViewerController;
import com.eason.html.easyview.core.basecontroller.PageParams;

/**
 * 黑名单列表查询
 * 
 * @author dingluofeng
 *
 */
@Controller
@RequestMapping("/blacklist")
public class BlacklistController extends BaseTableViewerController<DevRegBlacklistVo, DevRegBlacklistVo> {

	@Resource
	private BlacklistService blacklistStorageService;

	public BlacklistController() {
		super("黑名单信息", WidgetStyle.ADD | WidgetStyle.DEL | WidgetStyle.REFLUSH | WidgetStyle.UPLOAD);
		addTableColMappingFormatter(new AddTypeFormatter());
		setOnlineResource(true);
	}

	@CustomQueryAction(path = "/suspectedlist", conditionForm = DevSuspectedBlacklistCo.class, title = "黑名单可疑行为")
	public List<DevSuspectedBlacklistVo> suspectedlist(DevRegBlacklistVo co, DevSuspectedBlacklistCo uc) {
		System.out.println("custom:" + co);
		System.out.println("custom:" + uc);
		return blacklistStorageService.pagedSuspectedBlacklist(co.getSubSerial(), uc.getRegTime(), 0, 20);
	}

	@CustomQueryAction(id = "userDevice", path = "/userDevice", title = "用户设备列表")
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
	protected PageHolder<DevRegBlacklistVo> list(PageParams pageParams, DevRegBlacklistVo co) {
		int curPage = pageParams.getCurPage();
		int limit = pageParams.getLimit();
		PageHolder<DevRegBlacklistVo> pageHolder = blacklistStorageService.pagedBlacklist(co.getSubSerial(),
				curPage - 1, limit);
		return pageHolder;
	}

	@Override
	protected int add(DevRegBlacklistVo vo) {
		blacklistStorageService.saveBlacklist(vo);
		return super.add(vo);
	}

	@Override
	protected int update(DevRegBlacklistVo vo) {
		blacklistStorageService.saveBlacklist(vo);
		return super.update(vo);
	}

	@Override
	protected int delete(String... subSerials) {
		blacklistStorageService.delete(subSerials);
		return super.delete(subSerials);
	}

}
