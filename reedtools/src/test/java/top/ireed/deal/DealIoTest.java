package top.ireed.deal;

import junit.framework.TestCase;
import org.junit.Assert;
import top.ireed.general.TopException;

import java.io.File;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * date 2022/4/26 23:30
 * reedsource@189.cn
 */
public class DealIoTest extends TestCase {

	public void testToFile() throws TopException {
		DealIo.toFileIo(new File("D:\\cache\\io\\io测试数据.txt"), "测试内容111");
		Assert.assertTrue(true);
	}
}