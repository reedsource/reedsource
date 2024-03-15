/*
 * FileName: Linkage
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
public class Linkage {
	/**
	 * 省名称
	 */
	private String provinceName;
	/**
	 * 市名称
	 */
	private String cityName;

	public Linkage() {
	}

	/**
	 * 自定义
	 *
	 * @param provinceName 省名称
	 * @param cityName     市名称
	 */
	public Linkage(String provinceName, String cityName) {
		this.provinceName = provinceName;
		this.cityName = cityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public String getCityName() {
		return cityName;
	}
}
