/*
 * FileName: UserController
 * Author:   ireed
 */
package top.ireed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ireed.deal.DealLog;
import top.ireed.entity.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能简述:
 * 〈jspEl的Controller〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Controller
public class JspElController {
	@GetMapping("/EL01")
	public String el01(HttpServletRequest request) {
		DealLog.log("e0010el代码初识及取值");
		request.setAttribute("str1", "a111");

		DealLog.log("el表达式接收不同类型数据测试");

		String[] strArr = {"aaa", "bbb", "ccc"};
		request.setAttribute("strArr", strArr);

		List<String> sList = new ArrayList<>();
		sList.add("abc");
		sList.add("bcd");
		sList.add("cde");
		request.setAttribute("sList", sList);

		Map<String, String> map = new HashMap<>(16);
		map.put("str1", "aaa");
		map.put("str2", "bbb");
		request.setAttribute("myMap", map);


		Student s = new Student("A0001", "zs", 23);
		request.setAttribute("s", s);

		DealLog.log("servlet3");

		request.setAttribute("a", "50");
		request.setAttribute("b", 20);

		//EL获取拼接的el表达式的值
		request.setAttribute("ELis", "我获取的是 EL 拼接 me(is) 之后的 ELis 的 EL表达式的值");
		request.setAttribute("me", "is");

		return "el/el01";
	}

	/**
	 * 返回一个字符串到界面
	 *
	 * @return 字符串
	 */
	@GetMapping("/EL02")
	public @ResponseBody
	String el02() {
		return "简单字符串回复 <br><a href=\"/EL01\"> el表达式主界面 </a>";
	}

}
