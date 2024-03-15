/*
 * FileName: InitService
 * Author:   reedsource
 */
package top.ireed.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈初始化日志打印测试〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Component("InitService")
public class InitService {

	/**
	 * 创建日志记录对象  打印时的名称
	 */
	private static Logger logger = LogManager.getLogger("top_xx");

	/**
	 * 方法1.1创建类
	 */
	public void show(String msg) {
		logger.warn("warn级别日志信息 : warn日志");
		DealLog.log("这是InitService中的show" + msg);
		logger.error("error级别日志信息 : error日志");
	}
}
