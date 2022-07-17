/*
 * FileName: JspSessionController
 * Author:   reedsource
 */
package top.ireed.basics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能简述:
 * 〈session控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
@Controller
@RequestMapping("basics/session")
public class JspSessionController {

	/**
	 * 查看简单session
	 *
	 * @return 界面
	 */
	@GetMapping("/sessionForm")
	public String sessionForm() {
		return "basics/session/sessionForm";
	}

}
