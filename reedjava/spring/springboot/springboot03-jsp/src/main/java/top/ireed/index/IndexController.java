/*
 * FileName: IndexController
 * Author:   reedsource
 */
package top.ireed.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.ireed.deal.DealLog;
import top.ireed.index.entity.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能简述:
 * 〈主页控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
@Controller
public class IndexController {

	/**
	 * 常量
	 */
	private static final String JSP_BASICS = "JSP基础";
	/**
	 * 常量
	 */
	private static final String JSP_CASES = "JSP案例";
	/**
	 * 常量
	 */
	private static final String JSP_ANALYSIS = "JSP原理";

	/**
	 * 首页
	 *
	 * @param model 组装
	 * @return 界面
	 */
	@GetMapping("/index")
	public String jsp(Model model) {
		DealLog.log("进入jsp学习主页");
		model.addAttribute("index", getIndex());
		return "index";
	}

	/**
	 * 组装界面数据
	 *
	 * @return 数据
	 */
	private static List<Index> getIndex() {
		//主页列表数据
		List<Index> list = new ArrayList<>();
		list.add(new Index(JSP_ANALYSIS, "生命周期", "1", "生命周期", "", "/analysis/vita"));
		list.add(new Index(JSP_BASICS, "基本语法", "1", "基本结构语法", "", "/basics/vita"));
		list.add(new Index(JSP_BASICS, "基本结构语法", "2", "页面自动刷新", "", "/basics/jspAutoFlush"));
		list.add(new Index(JSP_BASICS, "HTTP", "1", "http消息头信息", "请求头案例列表", "/basics/http/httpRequestHeader"));
		list.add(new Index(JSP_BASICS, "HTTP", "1", "http返回状态码", "状态码实例自定义状态码为650", "/basics/http/httpResponseSendError"));
		list.add(new Index(JSP_CASES, "form跳转表单", "1", "jsp界面接收数据", "get post请求", "/basics/from/form"));
		list.add(new Index(JSP_CASES, "cookie主页", "1", "jsp控制cookie主界面", "jsp cookie增删改查", "/basics/cookie/cookieForm"));
		list.add(new Index(JSP_CASES, "session主页", "1", "jsp控制session主界面", "jsp session增删改查", "/basics/session/sessionForm"));
		list.add(new Index(JSP_CASES, "省市联动", "1", "添加页", "默认省市状态", "/cases/linkage/form"));
		list.add(new Index(JSP_CASES, "省市联动", "2", "修改页", "首选项省市状态", "/cases/linkage/edit"));
		return list;
	}
}
