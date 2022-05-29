package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;


@SpringBootApplication
public class Springboot12AnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot12AnnotationApplication.class, args);
		DealLog.log("测试路径 http://localhost/boot/hello");
	}

}
