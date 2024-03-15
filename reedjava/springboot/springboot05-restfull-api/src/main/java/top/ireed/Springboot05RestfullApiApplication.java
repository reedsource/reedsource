package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;

@SpringBootApplication
public class Springboot05RestfullApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot05RestfullApiApplication.class, args);
		DealLog.log("启动验证 http://localhost/boot/test/1234/name");
	}
}
