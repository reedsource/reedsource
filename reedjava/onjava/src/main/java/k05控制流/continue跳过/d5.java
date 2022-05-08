/*
 * FileName: d5
 * Author:   reedbook
 */
package k05控制流.continue跳过;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class d5 {
	/**
	 * continue 跳过关键字
	 * <p>
	 * continue 适用于任何循环控制结构中。作用是让程序立刻跳转到下一次循环的迭代。
	 * 在 for 循环中，continue 语句使程序立即跳转到更新语句。
	 * 在 while 或者 do…while 循环中，程序立即跳转到布尔表达式的判断语句。
	 */
	@Test
	public void d5() {
		int[] numbers = {10, 20, 30, 40, 50};

		for (int x : numbers) {
			if (x == 30) {
				continue;
			}
			DealLog.log(x);
			DealLog.log("\n");
		}

		//10
		//20
		//40
		//50
	}
}
