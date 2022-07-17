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
@RequestMapping("basics")
public class JspBasicsController {

	/**
	 * jsp 基本语法原理
	 *
	 * @return vita
	 */
	@GetMapping("vita")
	public String vita() {
		return "basics/basics/vita";
	}

	/**
	 * jsp 基本语法原理
	 *
	 * @return vita
	 */
	@GetMapping("jspAutoFlush")
	public String jspAutoFlush() {
		return "basics/basics/jspAutoFlush";
	}

}
