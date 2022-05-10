/*
 * FileName: javaOperation
 * Author:   reedsource
 */
package k04操作符.自增自减运算符;

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
	 * 自增自减运算符
	 */
	@Test
	public void o2() {
		//1、自增（++）自减（--）运算符是一种特殊的算术运算符，
		//在算术运算符中需要两个操作数来进行运算，而自增自减运算符是一个操作数
		int a = 3;//定义一个变量；
		int b = ++a;//自增运算
		int c = 3;
		int d = --c;//自减运算
		DealLog.log("进行自增运算后的值等于" + b);
		DealLog.log("进行自减运算后的值等于" + d);

		//int b = ++a; 拆分运算过程为: a=a+1=4; b=a=4, 最后结果为b=4,a=4
		//int d = --c; 拆分运算过程为: c=c-1=2; d=c=2, 最后结果为d=2,c=2

		//2、前缀自增自减法(++a,--a): 先进行自增或者自减运算，再进行表达式运算。
		//3、后缀自增自减法(a++,a--): 先进行表达式运算，再进行自增或者自减运算
		int e = 5;
		int f = 5;
		int x = 2 * ++e;
		int y = 2 * f++;
		DealLog.log("自增运算符前缀运算后e=" + e + ",x=" + x);
		DealLog.log("自增运算符后缀运算后f=" + f + ",y=" + y);

		//自增运算符前缀运算后a=6，x=12
		//自增运算符后缀运算后b=6，y=10
	}
}
