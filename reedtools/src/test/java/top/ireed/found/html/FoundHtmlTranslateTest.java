package top.ireed.found.html;

import junit.framework.TestCase;
import top.ireed.deal.DealIo;
import top.ireed.deal.DealJackson;
import top.ireed.found.dict.FoundDict;
import top.ireed.general.TopException;

import java.io.File;
import java.util.Map;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * date 2022/7/20 18:47
 * mail reedsource@189.cn
 */
public class FoundHtmlTranslateTest extends TestCase {

	public void testName() throws TopException {
		//文件路径
		String file = "D:\\cache\\html\\";
		//翻译后的文件路径
		String backupFile = "D:\\cache\\html\\Translate\\";

		//配置文件位置可以自定义修改
		Map<Object, Object> map = DealJackson.toMap(DealIo.getFileIo(new File("D:\\reed\\reed.json")));
		FoundDict foundDict = new FoundDict(map.get("found_dict_baidu_FoundDictId").toString(), map.get("found_dict_baidu_FoundDictKey").toString(), "jdbc:sqLite:D:\\cache\\data\\FoundDict.db");

		FoundHtmlTranslate foundHtmlTranslate = new FoundHtmlTranslate(file, backupFile, foundDict);
		foundHtmlTranslate.dictHtml();

	}
}