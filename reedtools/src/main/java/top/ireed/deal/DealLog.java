/*
 * FileName: DealLog
 * Author:   reedsource
 */
package top.ireed.deal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.ireed.general.TopException;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/9/14 9:06
 * reedsource@189.cn
 */
public class DealLog {
	private DealLog() {
	}

	/**
	 * 替代打印到控制台的日志,和打印到控制台效果一致
	 */
	private static final Logger logger = LogManager.getLogger("SYSTEM_OUT");

	/**
	 * 换行
	 */
	public static void log() {
		logger.info("\r\n");
	}

	/**
	 * @param msg 字符串
	 */
	public static void log(String msg) {
		logger.info(msg);
	}

	/**
	 * @param m   字符串
	 * @param msg 字符串
	 */
	public static void log(String m, String msg) {
		String f = m + " --> " + msg;
		logger.info(f);
	}

	/**
	 * @param m object对象
	 * @param o object对象
	 */
	public static void log(Object m, Object o) {
		log(String.valueOf(m), o);
	}

	/**
	 * @param m 字符串
	 * @param o object对象
	 */
	public static void log(String m, Object o) {
		String f = m + " --> " + o.toString();
		logger.info(f);
	}

	/**
	 * @param m object对象
	 * @param o object对象  组
	 */
	public static void log(Object m, Object... o) {
		log(String.valueOf(m), o);
	}


	/**
	 * @param m 字符串
	 * @param o object对象  组
	 */
	public static void log(String m, Object... o) {
		StringBuilder a = new StringBuilder();
		a.append(m);
		for (Object o1 : o) {
			a.append("\r\n --> ");
			a.append(o1.toString());
		}
		String log = a.toString();
		logger.info(log);
	}


	/**
	 * @param o 对象
	 */
	public static void log(Object o) {
		logger.info(o);
	}

	/**
	 * 抛出异常字符串
	 *
	 * @param msg 异常字符串
	 */
	public static void logException(String msg, Object o) {
		try {
			throw new TopException(msg + o);
		} catch (TopException e) {
			logger.info(e);
		}
	}

	/**
	 * 抛出异常字符串
	 *
	 * @param o 异常
	 */
	public static void logException(Object o) {
		logException(null, o);
	}
}
