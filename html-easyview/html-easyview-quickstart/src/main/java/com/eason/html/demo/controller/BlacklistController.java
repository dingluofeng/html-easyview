package com.eason.html.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;

import com.eason.html.demo.co.DevSuspectedBlacklistCo;
import com.eason.html.demo.service.BlacklistService;
import com.eason.html.demo.vo.DevRegBlacklistVo;
import com.eason.html.demo.vo.DevSuspectedBlacklistVo;
import com.eason.html.demo.vo.UserDeviceVo;
import com.eason.html.easyview.core.IMessageForm;
import com.eason.html.easyview.core.PageHolder;
import com.eason.html.easyview.core.WidgetStyle;
import com.eason.html.easyview.core.annotations.CustomQueryAction;
import com.eason.html.easyview.core.annotations.TableItemAction;
import com.eason.html.easyview.core.annotations.TableViewController;
import com.eason.html.easyview.core.annotations.ToolItemAction;
import com.eason.html.easyview.core.basecontroller.BaseTableViewerController;
import com.eason.html.easyview.core.basecontroller.PageParams;
import com.eason.html.easyview.core.basecontroller.ResponseResult;

/**
 * 黑名单列表查询
 * 
 * @author dingluofeng
 *
 */
@TableViewController(value = "/blacklist", showDefaultItemOpt = false)
public class BlacklistController extends BaseTableViewerController<DevRegBlacklistVo, DevRegBlacklistVo> {

	@Resource
	private BlacklistService blacklistService;

	public BlacklistController() {
		super("黑名单信息", WidgetStyle.ALL);
		setOnlineResource(false);
	}

	@TableItemAction(path = "/send/limit", title = "限制")
    public String send(DevRegBlacklistVo vo) {
		System.out.println(vo);
		return "Ok";
	}
	
	@ToolItemAction(path = "/send/limit2", title = "限制2",msgType = IMessageForm.INFO_FORM)
    public String send2(DevRegBlacklistVo vo) {
		System.out.println(vo);
		return "OK";
	}
	@ToolItemAction(path = "/send/limit3", title = "限制3",msgType = IMessageForm.PAGE_FORM)
	public String send3(DevRegBlacklistVo vo) {
		System.out.println(vo);
		return "<html><h3>通讯路程yyyyyyjjjjjjjj哈哈哈哈哈哈哈哈哈哈或或或或或或或或或或不不</h3></html>";
	}

	@CustomQueryAction(path = "/suspectedlist", conditionForm = DevSuspectedBlacklistCo.class, title = "黑名单可疑行为")
	public List<DevSuspectedBlacklistVo> suspectedlist(DevRegBlacklistVo co, DevSuspectedBlacklistCo uc) {
		System.out.println("custom:" + co);
		System.out.println("custom:" + uc);
		return blacklistService.pagedSuspectedBlacklist(co.getSubSerial(), uc.getRegTime(), 0, 20);
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
		PageHolder<DevRegBlacklistVo> pageHolder = blacklistService.pagedBlacklist(co.getSubSerial(),
				curPage - 1, limit);
		return pageHolder;
	}

	@Override
    protected ResponseResult fileImport(MultipartFile multipartFile) {
        System.out.println("received file:" + multipartFile.getOriginalFilename());
        return super.fileImport(multipartFile);
    }

    @Override
    protected ResponseResult add(DevRegBlacklistVo vo) {
    	blacklistService.saveBlacklist(vo);
		return super.add(vo);
	}

	@Override
    protected ResponseResult update(DevRegBlacklistVo vo) {
		blacklistService.saveBlacklist(vo);
		return super.update(vo);
	}

	@Override
    protected ResponseResult delete(String... subSerials) {
		blacklistService.delete(subSerials);
		return super.delete(subSerials);
	}

}
