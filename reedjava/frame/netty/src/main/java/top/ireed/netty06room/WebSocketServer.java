/*
 * FileName: WebSocketServer
 * Author:   reedsource
 */
package top.ireed.netty06room;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import top.ireed.deal.DealLog;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static top.ireed.netty06room.NettyConfig.bossGroup;
import static top.ireed.netty06room.NettyConfig.workGroup;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
class WebSocketServer {

	private final int port;

	WebSocketServer(int port) {
		this.port = port;
	}

	/**
	 * 启用一个端口
	 */
	void run() {

		try {
			InetAddress addr = InetAddress.getLocalHost();
			//获得本机IP
			String ip = addr.getHostAddress();
			//更新htmlServer路径
			NettyConfig.setWebSocketUrl("ws//" + ip + ":" + port + "/echo");
		} catch (UnknownHostException e) {
			DealLog.log("异常", e);
		}

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup);
			b.channel(NioServerSocketChannel.class);
			// 调用自己封装的处理类
			b.childHandler(new WebSocketChannelHandler());
			DealLog.log("服务端开启等待客户端连接...  webUrl ", NettyConfig.getWebSocketUrl());
			Channel ch = b.bind(port).sync().channel();
			ch.closeFuture().sync();

		} catch (Exception e) {
			DealLog.log("异常", e);
		} finally {
			// 优雅地退出程序
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
}
