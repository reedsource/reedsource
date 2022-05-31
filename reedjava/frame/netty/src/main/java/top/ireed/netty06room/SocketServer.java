package top.ireed.netty06room;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import top.ireed.deal.DealLog;

import static top.ireed.netty06room.NettyConfig.bossGroup;
import static top.ireed.netty06room.NettyConfig.workGroup;


/**
 * @author reedsource
 */
class SocketServer {

	private final int port;

	SocketServer(int port) {
		this.port = port;
	}

	/**
	 * 启用一个端口
	 */
	void run() {
		try {
			//建立服务端
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup)
					//指定接收连接
					.channel(NioServerSocketChannel.class)
					//ChannelInboundHandler的实现类
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) {
							// 调用处理类
							ch.pipeline().addLast(new SocketHandler());
						}
					})
					//已完成三次握手的请求的队列
					.option(ChannelOption.SO_BACKLOG, 128)
					//是否启用心跳保活机制。两个小时
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture f = b.bind(port).sync();
			DealLog.log("DiscardServer已启动，端口：" , port);
			//sync()会同步等待连接操作结果，用户线程将在此wait()，直到连接操作完成之后，线程被notify(),用户代码继续执行
			//closeFuture()当Channel关闭时返回一个ChannelFuture,用于链路检测
			//作用是产生一个wait()事件，保证main线程存活，否则main线程直接结束
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			DealLog.log("异常",e);
			Thread.currentThread().interrupt();
		} finally {
			//资源优雅释放
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
}