/*
 * FileName: TestServer
 * Author:   reedsource
 */
package top.ireed.netty04download;


/**
 * 功能简述:
 * 〈启动 netty下载服务器实现〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
public class TestServer {

	private static final String D_TOP = "D:\\top";

	public static void main(String[] args) {
		int port = 16807;
		//文件共享目录, 以文件夹结尾
		new HttpFileServer(port, D_TOP, true).run();
	}
}
