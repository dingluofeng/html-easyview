package com.eason.html.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.eason.html.demo.Address;
import com.eason.html.demo.UserInfo;
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
import com.eason.html.easyview.core.basecontroller.utils.FormViewResult;
import com.eason.html.easyview.core.statictable.HtmlStaticTableBuilder;
import com.eason.html.easyview.core.statictable.model.StaticTableData;
import com.eason.html.easyview.core.statictable.model.StaticTableData.TableDataBuilder;
import com.eason.html.easyview.core.utils.HttpRequestHolder;
import com.google.common.collect.Lists;

/**
 * 黑名单列表查询
 * 
 * @author dingluofeng
 *
 */
@TableViewController(value = "/blacklist")
public class BlacklistController extends BaseTableViewerController<DevRegBlacklistVo, DevRegBlacklistVo> {

	@Resource
	private BlacklistService blacklistService;

	public BlacklistController() {
		super("黑名单信息", WidgetStyle.ALL);
		setOnlineResource(false);
	}

	@TableItemAction(path = "/send/limit", title = "限制",msgType=IMessageForm.VIEW_FORM, hideScript="row.subSerial=='123456790'")
    public String send(DevRegBlacklistVo vo) {
		System.out.println(vo);
		return FormViewResult.formView(Lists.newArrayList(new UserInfo("dingluofeng", 18, Lists.newArrayList(new Address("China","浙江","杭州")))));
	}
	
	@ToolItemAction(path = "/send/limit2", title = "限制2",msgType = IMessageForm.INFO_FORM)
    public String send2(DevRegBlacklistVo vo) {
		System.out.println(vo);
		return "OK";
	}
	@ToolItemAction(path = "/send/limit3", title = "限制3",msgType = IMessageForm.INFO_FORM)
	public String send3(DevRegBlacklistVo vo) {
		System.out.println(vo);
		StringBuilder sb=new StringBuilder();
		sb.append("限制1").append(System.lineSeparator());
		sb.append("限制2").append(System.lineSeparator());
		sb.append("限制3").append(System.lineSeparator());
		sb.append("限制4").append(System.lineSeparator());
		sb.append("限制5").append(System.lineSeparator());
		sb.append("限制6").append(System.lineSeparator());
		sb.append("\t\t\t\t\t限制7").append(System.lineSeparator());
		sb.append("限制8").append(System.lineSeparator());
		sb.append("限制9").append(System.lineSeparator());
		sb.append("\t\t\t\t\t限制10").append(System.lineSeparator());
		sb.append("\t\t\t\t\t限制11").append(System.lineSeparator());
//		StaticTableData
		HtmlStaticTableBuilder builder = new HtmlStaticTableBuilder();
		builder.headBuider();
		String[] columns=new String[] {"执行结果"};
		List<Object[]> rows=new ArrayList<>();
		String[] split = sb.toString().split(System.lineSeparator());
		for (String string : split) {
			rows.add(new Object[] {string});
		}
		StaticTableData tableData = TableDataBuilder.newBuilder().columnTitles(columns).rows(rows).build();
		builder.bodyBuilder().newTableBuilder(tableData);
		return builder.build();
	}

	@CustomTableViewAction(path = "/suspectedlist", conditionForm = DevSuspectedBlacklistCo.class, title = "黑名单可疑行为")
	public PageHolder<DevSuspectedBlacklistVo> suspectedlist(PageParams pageParams,DevRegBlacklistVo co, DevSuspectedBlacklistCo uc) {
		System.out.println("custom，pageParams:" + pageParams);
		System.out.println("custom，DevRegBlacklistVo:" + co);
		System.out.println("custom，DevSuspectedBlacklistCo:" + uc);
		List<DevSuspectedBlacklistVo> pagedSuspectedBlacklist = blacklistService.pagedSuspectedBlacklist(co.getSubSerial(), uc.getRegTime(), pageParams.getCurPage()-1, pageParams.getLimit());
		return PageHolder.pageList(50, pagedSuspectedBlacklist);
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
        System.out.println(co);
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
