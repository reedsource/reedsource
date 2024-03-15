package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import top.ireed.controller.InitService;

@SpringBootApplication
public class Springboot11Log4jApplication {

	public static void main(String[] args) {
		//方法2.1 取到service
		ApplicationContext context = SpringApplication.run(Springboot11Log4jApplication.class, args);
		//方法2.2 取到service
		InitService service = (InitService) context.getBean("InitService");
		//方法2.3 执行方法
		service.show("日志方法");
	}

}
