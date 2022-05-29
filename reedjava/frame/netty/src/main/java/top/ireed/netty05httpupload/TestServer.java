/*
 * FileName: TestServer
 * Author:   reedsource
 */
package top.ireed.netty05httpupload;


/**
 * 功能简述:
 * 〈启动 netty文件上传服务实现〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
public class TestServer {
	public static void main(String[] args) {
		new HttpUploadServer().run();
	}
}
