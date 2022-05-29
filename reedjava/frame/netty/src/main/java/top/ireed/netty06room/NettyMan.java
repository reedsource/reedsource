/*
 * FileName: NettyMan
 * Author:   reedsource
 */
package top.ireed.netty06room;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
public class NettyMan {
	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			int port = 16807;
			new WebSocketServer(port).run();
		}, "WebSocketNetty");
		//线程启动
		t1.start();
		Thread t2 = new Thread(() -> {
			int port = 7001;
			new SocketServer(port).run();
		}, "SocketNetty");
		//线程启动
		t2.start();
	}
}
