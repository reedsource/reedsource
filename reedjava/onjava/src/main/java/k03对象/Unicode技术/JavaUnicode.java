/*
 * FileName: JavaUnicode
 * Author:   reedsource
 */
package k03对象.Unicode技术;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java可以被执行的注释,注释相关的技术
 * java会先执行编译Unicode编码
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class JavaUnicode {
	public static void main(String[] args) {
		a();
		b();
	}

	/**
	 * \u000d Unicode换行符
	 * jvm编译器会编译Unicode字符
	 */
	public static void a() {
		String a = "我是目标输出";
		//\u000d a = "我不是目标输出";
		DealLog.log(a);
	}

	public static void b() {
		boolean m = false;
		//\u000d m=c(m);
		DealLog.log(m);
	}

	public static boolean c(boolean m) {
		m = true;
		return m;
	}
}
