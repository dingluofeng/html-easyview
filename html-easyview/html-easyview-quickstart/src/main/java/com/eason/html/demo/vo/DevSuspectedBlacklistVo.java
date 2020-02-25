package com.eason.html.demo.vo;

import java.io.Serializable;
import java.util.Date;

import com.eason.html.easyview.core.annotations.EasyView;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DevSuspectedBlacklistVo implements Serializable {

	private Long id;

	private String subSerial;

	private String fullSerial;

	private String dasId;

	private String version;

	private String devType;

	private String devTypeDisplay;

	private String devMac;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date regTime;

	private String protocolVersion;

	@EasyView(name = "", columnHidden = true)
	private String firmwareCode;

	private String devWanIp;

	private String devLocation;

	private String region;

	private String remark;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubSerial() {
		return subSerial;
	}

	public void setSubSerial(String subSerial) {
		this.subSerial = subSerial;
	}

	public String getFullSerial() {
		return fullSerial;
	}

	public void setFullSerial(String fullSerial) {
		this.fullSerial = fullSerial;
	}

	public String getDasId() {
		return dasId;
	}

	public void setDasId(String dasId) {
		this.dasId = dasId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevTypeDisplay() {
		return devTypeDisplay;
	}

	public void setDevTypeDisplay(String devTypeDisplay) {
		this.devTypeDisplay = devTypeDisplay;
	}

	public String getDevMac() {
		return devMac;
	}

	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public String getFirmwareCode() {
		return firmwareCode;
	}

	public void setFirmwareCode(String firmwareCode) {
		this.firmwareCode = firmwareCode;
	}

	public String getDevWanIp() {
		return devWanIp;
	}

	public void setDevWanIp(String devWanIp) {
		this.devWanIp = devWanIp;
	}

	public String getDevLocation() {
		return devLocation;
	}

	public void setDevLocation(String devLocation) {
		this.devLocation = devLocation;
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
		return "DevSuspectedBlacklistVo [id=" + id + ", subSerial=" + subSerial + ", fullSerial=" + fullSerial
				+ ", dasId=" + dasId + ", version=" + version + ", devType=" + devType + ", devTypeDisplay="
				+ devTypeDisplay + ", devMac=" + devMac + ", regTime=" + regTime + ", protocolVersion="
				+ protocolVersion + ", firmwareCode=" + firmwareCode + ", devWanIp=" + devWanIp + ", devLocation="
				+ devLocation + ", region=" + region + ", remark=" + remark + "]";
	}

}