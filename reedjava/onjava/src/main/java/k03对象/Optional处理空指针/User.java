/*
 * FileName: User
 * Author:   reedsource
 */
package k03对象.Optional处理空指针;

import java.util.Optional;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class User {
	private String email;
	private String name;

	/**
	 * 添加了一个方法，用来返回 Optional
	 */
	private String position;

	public void setPosition(String position) {
		this.position = position;
	}

	public Optional<String> getPosition() {
		return Optional.ofNullable(position);
	}

	public User(String email) {
		this.email = email;
	}

	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"email='" + email + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
