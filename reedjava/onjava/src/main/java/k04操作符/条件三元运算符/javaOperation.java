/*
 * FileName: javaOperation
 * Author:   reedbook
 */
package k04操作符.条件三元运算符;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java运算符〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class javaOperation {

	/**
	 * 条件运算符（?:）
	 * <p>
	 * 条件运算符也被称为三元运算符。
	 * 该运算符有3个操作数，并且需要判断布尔表达式的值。
	 * 该运算符的主要是决定哪个值应该赋值给变量。
	 * variable x = (expression) ? value if true : value if false
	 */
	@Test
	public void o7() {
		int a, b;
		a = 10;
		// 如果 a 等于 1 成立，则设置 b 为 20，否则为 30
		b = (a == 1) ? 20 : 30;
		DealLog.log("Value of b is : " + b);

		// 如果 a 等于 10 成立，则设置 b 为 20，否则为 30
		b = (a == 10) ? 20 : 30;
		DealLog.log("Value of b is : " + b);

		//Value of b is : 30
		//Value of b is : 20
	}
}
