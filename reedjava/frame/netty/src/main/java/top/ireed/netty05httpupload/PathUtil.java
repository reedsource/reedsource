package top.ireed.netty05httpupload;

import top.ireed.deal.DealLog;

import java.io.File;

import static top.ireed.netty05httpupload.NettyConfig.LINUX_PATH;
import static top.ireed.netty05httpupload.NettyConfig.WIN_PATH;

/**
 * @author reedsource
 */
class PathUtil {
	private PathUtil() {
	}

	static String getFileBasePath() {
		//获取当前系统版本
		String os = System.getProperty("os.name");
		//根据系统版本获取文件保存目录
		String basePath = os.toLowerCase().startsWith("win") ? WIN_PATH : LINUX_PATH.replace("/", File.separator);
		//判断目录是否存在 创建目录
		File f = new File(basePath);
		//目录是否存在
		if (!f.exists()) {
			//创建目录
			boolean ms = f.mkdirs();
			DealLog.log("目录" + basePath + "创建成功" + ms);
		}
		return basePath;
	}

}