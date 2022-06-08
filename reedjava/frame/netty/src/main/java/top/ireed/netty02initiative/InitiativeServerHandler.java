package top.ireed.netty02initiative;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import top.ireed.deal.DealLog;

import java.util.concurrent.TimeUnit;

//ChannelInboundHandlerAdapter实现自ChannelInboundHandler
//ChannelInboundHandler提供了不同的事件处理方法你可以重写

/**
 * @author reedsource
 */
public class InitiativeServerHandler extends ChannelInboundHandlerAdapter {

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
		ctx.fireChannelActive();

		//当前连接id
		String uid = ctx.channel().id().asLongText();
		DealLog.log("当前连接对象id为 : ", uid);
		//将id记录到公共id记录map中
		ChannelMap.addTimeServerChannel(uid, ctx.channel());

		//启动调度定时任务,设置定时任务周期
		Task task = new Task(3);
		ctx.channel().eventLoop().scheduleAtFixedRate(task, 1, 3L, TimeUnit.SECONDS);


		// 创建当前时间返回文本 时间
		final ByteBuf time = Unpooled.copiedBuffer(String.valueOf(System.currentTimeMillis() / 1000L), CharsetUtil.UTF_8);

		//本处通过id获取通道 与上面记录对应
		Channel ch = ChannelMap.getTimeServerChannel(uid);

		// 出站操作返回ChannelFuture
		final ChannelFuture f = ch.writeAndFlush(time);


		// 监听器出站事件
		/*	f.addListener(new ChannelFutureListener() {
		// 创建一个匿名的ChannelFutureListener 类用来在操作完成时关闭 Channel
			@Override
			public void operationComplete(ChannelFuture future) {
				//出站成功 连接关闭
				DealLog.log("时间服务数据返回成功,本处关闭通道将忽略 忽略通道id ",uid);
			}
		});*/

		// 监听器出站事件
		// 创建一个匿名的ChannelFutureListener 类用来在操作完成时关闭 Channel
		//出站成功 连接关闭
		f.addListener(future ->
						DealLog.log("时间服务数据返回成功,本处关闭通道将忽略 忽略通道id ", uid)
				/*ctx.close();*/
		);
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
		DealLog.log("关闭一个链接" + ctx.name() + "链接信息 " + ctx.channel().remoteAddress());

		//从连接记录中删除连接
		ChannelMap.removeTimeServerChannel(ctx.channel().id().asLongText());
		super.channelInactive(ctx);
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
		DealLog.log(ctx.channel().remoteAddress() + " : " + m);

		//写消息
		ctx.write(msg);
		//冲刷消息
		ctx.flush();
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

		//从连接记录中删除连接
		ChannelMap.removeTimeServerChannel(ctx.channel().id().asLongText());
		ctx.close();
	}
}