/*
 * FileName: City
 * Author:   reedsource
 */
package top.ireed.cases.linkage.entity;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
public class City {
	/**
	 * 省编号
	 */
	private final String provinceId;
	/**
	 * 市编号
	 */
	private final String cityId;
	/**
	 * 市名称
	 */
	private final String cityName;

	/**
	 * 自定义新建
	 *
	 * @param provinceId 省编号
	 * @param cityId     市编号
	 * @param cityName   市名称
	 */
	public City(String provinceId, String cityId, String cityName) {
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public String getCityName() {
		return cityName;
	}
}
