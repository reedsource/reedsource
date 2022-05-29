package top.ireed.netty01basics;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.ireed.deal.DealLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

final class EchoClient {

	private String host;
	private int port;

	EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * 客户端启动类
	 */
	void run() {
		EventLoopGroup group = new NioEventLoopGroup();
		try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
			Bootstrap b = new Bootstrap();
			b.group(group)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new EchoClientHandler());

			// 连接到服务器
			ChannelFuture f = b.connect(host, port).sync();

			Channel channel = f.channel();
			ByteBuffer writeBuffer = ByteBuffer.allocate(32);

			String userInput;
			while ((userInput = stdIn.readLine()) != null) {
				writeBuffer.put(userInput.getBytes());
				writeBuffer.flip();
				writeBuffer.rewind();

				// 转为ByteBuf
				ByteBuf buf = Unpooled.copiedBuffer(writeBuffer);

				// 写消息到管道
				channel.writeAndFlush(buf);

				// 清理缓冲区
				writeBuffer.clear();
			}
		} catch (IOException e) {
			DealLog.log("不能从主机中获取I/O，主机名为：", host, e);
		} catch (InterruptedException e) {
			DealLog.log("推送任务异常", e);
			Thread.currentThread().interrupt();
		} finally {
			// 优雅的关闭
			group.shutdownGracefully();
		}
	}

}
