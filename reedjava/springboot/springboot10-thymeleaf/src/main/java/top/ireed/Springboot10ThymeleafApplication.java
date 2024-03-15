package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;

@SpringBootApplication
public class Springboot10ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot10ThymeleafApplication.class, args);
		DealLog.log("Thymeleaf语法教程 http://localhost/index");
		DealLog.log("springboot404界面演示 http://localhost/boot/error400");
		DealLog.log("springboot500界面演示 http://localhost/boot/error500");
	}
}
