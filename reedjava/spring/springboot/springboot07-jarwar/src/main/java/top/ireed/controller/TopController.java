/*
 * FileName: TopController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Controller
public class TopController {

	@GetMapping("boot")
	public String jarTest() {
		return "打包项目";
	}
}
