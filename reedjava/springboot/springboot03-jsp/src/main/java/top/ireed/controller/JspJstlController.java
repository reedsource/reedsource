/*
 * FileName: JspJstlController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.ireed.deal.DealLog;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能简述:
 * 〈jsp jstl标签库学习〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Controller
public class JspJstlController {

	@GetMapping("/jstl")
	public String jstl(HttpServletRequest request) {
		DealLog.log("进入jstl学习");
		List<String> sList = new ArrayList<>();
		sList.add("aaa");
		sList.add("bbb");
		sList.add("ccc");
		sList.add("ddd");
		sList.add("eee");
		sList.add("fff");
		request.setAttribute("sList", sList);
		return "jstl/jstl00theory";
	}


}
