/*
 * FileName: User
 * Author:   reedsource
 */
package top.ireed.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/27 17:22
 * reedsource@189.cn
 * <p>
 * 将ireed配置文件映射为user对象
 */
@ConfigurationProperties(prefix = "ireed")
@Component("user")
public class User {
	private String name;
	private String add;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", add='" + add + '\'' +
				'}';
	}
}
