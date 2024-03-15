/*
 * FileName: Index
 * Author:   reedsource
 */
package top.ireed.index.entity;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/17 23:15
 * reedsource@189.cn
 */
public class Index {
	/**
	 * 所属模块
	 */
	private final String modules;
	/**
	 * 子模块
	 */
	private final String module;
	/**
	 * 模块编号
	 */
	private final String moduleId;
	/**
	 * 案例名称
	 */
	private final String moduleName;
	/**
	 * 描述
	 */
	private final String describe;
	/**
	 * 请求路径
	 */
	private String url;

	/**
	 * 自定义
	 *
	 * @param modules    所属模块
	 * @param module     子模块
	 * @param moduleId   模块编号
	 * @param moduleName 案例名称
	 * @param describe   描述
	 * @param url        请求路径
	 */
	public Index(String modules, String module, String moduleId, String moduleName, String describe, String url) {
		this.modules = modules;
		this.module = module;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.describe = describe;
		this.url = url;
	}

	public String getModules() {
		return modules;
	}

	public String getModule() {
		return module;
	}

	public String getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getDescribe() {
		return describe;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
