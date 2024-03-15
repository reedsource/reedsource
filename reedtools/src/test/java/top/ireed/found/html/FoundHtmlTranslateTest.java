package top.ireed.found.html;

import junit.framework.TestCase;
import top.ireed.deal.DealFile;
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
        String file = DealFile.getUserTestCacheFile() + "\\html\\";
        //翻译后的文件路径
        String backupFile = DealFile.getUserTestCacheFile() + "\\html1\\";

        //配置文件位置可以自定义修改
        Map<Object, Object> map = DealJackson.toMap(DealIo.getFileIo(new File(DealFile.getUserTestDataFile() + "\\ireed.json")));
        FoundDict foundDict = new FoundDict(map.get("found_dict_baidu_FoundDictId").toString(), map.get("found_dict_baidu_FoundDictKey").toString(), "jdbc:sqLite:" + DealFile.getUserTestCacheFile() + "\\FoundDict.db");
        FoundHtmlTranslate foundHtmlTranslate = new FoundHtmlTranslate(file, backupFile, foundDict);
        foundHtmlTranslate.dictHtml();

    }
}