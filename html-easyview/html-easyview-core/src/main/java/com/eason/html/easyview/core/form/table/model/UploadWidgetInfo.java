/**
 * @ProjectName: 民用软件平台软件
 * @Copyright: 2012 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2020年2月26日 上午11:54:22
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.eason.html.easyview.core.form.table.model;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年2月26日 上午11:54:22
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年2月26日
 * @modify by reason:{方法名}:{原因}
 */
public class UploadWidgetInfo {

	private String name;
	// 上传文件上传URL
	String uploadUrl;

	// 上传文件控件ID
	String uploadId;

	// 指定允许上传的文件类型,普通文件:file,视频:video,音频:audio
	String acceptType = "file";

	// 允许上传文件类型:txt|xlsx
	String fileExts = "txt|xlsx";

	// 限制文件大小，单位 KB
	int limitSize = 10240;

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}

	public String getFileExts() {
		return fileExts;
	}

	public void setFileExts(String fileExts) {
		this.fileExts = fileExts;
	}

	public int getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(int limitSize) {
		this.limitSize = limitSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadWidgetInfo [name=");
		builder.append(name);
		builder.append(", uploadUrl=");
		builder.append(uploadUrl);
		builder.append(", uploadId=");
		builder.append(uploadId);
		builder.append(", acceptType=");
		builder.append(acceptType);
		builder.append(", fileExts=");
		builder.append(fileExts);
		builder.append(", limitSize=");
		builder.append(limitSize);
		builder.append("]");
		return builder.toString();
	}
}
