package top.ireed.netty05httpupload;

/**
 * 全局配置常量
 *
 * @author reedsource
 */
class NettyConfig {
	private NettyConfig() {
	}

	/**
	 * 上传后续请求路径
	 */
	static final String UPLOAD_URL = "/up";

	/**
	 * 文件上传请求url
	 */
	static final String FROM_FILE_URL = "/post_multipart";

	public static final String STRING = "D:/上传";
	/**
	 * win文件保存路径
	 */
	static final String WIN_PATH = STRING;

	/**
	 * linux文件保存路径
	 */
	static final String LINUX_PATH ="";

	private static String nettyIp ="";

	/**
	 * 共享常量 启动时初始化
	 */
	public static String getNettyIp() {
		return nettyIp;
	}

	public static void setNettyIp(String nettyIp) {
		NettyConfig.nettyIp = nettyIp;
	}
}
