/*
 * FileName: LinkageUtil
 * Author:   reedsource
 */
package top.ireed.cases.linkage.utils;

import top.ireed.cases.linkage.entity.City;
import top.ireed.cases.linkage.entity.Province;

/**
 * 功能简述:
 * 〈Linkage工具类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
public class LinkageUtil {

	private LinkageUtil() {
	}

	/**
	 * 省数据
	 */
	private static final Province[] provinces = {new Province("1", "湖北省"),
			new Province("2", "山东省"),
			new Province("3", "河北省")};
	/**
	 * 市数据
	 */
	private static final City[] citys = {new City("1", "1", "十堰市"),
			new City("1", "101", "丹江口"),
			new City("1", "102", "神农架"),
			new City("1", "103", "武汉市"),
			new City("1", "104", "宜昌市"),
			new City("1", "105", "荆门市"),
			new City("2", "201", "太原市"),
			new City("2", "202", "济南市"),
			new City("2", "203", "枣庄市"),
			new City("2", "204", "烟台市"),
			new City("3", "301", "邢台市"),
			new City("3", "302", "保定市")};

	/**
	 * @return 省列表数据
	 */
	public static Province[] getProvinces() {
		return provinces;
	}

	/**
	 * @return 市列表数据
	 */
	public static City[] getCitys() {
		return citys;
	}

	/**
	 * 获取省id
	 *
	 * @param province 省名称
	 * @return 省id
	 */
	public static String getProvinceId(String province) {
		//遍历
		for (Province province1 : provinces) {
			//相等
			if (province1.getProvinceName().equals(province)) {
				//返回省id
				return province1.getProvinceId();
			}
		}
		//无数据返回空
		return "";
	}

	/**
	 * 获取市id
	 *
	 * @param city 市名称
	 * @return 省id
	 */
	public static String getCityId(String city) {
		//遍历
		for (City city1 : citys) {
			//相等
			if (city1.getCityName().equals(city)) {
				//返回市id
				return city1.getCityId();
			}
		}
		//无数据返回空
		return "";
	}
}
