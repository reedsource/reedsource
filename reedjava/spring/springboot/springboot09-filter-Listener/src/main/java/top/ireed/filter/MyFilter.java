/*
 * FileName: MyFilter
 * Author:   ireed
 */
package top.ireed.filter;

import top.ireed.deal.DealLog;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 功能简述:
 * 〈filter过滤器的实现类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 * <p>
 * 这个注解是方法2
 */
@WebFilter(urlPatterns = "/boot2/*")
public class MyFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {
		DealLog.log("filter init 我将在项目启动同时启动");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		DealLog.log("进入filter过滤器");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		DealLog.log("filter1对象随着服务器关闭销毁了");
	}
}
