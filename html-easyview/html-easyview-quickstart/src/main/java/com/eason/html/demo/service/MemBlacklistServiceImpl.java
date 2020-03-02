package com.eason.html.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eason.html.demo.vo.DevRegBlacklistVo;
import com.eason.html.demo.vo.DevSuspectedBlacklistVo;
import com.eason.html.easyview.core.PageHolder;
import com.eason.html.easyview.core.logging.Log;
import com.eason.html.easyview.core.logging.LogFactory;
import com.eason.html.easyview.core.utils.CollectionUtils;
import com.eason.html.easyview.core.utils.StringUtils;

@Service
public class MemBlacklistServiceImpl implements BlacklistService {

	private Log logger = LogFactory.getLog(MemBlacklistServiceImpl.class);

	private List<DevRegBlacklistVo> blacklistDos = new ArrayList<>();

	private List<DevSuspectedBlacklistVo> suspectedBlacklistDos = new ArrayList<>();

	private Random random = new Random();

	public MemBlacklistServiceImpl() {
		super();
		init();
	}

	@SuppressWarnings("deprecation")
	private final void init() {
		String[] ips = new String[] { "101.1.101.12.1", "102.21.4.110", "201.2.31.2", "65.12.34.12", "55.1.2.1.22" };
		for (int i = 0; i < 50; i++) {
			DevRegBlacklistVo blacklistDo = new DevRegBlacklistVo();
			int subSerial = 123456789;
			blacklistDo.setSubSerial(String.valueOf(subSerial + i));
			String curNatIp = ips[random.nextInt(5)];
			blacklistDo.setCurNatIp(curNatIp);
			String curSerial = "CS-C2C-11WFS-" + String.valueOf(subSerial + i);
			blacklistDo.setCurSerial(curSerial);
			blacklistDo.setPreNatIp(ips[random.nextInt(5)]);
			blacklistDo.setPreSerial("CS-C2C-11WFS-" + String.valueOf(subSerial + i - 1));
			blacklistDo.setRegion("region" + i);
			blacklistDo.setRemark("remark" + i);
			blacklistDo.setUpdateTime(new Date().toLocaleString());
			blacklistDos.add(blacklistDo);
			for (int j = 0; j < 35; j++) {
				DevSuspectedBlacklistVo suspectedBlacklistDo = new DevSuspectedBlacklistVo();
				suspectedBlacklistDo.setDasId("dasId" + j);
				suspectedBlacklistDo.setDevLocation(curNatIp);
				suspectedBlacklistDo.setDevMac("devMac" + j);
				suspectedBlacklistDo.setDevType("devType" + j);
				suspectedBlacklistDo.setDevTypeDisplay("devTypeDisplay" + j);
				suspectedBlacklistDo.setDevWanIp(curNatIp);
				suspectedBlacklistDo.setFirmwareCode("firmwareCode" + j);
				suspectedBlacklistDo.setFullSerial(curSerial);
				suspectedBlacklistDo.setId(random.nextLong());
				suspectedBlacklistDo.setProtocolVersion("protocolVersion" + j);
				suspectedBlacklistDo.setRegion("region" + j);
				suspectedBlacklistDo.setRegTime(new Date());
				suspectedBlacklistDo.setRemark("remark" + j);
				suspectedBlacklistDo.setSubSerial(String.valueOf(subSerial + i));
				suspectedBlacklistDo.setVersion("v1.20." + random.nextInt(3) + " build 200201");
				suspectedBlacklistDos.add(suspectedBlacklistDo);
			}
		}
		logger.info("初始化黑名单内存库完成");
	}

	@Override
	public void saveBlacklist(DevRegBlacklistVo devRegBlacklistDo) {
		blacklistDos.add(devRegBlacklistDo);
	}

	@Override
	public PageHolder<DevRegBlacklistVo> pagedBlacklist(String subSerial, int page, int pageSize) {
		List<DevRegBlacklistVo> collect = blacklistDos.stream()
				.filter((a) -> StringUtils.equals(subSerial, a.getSubSerial())).collect(Collectors.toList());
		List<DevRegBlacklistVo> listPageData = getListPageData(collect, page * pageSize, pageSize);
		return new PageHolder<DevRegBlacklistVo>(collect.size(), listPageData);
	}

	@Override
	public void saveSuspectedBlacklist(DevSuspectedBlacklistVo suspectedBlacklist) {
		suspectedBlacklistDos.add(suspectedBlacklist);
	}

	@Override
	public DevSuspectedBlacklistVo getSuspectedBlacklist(String fullSerial) {
		for (DevSuspectedBlacklistVo suspectedBlacklistDo : suspectedBlacklistDos) {
			if (StringUtils.equals(fullSerial, suspectedBlacklistDo.getFullSerial())) {
				return suspectedBlacklistDo;
			}
		}
		return null;
	}

	@Override
	public List<DevSuspectedBlacklistVo> pagedSuspectedBlacklist(String subSerial, String beginTime, int page,
			int pageSize) {
		List<DevSuspectedBlacklistVo> collect = suspectedBlacklistDos.stream()
				.filter((a) -> StringUtils.equals(subSerial, a.getSubSerial())).collect(Collectors.toList());
		List<DevSuspectedBlacklistVo> listPageData = getListPageData(collect, page * pageSize, pageSize);
		return listPageData;
	}

	@Override
	public void delete(String... subSerials) {
		Iterator<DevRegBlacklistVo> iterator = blacklistDos.iterator();
		while (iterator.hasNext()) {
			DevRegBlacklistVo devRegBlacklistDo = (DevRegBlacklistVo) iterator.next();
			for (String string : subSerials) {
				if (StringUtils.equals(string, devRegBlacklistDo.getSubSerial())) {
					iterator.remove();
				}
			}

		}
	}

	public static <T> List<T> getListPageData(List<T> allList, int begin, int pageSize) {
		if (begin < 0 || pageSize < 0) {
			return allList;
		}
		List<T> ret = new ArrayList<>();
		if (CollectionUtils.isEmpty(allList)) {
			return ret;
		}
		// 设置内存分页数据
		int toPageMaxIndex = begin + pageSize;
		int listSize = allList.size();
		int end = toPageMaxIndex < listSize ? toPageMaxIndex : listSize;

		if (begin > end) {
			return ret;
		}
		List<T> pageList = allList.subList(begin, end);
		if (CollectionUtils.isEmpty(pageList)) {
			return ret;
		}
		ret.addAll(pageList);
		return ret;
	}

}
