package top.ireed.found.dict;

import junit.framework.TestCase;
import org.junit.Assert;
import top.ireed.deal.DealIo;
import top.ireed.deal.DealJackson;
import top.ireed.deal.DealLog;
import top.ireed.general.TopException;

import java.io.File;
import java.util.Map;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * date 2022/7/3 20:30
 * mail reedsource@189.cn
 */
public class FoundDictTest extends TestCase {

	public void testDict() throws TopException {

		//注意 需要先更新配置文件
		Map<Object, Object> map = DealJackson.toMap(DealIo.getFileIo(new File("D:\\codes\\reedsource\\reedtools\\src\\test\\java\\top\\ireed\\data\\reed.json")));

		FoundDict foundDict = new FoundDict(map.get("found_dict_baidu_FoundDictId").toString(), map.get("found_dict_baidu_FoundDictKey").toString());
		DealLog.log(foundDict.dict("reed"));
		Assert.assertEquals("芦苇", foundDict.dict("reed"));
		Assert.assertEquals("芦苇", foundDict.dict("reed", "en", "zh"));
	}

}