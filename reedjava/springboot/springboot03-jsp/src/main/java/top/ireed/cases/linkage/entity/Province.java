/*
 * FileName: Province
 * Author:   reedsource
 */
package top.ireed.cases.linkage.entity;

/**
 * 功能简述:
 * 〈模拟查询的实体的数据〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
public class Province {
	/**
	 * 省id
	 */
	private final String provinceId;
	/**
	 * 省名称
	 */
	private final String provinceName;

	/**
	 * 自定义
	 *
	 * @param provinceId   省id
	 * @param provinceName 省名称
	 */
	public Province(String provinceId, String provinceName) {
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

}
