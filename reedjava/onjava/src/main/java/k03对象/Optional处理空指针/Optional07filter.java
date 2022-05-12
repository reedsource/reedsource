/*
 * FileName: Optional07filter
 * Author:   reedsource
 */
package k03对象.Optional处理空指针;

import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

/**
 * 功能简述:
 * 〈过滤值〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Optional07filter {

	/**
	 * 参数过滤filter
	 * <p>
	 * 根据基本的电子邮箱验证来决定接受或拒绝 User(用户) 的示例
	 * 如果通过过滤器测试，result 对象会包含非空值
	 */
	@Test
	public void whenFilter_thenOk() {
		User user = new User("anna@gmail.com", "1234");
		//过滤 是否包含 @
		Optional<User> result = Optional.ofNullable(user)
				.filter(u -> u.getEmail() != null && u.getEmail().contains("@"));

		assertTrue(result.isPresent());
	}
}
