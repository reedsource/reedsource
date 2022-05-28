/*
 * FileName: MybatisController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ireed.model.OneTable;
import top.ireed.service.MybatisService;

import java.util.List;

/**
 * 功能简述:
 * 〈MybatisController控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Controller
public class MybatisController {
	@Autowired
	private MybatisService mybatisService;

	/**
	 * 注入工具类
	 */
	@Autowired
	private InjectionTool injectionTool;


	/**
	 * 普通mybatis的数据流程
	 *
	 * @return data
	 */
	@GetMapping("/all")
	public @ResponseBody
	String addStudent() {
		List<OneTable> list = mybatisService.all();
		return String.valueOf(list);
	}

	/**
	 * 在主方法内引入包含注入的工具类的使用方式
	 *
	 * @return date
	 */
	@GetMapping("/zhu")
	public @ResponseBody
	String zhu() {
		List<OneTable> list = injectionTool.all();
		return "注入工具类测试返回 : " + list;
	}

	/**
	 * 演示动态拼接sql in 时的拼接方法以及mapper的写法
	 *
	 * @return date
	 */
	@GetMapping("/in")
	public @ResponseBody
	String sqlIn(OneTable oneTable) {

		//sql id in 空白时默认配置
		String[] a = {"1", "2", "3", "4"};

		//拼接sql查询中的in字符串
		StringBuilder inString = new StringBuilder();
		//遍历 For-Each 循环
		for (int i = 0; i < a.length; i++) {
			if (i != 0) {
				inString.append(",");
			}
			inString.append(a[i]);
		}
		//前端没有传入筛选范围的情况下,将配置中 全部的in配置组合string放入
		if (oneTable.getTypeIn() == null) {
			oneTable.setTypeIn(inString.toString());
		}

		List<OneTable> list = mybatisService.inAll(oneTable);
		return "mybatis多条件查询测试 : " + list;
	}
}
