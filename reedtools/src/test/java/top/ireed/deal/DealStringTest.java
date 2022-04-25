package top.ireed.deal;

import org.junit.Assert;
import org.junit.Test;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * date 2022/4/21 18:02
 * mail reedsource@189.cn
 */
public class DealStringTest {

	@Test
	public void test() {
		Assert.assertEquals("浣犲ソ", DealString.convertCode("你好","UTF-8","GBK"));
		Assert.assertEquals("测&lt&gt&nbsp&acute试", DealString.replaceHtmlErrorString("测<> \\试"));
		Assert.assertEquals("测''''''试", DealString.replaceString("测'''试"));
		Assert.assertEquals("￥1,000,000.00", DealString.formatMoneyString(1000000));
		Assert.assertEquals("1,000,000.0000", DealString.formatString(1000000));
		Assert.assertEquals(2, DealString.inverted("1000000",'0',5));
		Assert.assertEquals("1000000", DealString.jy(DealString.ys("1000000")));
	}
}