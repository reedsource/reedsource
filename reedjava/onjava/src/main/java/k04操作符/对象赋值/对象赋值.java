/*
 * Author:   reedsource
 */
package k04操作符.对象赋值;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/14 23:21
 * reedsource@189.cn
 */
public class 对象赋值 {
	@Test
	public void 对象赋值_Test() {
		Tank t1 = new Tank();
		Tank t2 = new Tank();
		t1.level = 9;
		t2.level = 47;
		DealLog.log("1: t1.level: " + t1.level +
				", t2.level: " + t2.level);
		t1 = t2;
		DealLog.log("2: t1.level: " + t1.level +
				", t2.level: " + t2.level);
		t1.level = 27;
		DealLog.log("3: t1.level: " + t1.level +
				", t2.level: " + t2.level);

		//两个变量都是对Tank的引用
		//1: t1.level: 9, t2.level: 47
		//2: t1.level: 47, t2.level: 47
		//3: t1.level: 27, t2.level: 27
	}

}

class Tank {
	int level;
}