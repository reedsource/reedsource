/*
 * FileName: TestServer
 * Author:   reedsource
 */
package top.ireed.netty01basics;

/**
 * 功能简述:
 * 〈netty简单socket服务器端简单通信实现〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
public class TestServer {
	public static void main(String[] args) {
		int port = 16807;
		new EchoServer(port).run();
	}
}
