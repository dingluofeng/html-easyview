package com.eason.html.demo.vo;

import java.io.Serializable;

import com.eason.html.easyview.core.WidgetType;
import com.eason.html.easyview.core.annotations.EasyView;

@EasyView(name = "黑名单查询", cardView = false,checkbox = true)
public class DevRegBlacklistVo implements Serializable {
	
	@EasyView(name="设备短序列号")
	private String subSerial;

	@EasyView(name = "当次长序列号", readonly = true)
	private String curSerial;

	@EasyView(name = "上次长序列号", readonly = true)
	private String preSerial;

	private String curNatIp;

	private String preNatIp;

	@EasyView(name = "记录时间", type = WidgetType.Date)
	private String updateTime;

	@EasyView(name = "区域", validate = true)
	private String region;

	private String remark;
	
	@EasyView(name = "删除",type=WidgetType.Combo,dataProvider = DeletedDataProvider.class,formInput=false)
	private Boolean deleted;

	private static final long serialVersionUID = 1L;

	public String getSubSerial() {
		return subSerial;
	}

	public void setSubSerial(String subSerial) {
		this.subSerial = subSerial;
	}

	public String getCurSerial() {
		return curSerial;
	}

	public void setCurSerial(String curSerial) {
		this.curSerial = curSerial;
	}

	public String getPreSerial() {
		return preSerial;
	}

	public void setPreSerial(String preSerial) {
		this.preSerial = preSerial;
	}

	public String getCurNatIp() {
		return curNatIp;
	}

	public void setCurNatIp(String curNatIp) {
		this.curNatIp = curNatIp;
	}

	public String getPreNatIp() {
		return preNatIp;
	}

	public void setPreNatIp(String preNatIp) {
		this.preNatIp = preNatIp;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "DevRegBlacklistVo [subSerial=" + subSerial + ", curSerial=" + curSerial + ", preSerial=" + preSerial
				+ ", curNatIp=" + curNatIp + ", preNatIp=" + preNatIp + ", updateTime=" + updateTime + ", region="
				+ region + ", remark=" + remark + ", deleted=" + deleted + "]";
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}