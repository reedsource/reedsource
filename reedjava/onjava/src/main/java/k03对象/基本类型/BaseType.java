/*
 * FileName: BaseType
 * Author:   reedbook
 */
package k03对象.基本类型;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/7 14:06
 * reedsource@189.cn
 */
public class BaseType {
	//本处需要设置为常量 否则需要初始化
	private static boolean bool;
	private static byte by;
	private static char ch;
	private static double d;
	private static float f;
	private static int i;
	private static long l;
	private static short sh;
	private static String str;

	public static void main(String[] args) {
		DealLog.log("java基础类型的默认值");
		DealLog.log("boolean :" + bool);
		DealLog.log("byte :" + by);
		DealLog.log("char:" + ch);
		DealLog.log("double :" + d);
		DealLog.log("float :" + f);
		DealLog.log("int :" + i);
		DealLog.log("long :" + l);
		DealLog.log("short :" + sh);
		DealLog.log("String :" + str);
		DealLog.log();
		/*java基础类型的默认值
		boolean :false
		byte :0
		char:
		double :0.0
		float :0.0
		int :0
		long :0
		short :0
		String :null
		*/

		// byte
		//基本类型：byte 二进制位数：8
		//包装类：java.lang.Byte
		//最小值：Byte.MIN_VALUE=-128
		//最大值：Byte.MAX_VALUE=127
		DealLog.log("基本类型：byte 二进制位数：" + Byte.SIZE);
		DealLog.log("包装类：java.lang.Byte");
		DealLog.log("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
		DealLog.log("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
		DealLog.log();

		// short
		//基本类型：short 二进制位数：16
		//包装类：java.lang.Short
		//最小值：Short.MIN_VALUE=-32768
		//最大值：Short.MAX_VALUE=32767
		DealLog.log("基本类型：short 二进制位数：" + Short.SIZE);
		DealLog.log("包装类：java.lang.Short");
		DealLog.log("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
		DealLog.log("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
		DealLog.log();

		// int
		//基本类型：int 二进制位数：32
		//包装类：java.lang.Integer
		//最小值：Integer.MIN_VALUE=-2147483648
		//最大值：Integer.MAX_VALUE=2147483647
		DealLog.log("基本类型：int 二进制位数：" + Integer.SIZE);
		DealLog.log("包装类：java.lang.Integer");
		DealLog.log("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
		DealLog.log("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
		DealLog.log();

		// long
		//基本类型：long 二进制位数：64
		//包装类：java.lang.Long
		//最小值：Long.MIN_VALUE=-9223372036854775808
		//最大值：Long.MAX_VALUE=9223372036854775807
		DealLog.log("基本类型：long 二进制位数：" + Long.SIZE);
		DealLog.log("包装类：java.lang.Long");
		DealLog.log("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
		DealLog.log("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
		DealLog.log();

		// float
		//基本类型：float 二进制位数：32
		//包装类：java.lang.Float
		//最小值：Float.MIN_VALUE=1.4E-45
		//最大值：Float.MAX_VALUE=3.4028235E38
		DealLog.log("基本类型：float 二进制位数：" + Float.SIZE);
		DealLog.log("包装类：java.lang.Float");
		DealLog.log("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
		DealLog.log("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
		DealLog.log();

		// double
		//基本类型：double 二进制位数：64
		//包装类：java.lang.Double
		//最小值：Double.MIN_VALUE=4.9E-324
		//最大值：Double.MAX_VALUE=1.7976931348623157E308
		DealLog.log("基本类型：double 二进制位数：" + Double.SIZE);
		DealLog.log("包装类：java.lang.Double");
		DealLog.log("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
		DealLog.log("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
		DealLog.log();

		// char
		//基本类型：char 二进制位数：16
		//包装类：java.lang.Character
		//最小值：Character.MIN_VALUE=0
		//最大值：Character.MAX_VALUE=65535
		DealLog.log("基本类型：char 二进制位数：" + Character.SIZE);
		DealLog.log("包装类：java.lang.Character");
		// 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台
		DealLog.log("最小值：Character.MIN_VALUE=" + (int) Character.MIN_VALUE);
		// 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台
		DealLog.log("最大值：Character.MAX_VALUE=" + (int) Character.MAX_VALUE);
	}
}
