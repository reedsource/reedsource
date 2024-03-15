package top.ireed.listener;

import top.ireed.deal.DealLog;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class ListenerSession implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		DealLog.log("session对象创建了");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		DealLog.log("session对象销毁了");
	}
}

























