package top.ireed.netty06room;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import top.ireed.deal.DealLog;

import static top.ireed.netty06room.NettyConfig.*;

/**
 * 接受/处理/响应客户端webSocket请求的核心业务处理类
 *
 * @author reedsource
 */
public class WebSocketHandler extends ChannelInboundHandlerAdapter {

	/**
	 * webSocket的实现对象
	 */
	private WebSocketServerHandshaker wss;


	/**
	 * 客户端与服务端创建连接时调用
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		//将id记录到公共id记录map中
		ChannelMap.addTimeServerChannel(ctx.channel().id().asLongText(),"WebSocket", ctx.channel());
		DealLog.log("客户端与服务端连接开启 id" + ctx.channel().id().asLongText());
	}

	/**
	 * 客户端与服务端断开连接时调用
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		ChannelMap.removeTimeServerChannel(ctx.channel().id().asLongText());
		DealLog.log("客户端与服务端断开连接 id" + ctx.channel().id().asLongText());
	}

	/**
	 * 服务端接收客户端发送的数据结束后调用
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	/**
	 * 异常调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		DealLog.log("异常",cause);
		ChannelMap.removeTimeServerChannel(ctx.channel().id().asLongText());
		DealLog.log("客户端与服务端异常断开 id" + ctx.channel().id().asLongText());
		ctx.close();
	}

	/**
	 * 服务端处理客户端webSocket请求的核心方法
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// 处理客户端向服务端发起ttp握手请求的业务
		if (msg instanceof FullHttpRequest) {
			handlerHttpRequest(ctx, (FullHttpRequest) msg);
		}
		// 处理webSocket连接业务
		else if (msg instanceof WebSocketFrame) {
			handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}

	/**
	 * 处理客户端向服务端发起的webSocket连接业务
	 */
	private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		// 判断是否是关闭webSocket的指令
		if (frame instanceof CloseWebSocketFrame) {
			wss.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
		}

		// 判断是否是ping消息
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
		}

		// 判断是否是二进制消息，是则抛出异常
		if (!(frame instanceof TextWebSocketFrame)) {
			DealLog.log("目前不支持二进制消息");
			return;
		}

		// 返回应答
		String request = ((TextWebSocketFrame) frame).text();
		DealLog.log("服务端收到客户端的消息===" , request);

		// 群发，服务向每个连接上来的客户群发消息
		ChannelMap.writeAndFlush(request);
	}

	/**
	 * 处理客户端向服务端发起的http握手请求的业务
	 */
	private void handlerHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
		// 请求失败或者不是webSocket请求
		if (!req.decoderResult().isSuccess() || !NettyConfig.WEBSOCKET.equalsIgnoreCase(req.headers().get(UPGRADE))) {
			sendHttpResponse(ctx, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}

		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(NettyConfig.getWebSocketUrl(), null, false);
		wss = wsFactory.newHandshaker(req);
		if (wss == null) {
			// 将错误信息返回
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			wss.handshake(ctx.channel(), req);
		}
	}

	/**
	 * 服务端向客户端相应消息
	 */
	private void sendHttpResponse(ChannelHandlerContext ctx, DefaultFullHttpResponse resp) {

		if (resp.status().code() != T200) {
			ByteBuf buf = Unpooled.copiedBuffer(resp.status().toString(), CharsetUtil.UTF_8);
			// 写入resp
			resp.content().writeBytes(buf);
			buf.release();
		}

		// 发送数据
		ChannelFuture future = ctx.channel().writeAndFlush(resp);
		if (resp.status().code() != T200) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}
}
