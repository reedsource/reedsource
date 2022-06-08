package top.ireed.netty03html;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * 初始化连接时候的各个组件
 *
 * @author reedsource
 */
public class WebSocketChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel e) {
		// 请求消息解码器  是类HttpServerCodec是HttpRequestDecoder和HttpResponseEncoder的组合
		e.pipeline().addLast("http-codec", new HttpServerCodec());
		// //设置单次请求的文件大小上限
		e.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
		// 目的是支持异步大文件传输
		e.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
		// 调用自己封装的处理类
		e.pipeline().addLast("handler", new WebSocketHandler());
	}
}
