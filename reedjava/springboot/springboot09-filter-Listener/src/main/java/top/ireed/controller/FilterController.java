/*
 * FileName: TestController
 * Author:   ireed
 */
package top.ireed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈过滤器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 * <p>
 * 注解 @RestController 用来注解需要全部返回字符串的Controller
 */
@RestController
public class FilterController {

	@GetMapping("/boot/filter")
	public String bootFilter() {
		DealLog.log("执行了过滤器filter,本请求将被拦截,会进入过滤器");
		return "执行了过滤器filter,本请求将被拦截,会进入过滤器";
	}

	@GetMapping("/filter")
	public String filter() {
		DealLog.log("执行了过滤器filter,本请求不被拦截");
		return "执行了过滤器filter,本请求不被拦截,不会进入过滤器";
	}


	@GetMapping("/boot2/filter")
	public String boot2Filter() {
		DealLog.log("执行了注解方法2过滤器filter,本请求将被拦截,会进入过滤器");
		return "执行了注解方法2过滤器filter,本请求将被拦截,会进入过滤器";
	}
}
