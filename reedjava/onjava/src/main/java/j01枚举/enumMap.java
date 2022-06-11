/*
 * FileName: enumMap
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
 * date 2022/5/21 23:51
 * reedsource@189.cn
 */
public class enumMap {
	/**
	 * 演示EnumMap的使用，
	 * EnumMap跟HashMap的使用差不多，只不过key要是枚举类型
	 */
	@Test
	public void EnumMap_Test() {
		java.util.EnumMap<枚举类型, String> currEnumMap = new java.util.EnumMap<>(枚举类型.class);
		currEnumMap.put(枚举类型.RED, "红灯");
		currEnumMap.put(枚举类型.GREEN, "绿灯");
		currEnumMap.put(枚举类型.YELLOW, "黄灯");

		// 2.遍历对象
		for (枚举类型 枚举类型 : 枚举类型.values()) {
			DealLog.log("[key=" + 枚举类型.name() + ",value=" + currEnumMap.get(枚举类型) + "]");
		}
	}
}
