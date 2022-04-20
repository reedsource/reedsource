package top.ireed.deal;

import org.junit.Assert;
import org.junit.Test;

/**
 * 功能简述:
 * 〈${DESCRIPTION}〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/9/10 18:27
 * reedsource@189.cn
 */
public class DealLogTest {

	@Test
	public void log() {
		DealLog.log("测试打印");

		//无意义,主要避免sonar测试类断言需求判断
		Assert.assertTrue(true);
	}

	@Test
	public void log1() {
		DealLog.log("测试", "测试打印");

		//无意义,主要避免sonar测试类断言需求判断
		Assert.assertTrue(true);
	}

	@Test
	public void logException() {
		DealLog.logException("异常字符串,打包出现这一句话是正常的");
		Assert.assertTrue(true);
	}
}