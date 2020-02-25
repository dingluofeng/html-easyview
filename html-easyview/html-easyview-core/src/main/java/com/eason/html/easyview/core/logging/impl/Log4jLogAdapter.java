package com.eason.html.easyview.core.logging.impl;

import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.eason.html.easyview.core.logging.Log;
import com.eason.html.easyview.core.logging.LogAdapter;

/**
 * Apache log4j 适配器
 * <p/>
 * 存在<code>org.apache.log4j.Logger</code>就认为可用.
 * <p/>
 * 同样的,如果存在log4j-over-slf4j,则也会认为可用.
 * <p/>
 * 参考Issue : http://code.google.com/p/nutz/issues/detail?id=322
 * <p/>
 * <b>Log4J 1.2.11及之前的版本不支持Trace级别,默认转为使用Debug级别来Log</b>
 */
public class Log4jLogAdapter implements LogAdapter, Log4jLogAdapterMBean {

	private final static HashMap<String, Log4JLogger> ht = new HashMap<String, Log4JLogger>(1000);

	public boolean canWork() {
		try {
			Class.forName("org.apache.log4j.Logger", false, Thread.currentThread().getContextClassLoader());
			return true;
		} catch (Throwable e) {
		}
		return false;
	}

	public Log getLogger(String className) {
		synchronized (ht) {
			Log log = ht.get(className);
			if (log != null) {
				return log;
			}
			Log4JLogger logger = new Log4JLogger(className);
			ht.put(className, logger);
			return logger;
		}
	}

	class Log4JLogger extends AbstractLog {

		public final String SUPER_FQCN = AbstractLog.class.getName();

		public final String SELF_FQCN = Log4JLogger.class.getName();

		private final Logger logger;

		private boolean hasTrace;

		Log4JLogger(String className) {
			logger = Logger.getLogger(className);
			try {
				Level.class.getDeclaredField("TRACE");
				hasTrace = true;
			} catch (Throwable e) {
			}
			resetLevel();
		}

		private final void resetLevel() {
			isFatalEnabled = logger.isEnabledFor(Level.FATAL);
			isErrorEnabled = logger.isEnabledFor(Level.ERROR);
			isWarnEnabled = logger.isEnabledFor(Level.WARN);
			isInfoEnabled = logger.isEnabledFor(Level.INFO);
			isDebugEnabled = logger.isEnabledFor(Level.DEBUG);
			if (hasTrace) {
				isTraceEnabled = logger.isEnabledFor(Level.TRACE);
			}
		}

		public void debug(Object message, Throwable t) {
			if (isDebugEnabled)
				logger.log(SELF_FQCN, Level.DEBUG, message, t);
		}

		public void error(Object message, Throwable t) {
			if (isErrorEnabled)
				logger.log(SELF_FQCN, Level.ERROR, message, t);

		}

		public void fatal(Object message, Throwable t) {
			if (isFatalEnabled)
				logger.log(SELF_FQCN, Level.FATAL, message, t);
		}

		public void info(Object message, Throwable t) {
			if (isInfoEnabled)
				logger.log(SELF_FQCN, Level.INFO, message, t);
		}

		public void trace(Object message, Throwable t) {
			if (isTraceEnabled)
				logger.log(SELF_FQCN, Level.TRACE, message, t);
			else if ((!hasTrace) && isDebugEnabled)
				logger.log(SELF_FQCN, Level.DEBUG, message, t);
		}

		public void warn(Object message, Throwable t) {
			if (isWarnEnabled)
				logger.log(SELF_FQCN, Level.WARN, message, t);
		}

		protected void log(int level, Object message, Throwable tx) {
			switch (level) {
			case LEVEL_FATAL:
				logger.log(SUPER_FQCN, Level.FATAL, message, tx);
				break;
			case LEVEL_ERROR:
				logger.log(SUPER_FQCN, Level.ERROR, message, tx);
				break;
			case LEVEL_WARN:
				logger.log(SUPER_FQCN, Level.WARN, message, tx);
				break;
			case LEVEL_INFO:
				logger.log(SUPER_FQCN, Level.INFO, message, tx);
				break;
			case LEVEL_DEBUG:
				logger.log(SUPER_FQCN, Level.DEBUG, message, tx);
				break;
			case LEVEL_TRACE:
				if (hasTrace)
					logger.log(SUPER_FQCN, Level.TRACE, message, tx);
				else
					logger.log(SUPER_FQCN, Level.DEBUG, message, tx);
				break;
			default:
				break;
			}
		}

		public void setLevel(String levelName) {
			logger.setLevel(Level.toLevel(levelName));
			resetLevel();
		}
	}

	@Override
	public void setLevel(String logName, String levelName) {
		synchronized (ht) {
			Log4JLogger log4jLogger = ht.get(logName);
			if (log4jLogger != null) {
				System.out.println("setLevel:logName=" + logName + "-->levelName=" + levelName);
				log4jLogger.setLevel(levelName);
			} else {
				System.out.println("logName not exsit. setLevel:logName=" + logName + "-->levelName=" + levelName);
			}
		}
	}

	public Set<String> LoggerNames() {
		return ht.keySet();
	}

}
