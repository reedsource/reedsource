/*
 * FileName: JspFormController
 * Author:   reedsource
 */
package top.ireed.basics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能简述:
 * 〈form控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
@Controller
@RequestMapping("basics/from")
public class JspFormController {

	/**
	 * 查看简单表单提交等
	 *
	 * @return form
	 */
	@GetMapping("form")
	public String getForm() {
		return "basics/form/form";
	}
}
