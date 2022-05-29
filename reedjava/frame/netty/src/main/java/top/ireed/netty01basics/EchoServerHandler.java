package top.ireed.netty01basics;

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
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	/**
	 * 该方法用于接收从客户端接收的信息
	 *
	 * @param ctx 对象
	 * @param msg 消息
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)  {

		// 将接收到信息按字节打印到控制台
		// ByteBuf是一个引用计数对象实现ReferenceCounted，他就是在有对象引用的时候计数+1，无的时候计数-1，当为0对象释放内存
		/**	ByteBuf in = (ByteBuf) msg;
		try {
			while (in.isReadable()) {
				System.out.print((char) in.readByte());
				System.out.flush();
			}
		} finally {
			// 丢弃收到的数据
			ReferenceCountUtil.release(msg);
		}*/

		// 转为ByteBuf类型
		ByteBuf buf = (ByteBuf) msg;
		// 转为字符串
		String m = buf.toString(CharsetUtil.UTF_8);
		DealLog.log(ctx.channel().remoteAddress()+" : "+m);
		//写消息
		ctx.write(msg);
		//冲刷消息
		ctx.flush();
		/**上面两个方法等同于 ctx.writeAndFlush(msg);*/
	}

	/**
	 * 异常事件
	 *
	 * @param ctx 对象
	 * @param cause 处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
		// 当出现异常就关闭连接
		DealLog.log("异常",cause);
		ctx.close();
	}

	/**
	 * 掉线事件
	 *
	 * @param ctx 对象
	 * @throws Exception 异常
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// channel失效处理,客户端下线或者强制退出等任何情况都触发这个方法
		DealLog.log("关闭一个链接",ctx.name());
		super.channelInactive(ctx);
	}

	/**
	 * 连接事件
	 *
	 * @param ctx  对象
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx)  {
		ctx.fireChannelActive();
		DealLog.log("开启一个链接");
	}
}