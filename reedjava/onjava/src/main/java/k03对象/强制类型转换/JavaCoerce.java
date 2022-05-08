/*
 * FileName: JavaCoerce
 * Author:   reedbook
 */
package k03对象.强制类型转换;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java强制类型转换〉
 *
 * @author reedbook
 * @version 1.0.0
 * date 2022/5/7 14:06
 * reedsource@189.cn
 */
public class JavaCoerce {
	public static void main(String[] args) {
		int i1 = 123;
		byte b = (byte) i1;//强制类型转换为byte
		DealLog.log("int强制类型转换为byte后的值等于" + b);
	}

	/*
	 * 1. 条件是转换的数据类型必须是兼容的
	 * 2. 格式：(type)value type是要强制类型转换后的数据类型
	 * 整数的默认类型是 int
	 * 浮点型不存在这种情况，因为在定义 float 类型时必须在数字后面跟上 F 或者 f
	 * */
}
