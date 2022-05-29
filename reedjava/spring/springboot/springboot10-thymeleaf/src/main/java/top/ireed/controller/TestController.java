/*
 * FileName: TestController
 * Author:   ireed
 */
package top.ireed.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.ireed.modal.User;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 功能简述:
 * 〈控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Controller
public class TestController {
	/**
	 * Thymeleaf语法
	 *
	 * @param session session
	 * @return
	 */
	@GetMapping("/index")
	public String index(Model model, HttpSession session) {
		model.addAttribute("name", "张三");
		User user = new User("李四1", "北京1", 21, 1);
		User user1 = new User("李四2", "北京2", 22, 1);
		User user2 = new User("李四3", "北京3", 23, 2);
		User user3 = new User("李四4", "北京4", 24, 2);
		model.addAttribute("user", user);

		List<User> list = new ArrayList<>();
		list.add(user);
		list.add(user1);
		list.add(user2);
		list.add(user3);

		model.addAttribute("userList", list);

		Map<String, User> map = new HashMap<>(16);
		map.put("123", user);
		map.put("124", user1);
		map.put("125", user2);
		map.put("126", user);
		model.addAttribute("userMap", map);

		model.addAttribute("number", 7);
		User user4 = new User("王五", "上海", 25, 1);
		session.setAttribute("userSession", user4.toString());

		model.addAttribute("nowDate", new Date());
		model.addAttribute("domainName", "www.ireed.com");

		model.addAttribute("StringData", "<span style=\"color: red\">我是红色的html文本</span>");
		//注意 本处只有pom引入thymeleaf才可以使用CTRL+跳转
		return "index";
	}

	/**
	 * @return 界面跳转
	 */
	@GetMapping("/boot/user/")
	public String info() {
		return "info";
	}

	/**
	 * @param id   id
	 * @param user user
	 * @return 界面
	 */
	@GetMapping("/deleteUser")
	public String deleteUser(String id, User user) {
		return "info";
	}
}
