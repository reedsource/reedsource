/*
 * Author:   reedsource
 */
package k04操作符;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.Random;

/**
 * 功能简述:
 * 〈 只能操作基本类型里面的整数类型
 * 左移位操作符<<会将操作符左侧的操作数向左移动,移动的位数在操作符右侧指定,低位补0
 * 右移位操作符>> 向左移动
 * 如果对比char byte short移位运算 将会转换为ini类型. 结果也是int类型
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/20 20:55
 * reedsource@189.cn
 */
public class 移位操作符 {

	@Test
	public void 移位操作符_Test() {
		//无符合右移测试
		int i = -1;
		DealLog.log(Integer.toBinaryString(i));
		i >>>= 10;
		DealLog.log(Integer.toBinaryString(i));
		long l = -1;
		DealLog.log(Long.toBinaryString(l));
		l >>>= 10;
		DealLog.log(Long.toBinaryString(l));
		short s = -1;
		DealLog.log(Integer.toBinaryString(s));
		s >>>= 10;
		DealLog.log(Integer.toBinaryString(s));
		byte b = -1;
		DealLog.log(Integer.toBinaryString(b));
		b >>>= 10;
		DealLog.log(Integer.toBinaryString(b));
		b = -1;
		DealLog.log(Integer.toBinaryString(b));
		DealLog.log(Integer.toBinaryString(b >>> 10));

		//11111111111111111111111111111111
		//1111111111111111111111
		//1111111111111111111111111111111111111111111111111111111111111111
		//111111111111111111111111111111111111111111111111111111
		//11111111111111111111111111111111
		//11111111111111111111111111111111
		//11111111111111111111111111111111
		//11111111111111111111111111111111
		//11111111111111111111111111111111
		//1111111111111111111111
	}


	@Test
	public void 位操作_Test() {
		Random rand = new Random(47);
		int i = rand.nextInt();
		int j = rand.nextInt();
		printBinaryInt("-1", -1);
		printBinaryInt("+1", +1);
		int maxpos = 2147483647;
		printBinaryInt("maxpos", maxpos);
		int maxneg = -2147483648;
		printBinaryInt("maxneg", maxneg);
		printBinaryInt("i", i);
		printBinaryInt("~i", ~i);
		printBinaryInt("-i", -i);
		printBinaryInt("j", j);
		printBinaryInt("i & j", i & j);
		printBinaryInt("i | j", i | j);
		printBinaryInt("i ^ j", i ^ j);
		printBinaryInt("i << 5", i << 5);
		printBinaryInt("i >> 5", i >> 5);
		printBinaryInt("(~i) >> 5", (~i) >> 5);
		printBinaryInt("i >>> 5", i >>> 5);
		printBinaryInt("(~i) >>> 5", (~i) >>> 5);

		long l = rand.nextLong();
		long m = rand.nextLong();
		printBinaryLong("-1L", -1L);
		printBinaryLong("+1L", +1L);
		long ll = 9223372036854775807L;
		printBinaryLong("maxpos", ll);
		long lln = -9223372036854775808L;
		printBinaryLong("maxneg", lln);
		printBinaryLong("l", l);
		printBinaryLong("~l", ~l);
		printBinaryLong("-l", -l);
		printBinaryLong("m", m);
		printBinaryLong("l & m", l & m);
		printBinaryLong("l | m", l | m);
		printBinaryLong("l ^ m", l ^ m);
		printBinaryLong("l << 5", l << 5);
		printBinaryLong("l >> 5", l >> 5);
		printBinaryLong("(~l) >> 5", (~l) >> 5);
		printBinaryLong("l >>> 5", l >>> 5);
		printBinaryLong("(~l) >>> 5", (~l) >>> 5);
	}

	static void printBinaryInt(String s, int i) {
		//输出二进制
		DealLog.log(s + ", int: " + i + ", binary:\n   " + Integer.toBinaryString(i));
	}

	static void printBinaryLong(String s, long l) {
		//输出二进制
		DealLog.log(s + ", long: " + l + ", binary:\n    " + Long.toBinaryString(l));
	}
}
