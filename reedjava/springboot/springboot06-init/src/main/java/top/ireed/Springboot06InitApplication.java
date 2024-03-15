package top.ireed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import top.ireed.init.InitService;


@SpringBootApplication
public class Springboot06InitApplication implements CommandLineRunner {
	/**
	 * 方法1.3注入方法
	 */
	@Autowired
	private InitService initService;


	public static void main(String[] args) {
		//方法2.1 取到service
		ApplicationContext context = SpringApplication.run(Springboot06InitApplication.class, args);
		//方法2.2 取到service
		InitService service = (InitService) context.getBean("initService");
		//方法2.3 执行方法
		service.show("方法2");
	}


	/**
	 * 方法1 这个类本身在springboot启动时执行
	 *
	 * @param args data
	 */
	@Override
	public void run(String... args) {
		//方法1.4执行方法
		initService.show("方法1,本方法是springboot自带的,不需要手动调用,启动springboot后,将会自动执行,并存在springboot容器中");
	}
}
