/*
 * Author:   reedsource
 */
package k06初始化和清理.this关键字;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/24 18:44
 * reedsource@189.cn
 */
public class 当前对象传递给另一个方法 {

	@Test
	public void 当前对象传递给另一个方法_Test() {
		new 人类().吃(new 苹果类());

		//人 吃了 苹果类{m='去皮苹果'}

		//调用吃的时候,会把当前的苹果类传递给剥皮机处理
		//得到一个去皮苹果
	}
}

class 人类 {
	public void 吃(苹果类 苹果) {
		苹果类 peeled = 苹果.去皮();
		DealLog.log("人", "吃了", peeled);
	}
}

class 剥皮机 {
	static 苹果类 剥皮(苹果类 苹果) {
		// ... 剥皮
		苹果.m = "去皮苹果";

		// 剥皮后的
		return 苹果;
	}
}

class 苹果类 {
	String m = "普通苹果";

	苹果类 去皮() {
		//这里需要调用外部方法,直接将自己传递出去,因为是引用,可以直接被外部处理
		return 剥皮机.剥皮(this);
	}

	@Override
	public String toString() {
		return "苹果类{" + "m='" + m + '\'' + '}';
	}
}