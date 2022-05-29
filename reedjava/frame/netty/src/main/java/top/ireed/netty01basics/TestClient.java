/*
 * FileName: TestClient
 * Author:   reedsource
 */
package top.ireed.netty01basics;

/**
 * 功能简述:
 * 〈netty简单socket客户端简单通信实现〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
public class TestClient {

	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 16807;
		new EchoClient(host, port).run();
	}
}

