/*
 * FileName: 算术运算符
 * Author:   reedsource
 */
package k04操作符;

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
public class 算术运算符 {
	/**
	 * 算术运算符
	 * <p>
	 * +	加法 - 相加运算符两侧的值	A + B 等于 30
	 * -	减法 - 左操作数减去右操作数	A – B 等于 -10
	 * 乘法 - 相乘操作符两侧的值	A * B等于200
	 * /	除法 - 左操作数除以右操作数	B / A等于2
	 * ％	取余 - 左操作数除以右操作数的余数	B%A等于0
	 * ++	自增: 操作数的值增加1	B++ 或 ++B 等于 21（区别详见下文）
	 * --	自减: 操作数的值减少1	B-- 或 --B 等于 19（区别详见下文）
	 */
	@Test
	public void o1() {
		int a = 10;
		int b = 20;
		int c = 25;
		int d = 25;
		DealLog.log(a, b, c, d);
		DealLog.log("a + b = " + (a + b));
		DealLog.log("a - b = " + (a - b));
		DealLog.log("a * b = " + (a * b));
		DealLog.log("b / a = " + (b / a));
		DealLog.log("b % a = " + (b % a));
		DealLog.log("c % a = " + (c % a));
		DealLog.log("c % a = " + (c % a));
		DealLog.log("c %= a = " + (c %= a));


		DealLog.log("a++   = " + (a++));
		DealLog.log("a--   = " + (a--));
		DealLog.log("++a   = " + (++a));
		DealLog.log("--a   = " + (--a));
		// 查看  d++ 与 ++d 的不同
		DealLog.log("d++   = " + (d++));
		DealLog.log("++d   = " + (++d));


		// 单精度浮点数测试 float /double:
		float v = 3.14f;
		float w = 1.34f;
		DealLog.log(v, w);
		DealLog.log("v : " + v);
		DealLog.log("w : " + w);
		DealLog.log("v + w : " + (v + w));
		DealLog.log("v - w : " + (v - w));
		DealLog.log("v * w : " + v * w);
		DealLog.log("v / w : " + v / w);


		// 适用于 byte, short, int, long, and double:
		DealLog.log("v += w : " + (v += w));
		DealLog.log("v -= w : " + (v -= w));
		DealLog.log("v *= w : " + (v *= w));
		DealLog.log("v /= w : " + (v /= w));

		//10 20 25 25
		//a + b = 30
		//a - b = -10
		//a * b = 200
		//b / a = 2
		//b % a = 0
		//c % a = 5
		//c % a = 5
		//c %= a = 5
		//a++   = 10
		//a--   = 11
		//++a   = 11
		//--a   = 10
		//d++   = 25
		//++d   = 27
		//3.14 --> 1.34
		//v : 3.14
		//w : 1.34
		//v + w : 4.48
		//v - w : 1.8000001
		//v * w : 4.2076
		//v / w : 2.3432837
		//v += w : 4.48
		//v -= w : 3.1399999
		//v *= w : 4.2076
		//v /= w : 3.14
	}
}
