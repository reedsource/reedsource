package top.ireed.netty05httpupload;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import top.ireed.deal.DealLog;

import javax.net.ssl.SSLException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;

import static top.ireed.netty05httpupload.NettyConfig.UPLOAD_URL;

/**
 * @author reedsource
 */
final class HttpUploadServer {

	/**
	 * 当前ssl证书是否加载
	 */
	private static final boolean SSL = System.getProperty("ssl") != null;

	/**
	 * ssl证书加载 使用8443 否则使用8080
	 */
	private static final int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : "8080"));

	void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// 配置SSL
			final SslContext sslCtx;
			if (SSL) {
				SelfSignedCertificate ssc = new SelfSignedCertificate();
				sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
			} else {
				sslCtx = null;
			}
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.handler(new LoggingHandler(LogLevel.INFO));
			b.childHandler(new HttpUploadServerInitializer(sslCtx));
			Channel ch = b.bind(PORT).sync().channel();
			//获得本机IP
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress();
			NettyConfig.setNettyIp(ip);
			DealLog.log("打开您的web浏览器并导航到 " + (SSL ? "https" : "http") + "://" + ip + ":" + PORT + UPLOAD_URL);
			ch.closeFuture().sync();
		} catch (CertificateException|SSLException|InterruptedException|UnknownHostException e ) {
			DealLog.log("异常",e);
			Thread.currentThread().interrupt();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
