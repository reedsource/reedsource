package top.ireed.netty06room;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * 全局配置
 *
 * @author reedsource
 */
class NettyConfig {
	private NettyConfig() {
	}

	static EventLoopGroup bossGroup = new NioEventLoopGroup(1);

	static EventLoopGroup workGroup = new NioEventLoopGroup();

	public static final String WEBSOCKET = "WEBSOCKET";
	public static final String UPGRADE = "UPGRADE";
	public static final int T200 = 200;
	private static String webSocketUrl = "";

	public static String getWebSocketUrl() {
		return webSocketUrl;
	}

	public static void setWebSocketUrl(String webSocketUrl) {
		NettyConfig.webSocketUrl = webSocketUrl;
	}
}
