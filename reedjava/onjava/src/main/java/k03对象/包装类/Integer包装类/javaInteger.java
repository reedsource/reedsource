/*
 * FileName: javaInteger
 * Author:   reedsource
 */
package k03对象.包装类.Integer包装类;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java数值包装类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 * <p>
 * 所有的包装类（Integer、Long、Byte、Double、Float、Short）都是抽象类 Number 的子类
 * 在实际开发过程中，我们经常会遇到需要使用对象，而不是内置数据类型的情形。
 * 为了解决这个问题，Java 语言为每一个内置数据类型提供了对应的包装类
 * 这种由编译器特别支持的包装称为装箱，所以当内置数据类型被当作对象使用的时候，编译器会把内置类型装箱为包装类。
 * 相似的，编译器也可以把一个对象拆箱为内置类型。Number 类属于 java.lang 包
 */
public class javaInteger {
	public static void main(String[] args) {
		Integer x = 5;
		x = x + 10;
		DealLog.log(x);
	}

	//15
}
