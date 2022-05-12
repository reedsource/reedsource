/*
 * FileName: javaAssert
 * Author:   reedsource
 */
package k03对象.断言;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java断言〉
 * idea启动断言 VM虚拟机选项栏里输入 -enableassertions 或者 -ea
 * <p>
 * 断言只适用复杂的调式过程。
 * 断言一般用于程序执行结构的判断，千万不要让断言处理业务流程。
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class javaAssert {
	public static void main(String args[]) {
		boolean isOpen = false;

		// 如果开启了断言，会将isOpen的值改为true
		assert isOpen = true;

		// 打印是否开启了断言，如果为false，则没有启用断言
		DealLog.log(isOpen);
	}
}
