package top.ireed.listener;

import top.ireed.deal.DealLog;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ListenerRequest implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		DealLog.log("request对象创建了");
	}

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		DealLog.log("request对象销毁了");
	}
}

























