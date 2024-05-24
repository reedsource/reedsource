package top.ireed.netty03html;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 全局配置
 *
 * @author reedsource
 */
class NettyConfig {
    public static final String WEBSOCKET = "WEBSOCKET";
    public static final String UPGRADE = "UPGRADE";
    public static final int T200 = 200;
    /**
     * 存储每一个客户端接入进来时的channel对象
     */
    static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static String webSocketUrl = "";
    private NettyConfig() {
    }

    public static String getWebSocketUrl() {
        return webSocketUrl;
    }

    public static void setWebSocketUrl(String webSocketUrl) {
        NettyConfig.webSocketUrl = webSocketUrl;
    }
}
