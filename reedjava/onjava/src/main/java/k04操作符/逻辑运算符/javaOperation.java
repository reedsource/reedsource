/*
 * FileName: javaOperation
 * Author:   reedsource
 */
package k04操作符.逻辑运算符;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java运算符〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class javaOperation {
	/**
	 * 逻辑运算符
	 * <p>
	 * &&	    称为逻辑与运算符。当且仅当两个操作数都为真，条件才为真。	        （A && B）为假。
	 * | |	称为逻辑或操作符。如果任何两个操作数任何一个为真，条件为真。	    （A | | B）为真。
	 * ！	    称为逻辑非运算符。用来反转操作数的逻辑状态。
	 * 如果条件为true，则逻辑非运算符将得到false。	                    ！（A && B）为真。
	 */
	@Test
	public void o5() {
		boolean a = true;
		boolean b = false;
		DealLog.log("a && b = " + (a && b));
		DealLog.log("a || b = " + (a || b));
		DealLog.log("!(a && b) = " + !(a && b));
		//a && b = false
		//a || b = true
		//!(a && b) = true

		//短路逻辑运算符
		//当使用与逻辑运算符时，在两个操作数都为true时，结果才为true，
		// 但是当得到第一个操作为false时，其结果就必定是false，
		// 这时候就不会再判断第二个操作了。

		int m = 5;//定义一个变量；
		boolean n = (m < 4) && (m++ < 10);
		DealLog.log("使用短路逻辑运算符的结果为" + n);
		DealLog.log("m的结果为" + m);

		//使用短路逻辑运算符的结果为false
		//a的结果为5

		//解析： 该程序使用到了短路逻辑运算符(&&)，首先判断 a<4 的结果为 false，
		// 则 b 的结果必定是 false，
		// 所以不再执行第二个操作 a++<10 的判断，所以 a 的值为 5。
	}
}
