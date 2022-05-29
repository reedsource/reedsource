/*
 * FileName: SpringBootStartApplication
 * Author:   reedsource
 */
package top.ireed;

/**
 * 功能简述:
 * 〈修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法  war包必须〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */

import javafx.application.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 *
 * @author reedsource
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(Application.class);
	}
}
