/**
 * @ProjectName: 民用软件平台软件
 * @Copyright: 2012 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2013-5-18 下午5:15:31
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.eason.html.easyview.core.logging.impl;

import java.util.Set;

/**
 * <p>
 * </p>
 * 
 * @author DingLuofeng 2013-5-18 下午5:15:31
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2013-5-18
 * @modify by reason:{方法名}:{原因}
 */
public interface Log4jLogAdapterMBean {

	/**
	 * <p>
	 * 设置Log日志级别
	 * </p>
	 * 
	 * @author DingLuofeng 2013-5-18 下午5:23:07
	 * @param logName   记录日志类名 eg: com.hikvision.core.logging.LogAdapter
	 * @param levelName 日志级别名称 eg: TRACE、DEBUG、INFO、WARN、ERROR、FATAL;
	 */
	public void setLevel(String logName, String levelName);

	public Set<String> LoggerNames();
}
