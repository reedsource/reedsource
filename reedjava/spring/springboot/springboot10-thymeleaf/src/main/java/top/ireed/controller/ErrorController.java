/*
 * FileName: errorController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ireed.deal.DealLog;
import top.ireed.general.TopConstant;

/**
 * 功能简述:
 * 〈springboot错误页跳转等案例〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@RestController
public class ErrorController {

	/**
	 * 当服务器找不到请求的网页 将会进入404界面
	 *
	 * @return 404界面
	 */
	@GetMapping("/boot/error404")
	public String error404() {
		return "error404 如果看到这串文字,说明错误,正常应该跳转到界面";
	}

	/**
	 * 当系统内部出现错误,将会跳转到500界面
	 *
	 * @return 500界面
	 */
	@GetMapping("/boot/error500")
	public String error500() {
		int m = TopConstant.INT1 / TopConstant.INT0;
		DealLog.log(m);
		return "error500 如果看到这串文字,说明错误,正常应该跳转到界面";
	}
}
