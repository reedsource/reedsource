package top.ireed.listener;

import top.ireed.deal.DealLog;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author reedsource
 * 创建侦听器的步骤
 * 1.创建一个普通的java类
 * 监听什么,就实现什么接口
 * 比如我们现在要监听上下文对象的创建和销毁
 * 就必须要实现javax.servlet.ServletContextListener接口
 * 2.重写监听方法
 * 3.对于event变量的说明
 * 通过event能够取得监听的对象
 * 4.在web.xml中配置侦听器
 * 配置侦听器的位置,一般是在servlet的上面,filter的下面
 */
@WebListener
public class ListenerContext implements ServletContextListener {

	/**
	 * 用来监听上下文对象创建的方法
	 * 当上下文对象创建,则马上执行该方法
	 *
	 * @param event
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		DealLog.log("上下文context对象创建了");
	}

	/**
	 * 用来监听上下文对象销毁的方法
	 * 当上下文对象销毁,则马上执行该方法
	 *
	 * @param event
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		DealLog.log("上下文context对象销毁了");
	}
}

























