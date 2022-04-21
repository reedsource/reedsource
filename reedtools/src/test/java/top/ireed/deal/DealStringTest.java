package top.ireed.deal;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import top.ireed.model.Model;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * date 2022/4/21 18:02
 * mail reedsource@189.cn
 */
public class DealStringTest extends TestCase {

	@Test
	public void test() {
		Assert.assertEquals("1,000,000.0000", DealString.formatString(1000000));
	}

}