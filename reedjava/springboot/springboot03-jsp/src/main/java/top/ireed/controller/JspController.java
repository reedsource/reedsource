/*
 * FileName: JspController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈jsp〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Controller
public class JspController {
	@GetMapping("/jsp")
	public String jsp() {
		DealLog.log("进入jsp学习");
		return "jsp/j00jsp";
	}
}
