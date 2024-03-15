package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.ireed.deal.DealLog;

/**
 * 启动SpringBoot的自动配置
 */
@SpringBootApplication
public class Springboot01BasicsApplication {
	/**
	 * @param args *启动SpringBoot的运行环境 启动Tomcat
	 *             *会返回一个Spring的上下文对象 ApplicationContext，
	 *             *如果当前的SpringBoot不是Web项目，这里不会启动Tomcat，
	 *             *需要使用这个方法的返回值获取我们自定义的Bean对象
	 *             *启动环境以后，将使用Spring的注解，因此我们所有的带有Spring注解的类
	 *             *必须方法当类的同级别目录中或者是子包中，这样SpringBoot会启动默认的包扫描
	 */
	public static void main(String[] args) {
		SpringApplication.run(Springboot01BasicsApplication.class, args);
		DealLog.log("测试路径 http://localhost/boot/hello");
	}

}
