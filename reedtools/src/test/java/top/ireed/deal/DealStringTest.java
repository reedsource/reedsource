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
		Assert.assertEquals("1,000,000.0000", DealString.formatString(1000000));
	}

}