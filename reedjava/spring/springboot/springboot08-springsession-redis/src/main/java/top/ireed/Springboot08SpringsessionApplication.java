package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;


@SpringBootApplication
public class Springboot08SpringsessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot08SpringsessionApplication.class, args);
		DealLog.log("本项目需要启动连接目标redis");
		DealLog.log("springboot session启动验证 http://localhost/boot/session/key01/value01");
		DealLog.log("springboot redis赋值验证 http://localhost/boot/redis/key2/value33333");
		DealLog.log("springboot redis取值验证 http://localhost/boot/redis/key2");
		DealLog.log("springboot redis自增验证 http://localhost/boot/redisId");
	}

}
