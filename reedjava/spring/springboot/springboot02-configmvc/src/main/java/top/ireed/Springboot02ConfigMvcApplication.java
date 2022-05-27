package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;

@SpringBootApplication
public class Springboot02ConfigMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot02ConfigMvcApplication.class, args);
		DealLog.log("测试路径 http://localhost:8080/dev/boot/test");
	}

}
