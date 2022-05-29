package top.ireed.netty04download;

/**
 * 全局配置常量
 *
 * @author reedsource
 */
class NettyConfig {
	private NettyConfig() {
	}

	static String httpPath = "";
	static Boolean cn = false;

	/**
	 * 共享常量 启动时初始化
	 */
	public static String getHttpPath() {
		return httpPath;
	}

	public static void setHttpPath(String httpPath) {
		NettyConfig.httpPath = httpPath;
	}

	/**
	 * 是否共享路径中文 默认false
	 */
	public static Boolean getCn() {
		return cn;
	}

	public static void setCn(Boolean cn) {
		NettyConfig.cn = cn;
	}
}
