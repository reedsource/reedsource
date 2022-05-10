/*
 * FileName: d3
 * Author:   reedsource
 */
package k05控制流.for循环增强;

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
public class d3 {
	/**
	 * Java 增强 for 循环
	 * <p>
	 * Java5 引入了一种主要用于数组的增强型 for 循环。
	 * <p>
	 * Java 增强 for 循环语法格式如下:
	 * <p>
	 * for(声明语句 : 表达式)
	 * {
	 * //代码句子
	 * }
	 * 声明语句：声明新的局部变量，该变量的类型必须和数组元素的类型匹配。其作用域限定在循环语句块，其值与此时数组元素的值相等。
	 * <p>
	 * 表达式：表达式是要访问的数组名，或者是返回值为数组的方法。
	 */
	@Test
	public void d3() {
		int[] numbers = {10, 20, 30, 40, 50};

		for (int x : numbers) {
			DealLog.log(x);
			DealLog.log(",");
		}
		DealLog.log("\n");
		String[] names = {"James", "Larry", "Tom", "Lacy"};
		for (String name : names) {
			DealLog.log(name);
			DealLog.log(",");
		}

		//10,20,30,40,50,
		//James,Larry,Tom,Lacy,
	}
}
