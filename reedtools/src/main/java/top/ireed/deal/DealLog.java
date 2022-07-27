/*
 * FileName: DealLog
 * Author:   reedsource
 */
package top.ireed.deal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.ireed.entity.RE;
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
	 * 开发重点标记
	 * 使用本方法时,表示本处是一个重点关注点
	 * 需要在调用位置同步添加javadoc同时添加日志
	 */
	public static void GO(RE... o) {
		log((Object) o);
	}

	/**
	 * Object打印
	 * <p>
	 * 无元素 换行
	 * 单元素 直接打印
	 * 多元素 空格间隔组合打印
	 *
	 * @param o object对象  组
	 */
	public static void log(Object... o) {
		logger.info(DealString.objectToString(o));
	}

	/**
	 * 指向
	 * a --> b
	 *
	 * @param m object对象
	 * @param n object对象
	 */
	public static void logTo(Object m, Object n) {
		logger.info(m.toString() + " --> " + n.toString());
	}

	/**
	 * 指向 多个
	 * a
	 * --> b
	 * --> c
	 * --> d
	 *
	 * @param m 字符串
	 * @param o object对象  组
	 */
	public static void logToAll(Object m, Object... o) {
		StringBuilder a = new StringBuilder();
		a.append(m.toString());
		for (Object o1 : o) {
			a.append("\r\n  --> ").append(o1.toString());
		}
		logger.info(a.toString());
	}

	/**
	 * 抛出异常字符串
	 *
	 * @param o 异常信息组 打印结果多个异常将空格划分
	 */
	public static void logException(Object... o) {
		try {
			throw new TopException(o);
		} catch (TopException e) {
			logger.info(e);
		}
	}
}
