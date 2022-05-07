package top.ireed.deal;

import junit.framework.TestCase;
import org.junit.Assert;

import java.io.File;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * date 2022/4/26 23:30
 * reedsource@189.cn
 */
public class DealIoTest extends TestCase {

	public void testToFile() {
		DealIo.toFileIo(new File("D:\\cache\\io\\io测试数据.txt"), "测试内容111");
		Assert.assertTrue(true);
	}
}