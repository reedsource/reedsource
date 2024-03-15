/*
 * FileName: provinceCityCountyController
 * Author:   reedsource
 */
package top.ireed.cases.linkage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ireed.cases.linkage.entity.City;
import top.ireed.cases.linkage.entity.Linkage;
import top.ireed.cases.linkage.utils.LinkageUtil;
import top.ireed.deal.DealJackson;
import top.ireed.general.TopException;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能简述:
 * 〈本处主要展示省市联动〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
@Controller
@RequestMapping("cases/linkage")
public class LinkageController {

	/**
	 * 初始进入界面 只初始化 一般添加界面 下拉框能展示全部省市数据
	 *
	 * @return 联动界面
	 */
	@GetMapping("form")
	public String form(Model model) {
		//设置数据
		model.addAttribute("linkage", new Linkage());
		return "cases/linkage/linkage";
	}

	/**
	 * 本处场景 省市修改界面 进入时省市数据已经展示,同时下拉框能展示全部省市数据
	 *
	 * @return 联动界面
	 */
	@GetMapping("edit")
	public String edit(Model model) {
		//设置数据
		model.addAttribute("linkage", new Linkage("湖北省", "十堰市"));
		return "cases/linkage/linkage";
	}

	/**
	 * @return 省列表
	 */
	@PostMapping("province")
	@ResponseBody
	public String province() throws TopException {
		// 返回省数组列表数据
		return DealJackson.getJson(LinkageUtil.getProvinces());
	}

	/**
	 * @return 市列表
	 */
	@PostMapping("city")
	@ResponseBody
	public String city(String provinceId) throws TopException {
		//数据list
		List<City> list = new ArrayList<>();
		// 遍历省列表
		for (City city : LinkageUtil.getCitys()) {
			//省id等于市中的省id
			if (provinceId.equals(city.getProvinceId())) {
				//加入数据list
				list.add(city);
			}
		}
		//json格式化
		return DealJackson.getJson(list);
	}
}
