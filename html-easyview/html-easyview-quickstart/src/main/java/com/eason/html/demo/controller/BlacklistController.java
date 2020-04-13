package com.eason.html.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.eason.html.demo.co.DevSuspectedBlacklistCo;
import com.eason.html.demo.service.BlacklistService;
import com.eason.html.demo.vo.DevRegBlacklistVo;
import com.eason.html.demo.vo.DevSuspectedBlacklistVo;
import com.eason.html.demo.vo.UserDeviceVo;
import com.eason.html.demo.vo.mapping.ValueFormatter;
import com.eason.html.easyview.core.IMessageForm;
import com.eason.html.easyview.core.PageHolder;
import com.eason.html.easyview.core.WidgetStyle;
import com.eason.html.easyview.core.annotations.CustomQueryAction;
import com.eason.html.easyview.core.annotations.CustomTableViewAction;
import com.eason.html.easyview.core.annotations.TableColumn;
import com.eason.html.easyview.core.annotations.TableColumns;
import com.eason.html.easyview.core.annotations.TableItemAction;
import com.eason.html.easyview.core.annotations.TableViewController;
import com.eason.html.easyview.core.annotations.ToolItemAction;
import com.eason.html.easyview.core.basecontroller.BaseTableViewerController;
import com.eason.html.easyview.core.basecontroller.PageParams;
import com.eason.html.easyview.core.basecontroller.ResponseResult;
import com.eason.html.easyview.core.utils.HttpRequestHolder;

/**
 * 黑名单列表查询
 * 
 * @author dingluofeng
 *
 */
@TableViewController(value = "/blacklist", showDefaultItemOpt = true)
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

	@CustomTableViewAction(path = "/suspectedlist", conditionForm = DevSuspectedBlacklistCo.class, title = "黑名单可疑行为")
	public List<DevSuspectedBlacklistVo> suspectedlist(DevRegBlacklistVo co, DevSuspectedBlacklistCo uc) {
		System.out.println("custom:" + co);
		System.out.println("custom:" + uc);
		return blacklistService.pagedSuspectedBlacklist(co.getSubSerial(), uc.getRegTime(), 0, 20);
	}

	@CustomTableViewAction(path = "/mapped", title = "map测试")
    @TableColumns({
        @TableColumn(field = "mappedkey1", title = "key1显示值", mappingFormatter = ValueFormatter.class),
        @TableColumn(field = "mappedkey2", title = "key2显示值", mapping = "{'value2':'key2显示值'}"),
        @TableColumn(field = "mappedkey3", title = "key3显示值", mapping = "{1:'key3显示值'}")})
    public Map<Object, Object> mapped(DevRegBlacklistVo co, DevSuspectedBlacklistCo uc) {
        System.out.println("custom:" + co);
        System.out.println("custom:" + uc);
        Map<Object, Object> mapped = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            mapped.put("mappedkey" + i, "value" + i);
        }
        mapped.put("mappedkey3", 1);
        return mapped;
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
        HttpServletRequest request = HttpRequestHolder.getRequest();
        System.out.println(request);
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
