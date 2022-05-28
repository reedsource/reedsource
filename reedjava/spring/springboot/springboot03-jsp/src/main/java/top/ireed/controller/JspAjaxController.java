/*
 * FileName: jspAjaxController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ireed.deal.DealJackson;
import top.ireed.deal.DealLog;
import top.ireed.entity.Student;
import top.ireed.general.TopException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能简述:
 * 〈jsp ajax控制器〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 */
@Controller
public class JspAjaxController {

	@GetMapping("/ajax")
	public String ajax() {
		DealLog.log("进入ajax01学习");
		return "ajax/ajax00theory";
	}


	/**
	 * ajax简单反馈
	 *
	 * @param str1 数据1
	 * @param str2 数据2
	 * @param str3 数据3
	 * @return 数据文本
	 */
	@PostMapping(value = "/ajax00")
	@ResponseBody
	public String ajax00(String str1, String str2, String str3) throws InterruptedException {
		DealLog.log("进入ajax01初识原理");

		//异步的显示,3000后才返回jsp
		Thread.sleep(3000);
		return "进入ajax01初识原理后端返回数据 " + str1 + " " + str2 + " " + str3;
	}

	/**
	 * ajax类数据反馈
	 *
	 * @return 类数据
	 */
	@PostMapping(value = "/ajax011")
	@ResponseBody
	public String ajax011() throws TopException {
		Student s = new Student("A0001", "zs", 23);
		Student s1 = new Student("A0002", "ls", 24);
		List<Student> list = new ArrayList<>(16);
		list.add(s);
		list.add(s1);
		return DealJackson.getJson(list);
	}


	/**
	 * @return 省列表
	 */
	@PostMapping(value = "/ajax021")
	@ResponseBody
	public String ajax021() throws TopException {
		List<String> provinceList = new ArrayList<>(16);
		provinceList.add("河北省");
		provinceList.add("湖北省");
		provinceList.add("山东省");
		return DealJackson.getJson(provinceList);
	}

	/**
	 * @return 市列表
	 */
	@PostMapping(value = "/ajax022")
	@ResponseBody
	public String ajax022(String province) throws TopException {
		Map<String, String> cityMap = new HashMap<>(16);
		cityMap.put("邢台市", "河北省");
		cityMap.put("保定市", "河北省");
		cityMap.put("十堰市", "湖北省");
		cityMap.put("丹江口", "湖北省");
		cityMap.put("神农架", "湖北省");
		cityMap.put("太原市", "山东省");
		//省下的市
		List<String> cityList = new ArrayList<>(16);
		for (Map.Entry<String, String> entry : cityMap.entrySet()) {
			if (province.equals(cityMap.get(entry.getKey()))) {
				cityList.add(entry.getKey());
			}
		}
		return DealJackson.getJson(cityList);
	}

	/**
	 * @return 发送数据到其它jsp界面,并将jsp界面内容全部返回到当前
	 */
	@GetMapping("/data")
	public String data(HttpServletRequest request) {
		request.setAttribute("data", "我是后端发往data页面的数据");
		//返回其他界面
		return "ajax/data";
	}
}
