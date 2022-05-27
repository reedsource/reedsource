/*
 * FileName: ConfigController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ireed.model.User;

/**
 * 功能简述:
 * 〈config控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/27 17:22
 * reedsource@189.cn
 */
@Controller
public class ConfigController {
	@Value("${ireed.name}")
	private String name;

	/**
	 * 如果这里的参数add和抽象类user的内容不符,将返回 null/0/false...等
	 */
	@Value("${ireed.add}")
	private String add;

	@Autowired
	private User user;

	/**
	 * 读取配置文件 及映射的文件对象
	 *
	 * @return msg
	 */
	@GetMapping("/boot/test")
	public @ResponseBody
	String test() {
		return "name:" + name + "  address:" + add + "    " + user;
	}
}
