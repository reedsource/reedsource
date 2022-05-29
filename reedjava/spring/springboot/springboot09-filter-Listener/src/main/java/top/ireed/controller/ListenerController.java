/*
 * FileName: ListenerController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ireed.listener.cases.HttpSessionListener01;

import javax.servlet.http.HttpSession;

/**
 * 功能简述:
 * 〈Listener侦听器控制〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@RestController
public class ListenerController {

	/**
	 * @return 测试Listener
	 */
	@GetMapping("/boot/Listener")
	public String bootListener() {
		return "请求进入时,Listener request对象创建,请求返回后 Listener request对象销毁";
	}

	/**
	 * 统计网站访问人数
	 *
	 * @return 数量
	 */
	@GetMapping("/onlineCount")
	public String onlineCount() {
		return "网站当前在线人数为" + HttpSessionListener01.getOnlineNum();
	}

	/**
	 * 统计访问量或在线用户
	 *
	 * @param httpSession httpSession对象
	 * @return 界面
	 */
	@GetMapping("/index")
	public String index(HttpSession httpSession) {
		//用户进入  或登录时,将特定信息存入session 触发session会话域 统计访问量或在线用户量
		httpSession.setAttribute("username", "username");
		return "首页";
	}
}
