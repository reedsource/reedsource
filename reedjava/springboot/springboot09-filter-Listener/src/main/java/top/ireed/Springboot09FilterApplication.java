package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;


@SpringBootApplication
public class Springboot09FilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot09FilterApplication.class, args);
		DealLog.log("会进入过滤器的请求 http://localhost/boot/filter");
		DealLog.log("会进入过滤器的请求 http://localhost/boot2/filter");
		DealLog.log("会进入过滤器的请求 http://localhost/filter");
		DealLog.log("=================================================");
		DealLog.log("触发Listener filter侦听器创建销毁事件  http://localhost/boot/Listener");
		DealLog.log("查询当前在线人数 http://localhost/onlineCount");
		DealLog.log("会进入首页 http://localhost/index");
	}
}