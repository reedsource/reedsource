/*
 * FileName: Optional05Abnormal
 * Author:   reedsource
 */
package k03对象.Optional处理空指针;

import org.junit.Test;

import java.util.Optional;

/**
 * 功能简述:
 * 〈返回异常〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class Optional05Abnormal {

	/**
	 * orElseThrow() API —— 它会在对象为空的时候抛出异常，而不是返回备选的值
	 * 如果 user 值为 null，会抛出 IllegalArgumentException
	 * 可以决定抛出什么样的异常，而不总是抛出 NullPointerException
	 */
	@Test
	public void whenThrowException_thenOk() {
		User user = null;
		User result = Optional.ofNullable(user)
				.orElseThrow( () -> new IllegalArgumentException());
	}
}
