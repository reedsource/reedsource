/*
 * FileName: HttpFileServer
 * Author:   reedsource
 */
package top.ireed.netty04download;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import top.ireed.deal.DealLog;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
class HttpFileServer {

	/**
	 * 服务端口
	 */
	private final int port;

	/**
	 * 共享的文件路径
	 */
	private final String path;
	/**
	 * 是否共享中文目录
	 */
	private final Boolean cn;

	HttpFileServer(int port, String path, Boolean cn) {
		this.port = port;
		this.path = path;
		this.cn = cn;
	}

	void run() {

		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					// 有连接到达时会创建一个channel
					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new HttpServerCodec());
					pipeline.addLast(new HttpObjectAggregator(65536));
					pipeline.addLast(new ChunkedWriteHandler());
					pipeline.addLast(new FileServerHandler());
				}
			});
			Channel ch = b.bind(port).sync().channel();

			//初始化共享文件目录
			NettyConfig.setHttpPath(path);
			NettyConfig.setCn(cn);

			//获得本机IP
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress();
			DealLog.log("打开浏览器，输入： " + ("http") + "://" + ip + ":" + port + '/',"本地共享目录：" + path);
			ch.closeFuture().sync();
		} catch (InterruptedException e) {
			DealLog.log("目录共享异常",e);
			Thread.currentThread().interrupt();
		} catch (UnknownHostException e) {
			DealLog.log("目录共享异常",e);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}