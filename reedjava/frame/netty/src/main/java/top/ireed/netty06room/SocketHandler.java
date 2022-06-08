package top.ireed.netty06room;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import top.ireed.deal.DealLog;


//ChannelInboundHandlerAdapter实现自ChannelInboundHandler
//ChannelInboundHandler提供了不同的事件处理方法你可以重写

/**
 * @author reedsource
 */
public class SocketHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 注册通道
	 *
	 * @param ctx Channel对象
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) {
		ctx.fireChannelRegistered();
	}

	/**
	 * 通道未注册
	 *
	 * @param ctx Channel对象
	 */
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) {
		ctx.fireChannelUnregistered();
	}

	/**
	 * 连接事件
	 *
	 * @param ctx 对象
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		//将id记录到公共id记录map中
		ChannelMap.addTimeServerChannel(ctx.channel().id().asLongText(), "Socket", ctx.channel());
		DealLog.log("客户端与服务端连接开启 id" + ctx.channel().id().asLongText());
	}

	/**
	 * 掉线事件
	 *
	 * @param ctx 对象
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		ChannelMap.removeTimeServerChannel(ctx.channel().id().asLongText());
		DealLog.log("客户端与服务端断开连接 id" + ctx.channel().id().asLongText());
	}

	/**
	 * 该方法用于接收从客户端接收的信息
	 *
	 * @param ctx 对象
	 * @param msg 消息
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// 转为ByteBuf类型
		ByteBuf buf = (ByteBuf) msg;
		// 转为字符串
		String m = buf.toString(CharsetUtil.UTF_8);
		DealLog.log("socket收到", ctx.channel().remoteAddress() + " : " + m);
		ChannelMap.writeAndFlush(m);

		//写消息
		ctx.write(m);
		//冲刷消息
		ctx.flush();
		// 上面两个方法等同于  writeAndFlush出现消息粘连问题 弃用
		// ctx.writeAndFlush(msg);
	}

	/**
	 * 通道阅读完成
	 *
	 * @param ctx Channel对象
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.fireChannelReadComplete();
	}

	/**
	 * 用户事件触发
	 *
	 * @param ctx Channel对象
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
		ctx.fireUserEventTriggered(evt);
	}

	/**
	 * 通道可写性改变
	 *
	 * @param ctx Channel对象
	 */
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) {
		ctx.fireChannelWritabilityChanged();
	}

	/**
	 * 异常事件
	 *
	 * @param ctx   对象
	 * @param cause 处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// 当出现异常就关闭连接
		DealLog.log("异常", cause);
		ChannelMap.removeTimeServerChannel(ctx.channel().id().asLongText());
		DealLog.log("客户端与服务端异常断开 id", ctx.channel().id().asLongText());
		ctx.close();
	}
}