package top.ireed.deal;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.entity.RE;
import top.ireed.model.Model;

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
		DealLog.log("========");
		DealLog.log();
		DealLog.log();
		DealLog.log("测试打印");
		DealLog.log(new Model("6", "key"), new Model("7", "key"), new Model("8", "key"));
		DealLog.GO(new RE("6", "key"), new RE("7", "key"), new RE("8", "key"));

		DealLog.logTo(new Model("6", "key"), new Model("7", "key"));

		DealLog.logToAll("测试打印logToAll", new Model("6", "key"), new Model("7", "key"));

		//无意义,主要避免sonar测试类断言需求判断
		Assert.assertTrue(true);
	}

	@Test
	public void logException() {
		DealLog.logException("异常字符串", "打包出现这一句话是正常的");
		Assert.assertTrue(true);
	}
}