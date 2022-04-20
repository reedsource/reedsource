package top.ireed.deal;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * 功能简述:
 * 〈${DESCRIPTION}〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/9/10 18:27
 * reedsource@189.cn
 */
public class DealFileTest {

	private static final String D_CACHE_PATH = "D:\\cache\\path\\";

	@Test
	public void getFile() {
		Assert.assertTrue(DealFile.getFile(D_CACHE_PATH + "path1.txt"));
	}

	@Test
	public void getFile1() {
		Assert.assertTrue(DealFile.getFile(new File(D_CACHE_PATH + "path2.txt")));
	}
}