/*
 * FileName: MyMvcConfig
 * Author:   reedsource
 */
package top.ireed.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ireed.listener.cases.HttpSessionListener01;

import javax.servlet.http.HttpSessionListener;

/**
 * 功能简述:
 * 〈〉
 * springboot中我们没有了web.xml等相关xml配置文件，取而代之的是使用@Configuration注解在java代码中实现相关配置。于是我们可以创建一个配置类
 * # @Configuration其实就是告诉spring，spring容器要怎么配置
 * （怎么去注册bean，怎么去处理bean之间的关系（装配）=>@Configuration相当于<beans></beans>便签，作用为配置spring容器(应用上下文)
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Configuration
public class MyMvcConfig {
	/**
	 * @return
	 * @ Bean的意思就是，我要获取这个bean的时候，你spring要按照这种方式去帮我获取到这个bean。
	 * @ Bean注解的方法会实例化、配置并初始化一个新的对象，这个对象会由spring IoC 容器管理。
	 */
	@Bean
	public ServletListenerRegistrationBean<HttpSessionListener> listenerRegist() {
		ServletListenerRegistrationBean<HttpSessionListener> srb = new ServletListenerRegistrationBean<>();
		srb.setListener(new HttpSessionListener01());
		return srb;
	}
}