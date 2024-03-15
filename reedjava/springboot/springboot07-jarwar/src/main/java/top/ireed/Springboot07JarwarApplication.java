package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class Springboot07JarwarApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(Springboot07JarwarApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//注意 Application为类名
		return builder.sources(Springboot07JarwarApplication.class);
	}
}
