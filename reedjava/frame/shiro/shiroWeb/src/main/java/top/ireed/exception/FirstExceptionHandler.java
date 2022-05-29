/*
 * FileName: FirstExceptionHandler
 * Author:   reedsource
 */
package top.ireed.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */

@Component
public class FirstExceptionHandler implements HandlerExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
		ModelAndView mv = new ModelAndView();
		//如果异常等于shiro登陆异常
		if (e instanceof IncorrectCredentialsException || e instanceof UnknownAccountException) {
			//转发请求到错误页面
			mv.setViewName("redirect:/user/error.do");
		}
		return mv;
	}
}
