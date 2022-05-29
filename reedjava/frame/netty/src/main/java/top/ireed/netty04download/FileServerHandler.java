/*
 * FileName: FileServerHandler
 * Author:   reedsource
 */
package top.ireed.netty04download;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
import top.ireed.deal.DealLog;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static top.ireed.netty04download.NettyConfig.cn;
import static top.ireed.netty04download.NettyConfig.httpPath;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
public class FileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private static final String HTTP_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";
	private static final String HTTP_DATE_GMT_TIMEZONE = "GMT";
	private static final int HTTP_CACHE_SECONDS = 60;
	private static final String PATH_1 = "/";
	private static final String PATH_2 = ".";
	public static final String CONTENT_TYPE = "Content-Type";

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		// 监测解码情况
		if (!request.decoderResult().isSuccess()) {
			sendError(ctx, BAD_REQUEST);
			return;
		}
		//转换请求路径为绝对路径
		final String uri = request.uri();
		final String path = sanitizeUri(uri);
		if (path == null) {
			sendError(ctx, FORBIDDEN);
			return;
		}
		//读取要下载的文件
		File file = new File(path);
		if (file.isHidden() || !file.exists()) {
			sendError(ctx, NOT_FOUND);
			return;
		}
		//路径是否是一个标准文件
		if (file.isDirectory()) {
			if (uri.endsWith(PATH_1)) {
				sendListing(ctx, file);
			} else {
				sendRedirect(ctx, uri + '/');
			}
			return;
		}
		//是否路径
		if (!file.isFile()) {
			sendError(ctx, FORBIDDEN);
			return;
		}
		// 缓存验证
		String ifModifiedSince = request.headers().get("If-Modified-Since");
		if (ifModifiedSince != null && !ifModifiedSince.isEmpty()) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
			Date ifModifiedSinceDate = dateFormatter.parse(ifModifiedSince);
			//只比较第二种情况，因为我们发送的是datetime格式  发送到客户 去除毫秒
			long ifModifiedSinceDateSeconds = ifModifiedSinceDate.getTime() / 1000;
			long fileLastModifiedSeconds = file.lastModified() / 1000;
			if (ifModifiedSinceDateSeconds == fileLastModifiedSeconds) {
				sendNotModified(ctx);
				return;
			}
		}
		//对文件随机访问的操作，访问包括读和写
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			long fileLength = raf.length();
			HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
			HttpUtil.setContentLength(response, fileLength);
			setContentTypeHeader(response, file);
			setDateAndCacheHeaders(response, file);
			if (HttpUtil.isKeepAlive(request)) {
				response.headers().set("CONNECTION", "keep-alive");
			}
			//写上起始行和标题。
			ctx.write(response);
			//写出内容。
			ChannelFuture sendFileFuture;
			if (ctx.pipeline().get(SslHandler.class) == null) {
				sendFileFuture = ctx.write(new DefaultFileRegion(raf.getChannel(), 0, fileLength), ctx.newProgressivePromise());
			} else {
				sendFileFuture = ctx.write(new HttpChunkedInput(new ChunkedFile(raf, 0, fileLength, 8192)), ctx.newProgressivePromise());
			}
			sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
				@Override
				public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) {
					//数据总长度
					if (total < 0) {
						DealLog.log(future.channel() + " 转移进展: " + progress);
					} else {
						DealLog.log(future.channel() + " 转移进展: " + progress + " / " + total);
					}
				}

				@Override
				public void operationComplete(ChannelProgressiveFuture future) {
					DealLog.log(future.channel() + " 传输完成.");
				}
			});
			//写上结束标记
			ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
			//决定是否关闭连接。
			if (!HttpUtil.isKeepAlive(request)) {
				//当全部内容写完后关闭连接。
				lastContentFuture.addListener(ChannelFutureListener.CLOSE);
			}
		} catch (FileNotFoundException e) {
			sendError(ctx, NOT_FOUND);
			DealLog.log("下载文件异常", e);
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		DealLog.log("异常", cause);
		if (ctx.channel().isActive()) {
			sendError(ctx, INTERNAL_SERVER_ERROR);
		}
	}

	private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");

	/**
	 * 将请求的路径解码为服务器绝对路径
	 *
	 * @param uri 请求路径
	 * @return 绝对路径
	 */
	private static String sanitizeUri(String uri) {
		//解码路径
		try {
			uri = URLDecoder.decode(uri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			DealLog.log("路径解码异常", e);
		}
		//如果路径不包含/
		if (!uri.startsWith(PATH_1)) {
			return null;
		}

		// 转换文件分隔符
		uri = uri.replace('/', File.separatorChar);

		//简单的安全检查
		//你必须在生产环境中做一些重要的事情
		if (uri.contains(File.separator + PATH_2) || uri.contains(PATH_2 + File.separator) || uri.startsWith(PATH_2) || uri.endsWith(PATH_2) || INSECURE_URI.matcher(uri).matches()) {
			return null;
		}
		//转换为绝对路径。
		return httpPath + File.separator + uri;
	}

	private static final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9.]*");

	/**
	 * 前端显示界面拼接
	 *
	 * @param ctx 通道对象
	 * @param dir 文件路径
	 */
	private static void sendListing(ChannelHandlerContext ctx, File dir) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
		response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");

		//提取文件路径 一般此信息不应该显示到前端
		StringBuilder buf = new StringBuilder();
		String dirPath = dir.getPath();

		buf.append("<!DOCTYPE html>\r\n");
		buf.append("<html><head><title>");
		buf.append("Listing of: ");
		buf.append("页面标签标题");
		buf.append("</title></head><body>\r\n");

		buf.append("<h3>目录标题: ");
		buf.append("我是一个文件标题 本处显示文件路径").append(dirPath);
		buf.append("</h3>\r\n");

		buf.append("<ul>");
		buf.append("<li><a href=\"../\">..</a></li>\r\n");

		for (File f : Objects.requireNonNull(dir.listFiles())) {
			String name = f.getName();
			//是否共享路径下中文目录
			if ((!cn && !ALLOWED_FILE_NAME.matcher(name).matches()) || (f.isHidden() || !f.canRead())) {
				//剔除中文路径
				continue;
			}
			buf.append("<li><a href=\"");
			buf.append(name);
			buf.append("\">");
			buf.append(name);
			buf.append("</a></li>\r\n");
		}

		buf.append("</ul></body></html>\r\n");
		ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
		response.content().writeBytes(buffer);
		buffer.release();

		//发送错误消息后立即关闭连接。
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	private static void sendRedirect(ChannelHandlerContext ctx, String newUri) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, FOUND);
		response.headers().set("Location", newUri);

		//发送错误消息后立即关闭连接。
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status, Unpooled.copiedBuffer("Failure: "
				+ status + "\r\n", CharsetUtil.UTF_8));
		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");

		//发送错误消息后立即关闭连接。
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	/**
	 * 当文件时间戳与浏览器正在发送的内容相同时，发送一个“304未修改”
	 *
	 * @param ctx Context
	 */
	private static void sendNotModified(ChannelHandlerContext ctx) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, NOT_MODIFIED);
		setDateHeader(response);

		//发送错误消息后立即关闭连接。
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	/**
	 * 设置HTTP响应的日期标头
	 *
	 * @param response HTTP response
	 */
	private static void setDateHeader(FullHttpResponse response) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
		dateFormatter.setTimeZone(TimeZone.getTimeZone(HTTP_DATE_GMT_TIMEZONE));

		Calendar time = new GregorianCalendar();
		response.headers().set("Date", dateFormatter.format(time.getTime()));
	}

	/**
	 * 设置HTTP响应的日期和缓存标头
	 *
	 * @param response    HTTP response
	 * @param fileToCache file to extract content type
	 */
	private static void setDateAndCacheHeaders(HttpResponse response, File fileToCache) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
		dateFormatter.setTimeZone(TimeZone.getTimeZone(HTTP_DATE_GMT_TIMEZONE));

		// Date header
		Calendar time = new GregorianCalendar();
		response.headers().set("Date", dateFormatter.format(time.getTime()));

		// Add cache headers
		time.add(Calendar.SECOND, HTTP_CACHE_SECONDS);
		response.headers().set("Expires", dateFormatter.format(time.getTime()));
		response.headers().set("Cache-Control", "private, max-age=" + HTTP_CACHE_SECONDS);
		response.headers().set("Last-Modified", dateFormatter.format(new Date(fileToCache.lastModified())));
	}

	/**
	 * 设置HTTP响应的内容类型头
	 *
	 * @param response HTTP response
	 * @param file     file to extract content type
	 */
	private static void setContentTypeHeader(HttpResponse response, File file) {
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		response.headers().set(CONTENT_TYPE, mimeTypesMap.getContentType(file.getPath()));
	}
}