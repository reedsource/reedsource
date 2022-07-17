/*
 * FileName: JspCookieController
 * Author:   reedsource
 */
package top.ireed.basics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能简述:
 * 〈cookie控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
@Controller
@RequestMapping("basics/cookie")
public class JspCookieController {

	/**
	 * 查看cookie教程
	 *
	 * @return form
	 */
	@GetMapping("cookieForm")
	public String cookieForm() {
		return "basics/cookie/cookieForm";
	}

}
