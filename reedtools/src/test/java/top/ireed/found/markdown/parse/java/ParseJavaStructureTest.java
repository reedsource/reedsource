/*
 * FileName: ParseJavaStructureTest
 * Author:   reedsource
 */
package top.ireed.found.markdown.parse.java;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.deal.DealLog;
import top.ireed.found.markdown.parse.ParseJavaStructure;
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
		DealLog.log(ParseJavaStructure.javaParse(new File("D:\\clouds\\codes\\reedsource\\reedtools\\src\\main\\java\\top\\ireed\\found\\FoundSqlite.java")));
		Assert.assertTrue(true);
	}

}
