/*
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能简述:
 * 〈springboot基本controller控制器  返回基本字符串〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/27 17:22
 * reedsource@189.cn
 * <p>
 * 标明控制器注解
 */
@Controller
public class HelloController {
	/**
	 * @return 处理请求地址映射的注解, 表示类中的所有响应请求的方法都是以该地址作为父路径
	 * 将 HTTP 请求正文插入方法中,使浏览器界面可以识别
	 */
	@GetMapping("/boot/hello")
	public @ResponseBody
	String hello() {
		return "Hello SpringBoot!";
	}
}
