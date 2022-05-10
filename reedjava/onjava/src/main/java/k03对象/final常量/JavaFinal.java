/*
 * FileName: JavaFinal
 * Author:   reedsource
 */
package k03对象.final常量;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java常量〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/7 14:06
 * reedsource@189.cn
 */
public class JavaFinal {
	public static void main(String[] args) {
		//Java 中使用 final 关键字来修饰常量 ,是不能被修改的
		final double PI = 3.1415927;
		//虽然常量名也可以用小写，但为了便于识别，通常使用大写字母表示常量。
		//字面量可以赋给任何内置类型的变量
		byte a = 68;
		char b = 'A';
		//byte、int、long、和short都可以用十进制、16进制以及8进制的方式来表示。
		//当使用常量的时候，前缀 0 表示 8 进制，而前缀 0x 代表 16 进制
		//以下是不同进制下的100
		int decimal = 100;
		int octal = 0144;
		int he = 0x64;
		//Java的字符串常量也是包含在两个引号之间的字符序列
		String m = "Hello World";
		//字符串常量和字符常量都可以包含任何Unicode字符
		char v = '\u0001';
		String n = "\u0001";
		DealLog.log(PI);
		DealLog.log(a);
		DealLog.log(b);
		DealLog.log(decimal);
		DealLog.log(octal);
		DealLog.log(he);
		DealLog.log(m);
		DealLog.log(v);
		DealLog.log(n);
	}
}
