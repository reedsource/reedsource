/*
 * FileName: JspBasicsController
 * Author:   reedsource
 */
package top.ireed.basics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能简述:
 * 〈jsp基础控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
@Controller
@RequestMapping("basics/http")
public class JspHttpController {

	/**
	 * http消息头信息
	 *
	 * @return httpHeader
	 */
	@GetMapping("httpRequestHeader")
	public String httpRequestHeader() {
		return "basics/http/httpRequestHeader";
	}

	/**
	 * http响应头信息
	 *
	 * @return httpHeader
	 */
	@GetMapping("httpResponseSendError")
	public String httpResponseSendError() {
		return "basics/http/httpResponseSendError";
	}
}
