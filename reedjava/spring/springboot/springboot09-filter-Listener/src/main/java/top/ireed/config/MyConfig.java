/*
 * FileName: MyConfig
 * Author:   ireed
 */
package top.ireed.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ireed.filter.MyFilter;

import javax.servlet.Filter;

/**
 * 功能简述:
 * 〈config配置类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Configuration
public class MyConfig {
	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean() {
		FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>(new MyFilter());
		//可以设置多个
		frb.addUrlPatterns("/boot/*");
		return frb;
	}
}