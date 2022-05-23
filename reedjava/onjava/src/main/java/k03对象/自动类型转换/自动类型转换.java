/*
 * FileName: 自动类型转换
 * Author:   reedsource
 */
package k03对象.自动类型转换;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java自动类型转换〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/7 14:06
 * reedsource@189.cn
 */
public class 自动类型转换 {
	/**
	 * 整型、实型（常量）、字符型数据可以混合运算。运算中，不同类型的数据先转化为同一类型，然后进行运算
	 * 低  ------------------------------------>  高
	 * byte,short,char—> int —> long—> float —> double
	 * <p>
	 * 在java中，如果比int类型小的类型做运算，java在编译的时候就会将它们统一强转成int类型。
	 * 当是比int类型大的类型做运算，就会自动转换成它们中最大类型那个
	 * <p>
	 * 数据类型转换必须满足如下规则：
	 * 1. 不能对boolean类型进行类型转换。
	 * 2. 不能把对象类型转换成不相关类的对象。
	 * 3. 在把容量大的类型转换为容量小的类型时必须使用强制类型转换。
	 * 4. 转换过程中可能导致溢出或损失精度，例如
	 * 5. 浮点数到整数的转换是通过舍弃小数得到，而不是四舍五入
	 * (int)23.7 == 23;
	 * (int)-45.89f == -45
	 * <p>
	 * java 中，任何字符类型与字符串相加，结果都是拼接
	 * int 类型在赋值到 Integer 类时，会自动封装，调用 Integer 的 valueOf(int i) 方法
	 * <p>
	 * 当 i >= -128 && i <= 127 时，Integer.valueOf(i) 会将 i 存储在内部类 IntegerCache的static final Integer cache[]里，
	 * 这一字节的缓存内存地址是静态的，返回值即:
	 * IntegerCache.cache[i + (-IntegerCache.low)]
	 * Integer a = 1;
	 * Integer b = 1;
	 * a 和 b 的引用都指向同一个对象，即 a == b。
	 */
	@Test
	public void 自动类型转换_Test() {
		//必须满足转换前的数据类型的位数要低于转换后的数据类型，
		// 例如: short数据类型的位数为16位，就可以自动转换位数为32的int类型，
		// 同样float数据类型的位数为32，可以自动转换为64位的double类型

		//定义一个char类型
		//char自动类型转换为int
		int i1 = 'a';
		DealLog.log("char自动类型转换为int后的值等于" + i1);
		//定义一个char类型
		char c2 = 'A';
		//char 类型和 int 类型计算
		int i2 = c2 + 1;
		DealLog.log("char类型和int计算后的值等于" + i2);

		//解析：c1 的值为字符 a ,查 ASCII 码表可知对应的 int 类型值为 97， A 对应值为 65，所以 i2=65+1=66

		int i = 128;
		byte b = (byte) i;
		//因为 byte 类型是 8 位，最大值为127，所以当 int 强制转换为 byte 类型时，值 128 时候就会导致溢出
		DealLog.log("数值溢出的" + b);
	}
}

