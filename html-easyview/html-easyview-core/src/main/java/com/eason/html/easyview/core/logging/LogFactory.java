package com.eason.html.easyview.core.logging;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * 获取 Log 的静态工厂方法
 */
public final class LogFactory {

	private static LogAdapter adapter;

	static {
		init();
	}

	/**
	 * Get a Log by Class
	 * 
	 * @param clazz your class
	 * @return Log
	 * @throws NullPointerException when clazz is null
	 */
	public static Log getLog(Class<?> clazz) {
		return getLog(clazz.getName());
	}

	/**
	 * Get a Log by name
	 * 
	 * @param className the name of Log
	 * @return Log
	 * @throws NullPointerException when className is null, maybe it will case NPE
	 */
	public static Log getLog(String className) {
		return adapter.getLogger(className);
	}

	/**
	 * 返回以调用者的类命名的Log,是获取Log对象最简单的方法!
	 */
	public static Log get() {
		return getLog(Thread.currentThread().getStackTrace()[2].getClassName());
	}

	/**
	 * 初始化Log,检查全部Log的可用性,选择可用的Log适配器
	 * <p/>
	 * <b>加载本类时,该方法已经在静态构造函数中调用,用户无需主动调用.</b>
	 * <p/>
	 * <b>除非迫不得已,请不要调用本方法<b/>
	 * <p/>
	 */
	private static void init() {
		try {
			String packageName = LogFactory.class.getPackage().getName() + ".impl.";
			adapter = new SimplePluginManager<LogAdapter>(packageName + "Log4j2LogAdapter",
					packageName + "Log4jLogAdapter", packageName + "SystemLogAdapter").get();
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			ObjectName logName = new ObjectName("com.hikvision.core.log:name=LogConfigration" + adapter.hashCode());
			server.registerMBean(adapter, logName);
		} catch (Throwable e) {
			// 这是不应该发生的,SystemLogAdapter应该永远返回true
			// 唯一的可能性是所请求的com.commons.core.log.impl.SystemLogAdapter根本不存在
			// 例如改了package
			e.printStackTrace();
		}
	}
}
