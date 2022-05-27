/*
 * FileName: Optional判空条件返回
 * Author:   reedsource
 */
package k03对象.工具类.Optional处理空指针;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * 功能简述:
 * 〈Optional返回值〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Optional判空条件返回 {

	/**
	 * orElse()，它的工作方式非常直接，如果有值则返回该值，否则返回传递给它的参数值
	 * 这里 user 对象是空的，所以返回了作为默认值的 user2。
	 */
	@Test
	public void 判空返回类似ifElse() {
		User user = null;
		User user2 = new User("anna@gmail.com", "1234");
		User user3 = new User("aaa", "bbb");
		User result = Optional.ofNullable(user).orElse(user2);
		User result2 = Optional.ofNullable(user2).orElse(user3);

		assertEquals(user2.getEmail(), result.getEmail());

		//如果对象的初始值不是 null，那么默认值会被忽略：
		assertEquals(user3.getEmail(), result2.getEmail());
	}

	/*
	第二个同类型的 API 是 orElseGet
	其行为略有不同。
	这个方法会在有值的时候返回值，如果没有值，它会执行作为参数传入的 Supplier(供应者) 函数式接口，
	并将返回其执行结果

	两种方法 无值时返回一致

	orElse 当有值时 依然会创建函数中的对象 执行方法

	orElseGet 当有值时 不会再执行函数中的方法了
	*/
	@Test
	public void 判空true执行函数返回() {
		User user = new User("john@gmail.com", "1234");
		DealLog.log("orElse");
		User result = Optional.ofNullable(user).orElse(createNewUser());
		DealLog.log("orElseGet");
		User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());

		DealLog.log("========================================");

		User user2 = null;
		DealLog.log("orElse2");
		User result3 = Optional.ofNullable(user2).orElse(createNewUser());
		DealLog.log("orElseGet2");
		User result4 = Optional.ofNullable(user2).orElseGet(() -> createNewUser());
	}

	private User createNewUser() {
		DealLog.log("createNewUser创建了一个对象");
		return new User("extra@gmail.com", "1234");
	}

}
