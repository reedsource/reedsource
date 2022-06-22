/*
 * FileName: while循环
 * Author:   reedsource
 */
package k05控制流;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class while循环 {
	/**
	 * while 循环
	 * <p>
	 * while是最基本的循环，它的结构为：
	 * while( 布尔表达式 ) {
	 * //循环内容
	 * }
	 * 只要布尔表达式为 true，循环就会一直执行下去
	 */
	@Test
	public void d0() {
		int x = 10;
		while (x < 20) {
			DealLog.log("value of x : " + x);
			x++;
		}

		//value of x : 10
		//value of x : 11
		//value of x : 12
		//value of x : 13
		//value of x : 14
		//value of x : 15
		//value of x : 16
		//value of x : 17
		//value of x : 18
		//value of x : 19
	}
}
