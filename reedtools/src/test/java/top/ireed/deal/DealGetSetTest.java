package top.ireed.deal;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.model.Model;

/**
 * 功能简述:
 * 〈${DESCRIPTION}〉
 *
 * @author reedsource
 * create 2020/11/17 11:10
 * mailbox reedsource@189.cn
 */
public class DealGetSetTest {

	@Test
	public void getMethod1() {
		Model model = new Model();
		DealGetSet.getMethod(model, "sa");
		Assert.assertTrue(true);
	}
}