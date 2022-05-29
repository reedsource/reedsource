package top.ireed.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ireed.entity.User;

/**
 * 功能简述:
 * 〈shiroWeb实现〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 */
@Controller
@RequestMapping("user/")
public class MyController {

	public static final String INDEX = "index";

	/**
	 *  主页
	 * @return 页面
	 */
	@GetMapping("index.do")
	public String inde() {
		return INDEX;
	}
	/**
	 * 错误返回页面   这里的.do 配置 是在web.xml文件中配置
	 * @return 页面
	 */
	@GetMapping("error.do")
	public String error() {
		return "error";
	}

	/**
	 * 登陆
	 * @param user 账户
	 * @return 页面
	 */
	@PostMapping("login.do")
	public String login(User user) {
		//1.根据来自于浏览器提供登陆名和密码进行的登陆验证
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		currentUser.login(token);
		return INDEX;
	}

	/**
	 * 注销
	 * @return 页面
	 */
	@GetMapping("logout.do")
	public String logout() {
		//1.根据来自于浏览器提供登陆名和密码进行的登陆验证
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return INDEX;
	}

	/**
	 *  进入登陆页面
	 * @return 页面
	 */
	@GetMapping("userLogin.do")
	public String userLogin(){
		//提供用户信息查询
		return "login";
	}

	/**
	 *  查询账户  主要用户验证 用户进入界面权限拦截相关配置 shiro.ini [main] [urls] 配置
	 * @return 页面
	 */
	@GetMapping("userList.do")
	public String userList(){
		//提供用户信息查询
		return "userList";
	}

	/**
	 * 标签学习相关界面
	 * @return 页面
	 */
	@GetMapping("shiro.do")
	public String shiro(){
		return "shiro";
	}

}
