/*
 * FileName: ParseJavaStructureTest
 * Author:   reedsource
 */
package top.ireed.deal.parse.java;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.deal.DealLog;
import top.ireed.general.TopException;

import java.io.File;

/**
 * 功能简述:
 * 〈ParseJavaStructure测试〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2021-04-01 14:30
 * reedsource@189.cn
 */
public class ParseJavaStructureTest {
	@Test
	public void Test() throws TopException {
		DealLog.log(ParseJavaStructure.javaParse(new File("D:\\codes\\reedsource\\reedtools\\src\\test\\java\\top\\ireed\\deal\\parse\\java\\ParseJavaStructureCase.java")));
		Assert.assertTrue(true);
	}

}
