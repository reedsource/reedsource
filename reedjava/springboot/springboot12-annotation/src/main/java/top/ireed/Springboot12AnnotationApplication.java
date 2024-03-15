package top.ireed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;

@SpringBootApplication
public class Springboot12AnnotationApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(Springboot12AnnotationApplication.class, args);
		DealLog.log("测试路径 http://localhost/boot/hello?type=type3");
		DealLog.log("测试路径 http://localhost/enumhandler?type=type03");
	}


	/**
	 * 方法1 这个类本身在springboot启动时执行
	 *
	 * @param args data
	 */
	@Override
	public void run(String... args) {
		//方法1.4执行方法
//		taskHandlerRegister.initApplicationContext()
	}
}
