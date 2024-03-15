/*
 * FileName: InitService
 * Author:   reedsource
 */
package top.ireed.init;

import org.springframework.stereotype.Component;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈初始执行程序〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 * <p>
 * 方法1.2 将类添加到spring的上下文中去
 */
@Component("initService")
public class InitService {
	/**
	 * 方法1.1创建类
	 */
	public void show(String msg) {
		DealLog.log("这是InitService中的show" + msg);
	}
}
