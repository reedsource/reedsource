package top.ireed.found.words;

import junit.framework.TestCase;
import top.ireed.deal.DealFile;

import java.io.File;

public class FoundDicts01Test extends TestCase {

    public void testDict() {
        File file = new File(DealFile.getUserTestDataFile() + "\\分词\\词库.txt");
        String msg = "分词的目标是将连续的汉字序列切分成有意义的词语，为后续的语义分析和信息处理提供基础。工作的和尚未工作的,羽毛球拍卖完了";
        System.out.println(msg);
        FoundDicts01 s = new FoundDicts01(msg, 3, file);
        System.out.println(s.go());
        System.out.println(s.to());
        System.out.println(s.goTo());
    }


}