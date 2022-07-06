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
		DealLog.log(DealFile.newFile(D_CACHE_PATH + "全部当目录创建.冗余"));
		DealLog.log(DealFile.newFile(new File(D_CACHE_PATH + "全部当目录创建.冗余2")));
		DealLog.log(DealFile.newParentFile(D_CACHE_PATH + "创建父级目录//只创建父级目录.冗余"));
		Assert.assertTrue(DealFile.newParentFile(new File(D_CACHE_PATH + "创建父级目录2//只创建父级目录2.冗余2")));
	}
}