/*
 * FileName: BigDecimal03Compare
 * Author:   reedsource
 */
package k03对象.包装类.精确计算BigDecimal.BigDecimal比较;

import top.ireed.deal.DealLog;

import java.math.BigDecimal;

/**
 * 功能简述:
 * 〈比较〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class BigDecimal03Compare {
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal("100");
		BigDecimal b = new BigDecimal("200");
		//-1,表示 a 小于 b
		//0,表示 a 等于 b
		//1,表示 a 大于 b
		DealLog.log(a.compareTo(b));

		//判断是否为空
		if (a != null) {
			DealLog.log(a + "不为空");
		}
	}
}
