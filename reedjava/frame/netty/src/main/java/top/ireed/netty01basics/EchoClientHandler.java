package top.ireed.netty01basics;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import top.ireed.deal.DealLog;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

		// 从管道读消息
		// 转为ByteBuf类型
		ByteBuf buf = (ByteBuf) msg;
		// 转为字符串
		String m = buf.toString(CharsetUtil.UTF_8);
		DealLog.log("收到", m);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

		// 当出现异常就关闭连接
		DealLog.log("异常", cause);
		ctx.close();
	}
}