/*
 * FileName: HttpSessionListener01
 * Author:   reedsource
 */
package top.ireed.listener.cases;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 功能简述:
 * 〈统计在线人数和在线用户〉
 * 使用Springboot提供了RegistrationBean的子类ServletListenerRegistrationBean 用于注册Listener，
 * 完成过滤器的设置首先我们创建一个HttpSessionListener类
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
public class HttpSessionListener01 implements HttpSessionListener {
	static int onlineNum = 0;

	@Override
	public synchronized void sessionCreated(HttpSessionEvent se) {
		onlineNum++;
	}

	@Override
	public synchronized void sessionDestroyed(HttpSessionEvent se) {
		onlineNum--;
	}

	public static int getOnlineNum() {
		return onlineNum;
	}
}
