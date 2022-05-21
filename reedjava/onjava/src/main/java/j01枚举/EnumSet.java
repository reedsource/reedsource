/*
 * FileName: EnumSet
 * Author:   reedsource
 */
package j01枚举;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/21 23:52
 * reedsource@189.cn
 */
public class EnumSet {
	/**
	 * 演示EnumSet如何使用，EnumSet是一个抽象类，获取一个类型的枚举类型内容
	 */
	@Test
	public void EnumSet_Test() {
		java.util.EnumSet<枚举类型> currEnumSet = java.util.EnumSet.allOf(枚举类型.class);
		for (枚举类型 aLightSetElement : currEnumSet) {
			DealLog.log("当前EnumSet中数据为", aLightSetElement);
		}
	}
}
