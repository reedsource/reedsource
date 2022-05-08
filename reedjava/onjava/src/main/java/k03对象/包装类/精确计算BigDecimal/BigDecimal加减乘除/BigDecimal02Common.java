/*
 * FileName: BigDecimal02Common
 * Author:   reedbook
 */
package k03对象.包装类.精确计算BigDecimal.BigDecimal加减乘除;

import top.ireed.deal.DealLog;

import java.math.BigDecimal;

/**
 * 功能简述:
 * 〈常用方法〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class BigDecimal02Common {
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal("100");
		BigDecimal b = new BigDecimal("200");

		DealLog.log("BigDecimal对象中的值相加，返回BigDecimal对象 " + a.add(b));
		DealLog.log("BigDecimal对象中的值相减，返回BigDecimal对象 " + a.subtract(b));
		DealLog.log("BigDecimal对象中的值相乘，返回BigDecimal对象 " + a.multiply(b));
		DealLog.log("BigDecimal对象中的值相除，返回BigDecimal对象 " + a.divide(b));

		/*通过BigDecimal的divide方法进行除法时当不整除，出现无限循环小数时，
		就会抛异常：java.lang.ArithmeticException: Non-terminating decimal expansion;
		no exact representable decimal result.
		解决方法 divide方法设置精确的小数点，如：divide(xxxxx,2)*/

		DealLog.log("将BigDecimal对象中的值转换成字符串 " + a.toString());
		DealLog.log("将BigDecimal对象中的值转换成双精度数 " + a.doubleValue());
		DealLog.log("将BigDecimal对象中的值转换成单精度数 " + a.floatValue());
		DealLog.log("将BigDecimal对象中的值转换成长整数 " + a.longValue());
		DealLog.log("将BigDecimal对象中的值转换成整数 " + a.intValue());
	}
}
