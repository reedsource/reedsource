/*
 * FileName: SessionController
 * Author:   ireed
 */
package top.ireed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 功能简述:
 * 〈session控制器>
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@RestController
public class SessionController {
	@GetMapping("/boot/session/{key}/{value}")
	public String test(@PathVariable String key, @PathVariable String value, HttpSession session) {
		session.setAttribute(key, value);
		return "设置session数据成功  key=" + key + "  value= " + session.getAttribute(key);
	}
}
