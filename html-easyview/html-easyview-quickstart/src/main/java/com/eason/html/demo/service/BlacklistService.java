package com.eason.html.demo.service;

import java.util.List;

import com.eason.html.demo.vo.DevRegBlacklistVo;
import com.eason.html.demo.vo.DevSuspectedBlacklistVo;
import com.eason.html.easyview.core.PageHolder;

public interface BlacklistService {

	void saveBlacklist(DevRegBlacklistVo devRegBlacklistDo);

	PageHolder<DevRegBlacklistVo> pagedBlacklist(String subSerial, int page, int pageSize);

	void saveSuspectedBlacklist(DevSuspectedBlacklistVo suspectedBlacklist);

	DevSuspectedBlacklistVo getSuspectedBlacklist(String fullSerial);

	List<DevSuspectedBlacklistVo> pagedSuspectedBlacklist(String subSerial, String beginTime, int page, int pageSize);

	void delete(String... subSerials);

}