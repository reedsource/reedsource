package top.ireed.netty05httpupload;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.util.CharsetUtil;
import top.ireed.deal.DealLog;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static top.ireed.netty05httpupload.NettyConfig.FROM_FILE_URL;
import static top.ireed.netty05httpupload.NettyConfig.UPLOAD_URL;

/**
 * @author reedsource
 */
public class HttpUploadServerHandler extends SimpleChannelInboundHandler<HttpObject> {

	public static final String H_1 = " <h1> welcome home </h1> ";
	public static final String STRING = "/";
	private HttpRequest request;

	/**
	 * 如果超过磁盘大小
	 */
	private static final HttpDataFactory FACTORY = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);

	/**
	 * Netty 解码器  解码Body并可以处理POST BODY
	 */
	private HttpPostRequestDecoder decoder;

	static {
		//应该删除文件
		DiskFileUpload.deleteOnExitTemporaryFile = true;
		//退出(正常退出)
		//系统临时目录
		DiskFileUpload.baseDirectory = null;
		//应该删除文件
		DiskAttribute.deleteOnExitTemporaryFile = true;
		//退出(正常退出)
		//系统临时目录
		DiskAttribute.baseDirectory = null;
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		if (decoder != null) {
			decoder.cleanFiles();
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		//获取url 路由 将请求引导到不同的处理功能
		if (msg instanceof HttpRequest) {
			this.request = (HttpRequest) msg;
			URI uri = new URI(request.uri());
			DealLog.log("请求url : ", uri);
			urlRoute(ctx, uri.getPath());
		}

		if (decoder != null) {
			if (msg instanceof HttpContent) {
				// 接收一个新的请求体
				decoder.offer((HttpContent) msg);
				// 将内存中的数据序列化本地
				readHttpDataChunkByChunk();
			}

			if (msg instanceof LastHttpContent) {
				DealLog.log("文件上传成功");
				reset();
				writeResponse(ctx, "<h1>上传成功</h1>");
			}
		}
	}

	/**
	 * url路由
	 *
	 * @param ctx 通道
	 * @param uri 请求后缀uri
	 */
	private void urlRoute(ChannelHandlerContext ctx, String uri) {

		StringBuilder urlResponse = new StringBuilder();

		// 访问单文件上传页面
		if (uri.startsWith(UPLOAD_URL)) {
			urlResponse.append(getUploadResponse());
			//文件上传请求
		} else if (uri.startsWith(FROM_FILE_URL)) {
			decoder = new HttpPostRequestDecoder(FACTORY, request);
			return;
		} else {
			//进入主页
			urlResponse.append(getHomeResponse());
		}
		writeResponse(ctx, urlResponse.toString());
	}

	private void writeResponse(ChannelHandlerContext ctx, String context) {
		ByteBuf buf = Unpooled.copiedBuffer(context, CharsetUtil.UTF_8);
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
		response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
		//设置短连接 addListener 写完马上关闭连接
		ctx.channel().writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	/**
	 * 主页html拼接
	 *
	 * @return html文本
	 */
	private String getHomeResponse() {
		return H_1;
	}

	/**
	 * @return 上传界面html文本
	 */
	private String getUploadResponse() {
		return "<!DOCTYPE html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <title>Title</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"\n" +
				"<form action=\"http://" + NettyConfig.getNettyIp() + ":8080/post_multipart\" enctype=\"multipart/form-data\" method=\"POST\">\n" +
				"\n" +
				"\n" +
				"    <input type=\"file\" name=" +
				" " +
				"" +
				"\"YOU_KEY\">\n" +
				"\n" +
				"    <input type=\"submit\" name=\"send\">\n" +
				"\n" +
				"</form>\n" +
				"\n" +
				"</body>\n" +
				"</html>";
	}

	private void readHttpDataChunkByChunk() throws IOException {
		// while 是为了接受完整数据后处理
		while (decoder.hasNext()) {
			// 获得文件数据
			InterfaceHttpData data = decoder.next();
			if (data != null && data.getHttpDataType() == InterfaceHttpData.HttpDataType.FileUpload) {
				FileUpload fileUpload = (FileUpload) data;
				if (fileUpload.isCompleted()) {
					//告诉文件是否在内存中
					DealLog.log(fileUpload.isInMemory());
					//或存档
					//可以搬到另一个地方
					fileUpload.renameTo(new File(PathUtil.getFileBasePath() + STRING + fileUpload.getFilename()));
					//文件的位置
					//删除
					decoder.removeHttpDataFromClean(fileUpload);
				}
			}
		}
	}

	private void reset() {
		request = null;
		//销毁解码器释放所有资源
		decoder.destroy();
		decoder = null;
	}
}
