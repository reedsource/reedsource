package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;

import java.io.IOException;

/**
 * @author reedsource
 * 启动SpringBoot的自动配置
 */
@SpringBootApplication
public class Springboot03JspApplication {

	public static void main(String[] args) throws IOException {
		DealLog.log("springboot03-jsp学习项目启动");
		DealLog.log("EL表达式教程 http://localhost/EL01");
		DealLog.log("AJAX表达式教程 http://localhost/ajax");
		DealLog.log("jstl表达式教程 http://localhost/jstl");
		DealLog.log("jsp教程 http://localhost/jsp");

		//启动调用浏览器打开界面
		Runtime.getRuntime().exec("cmd   /c   start   http://localhost/index");
		SpringApplication.run(Springboot03JspApplication.class, args);
	}
}
