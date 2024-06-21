package top.ireed.found.words;

import junit.framework.TestCase;

public class FoundDicts02Test extends TestCase {
    public void testDict() {
        String msg = "分词的目标是将连续的汉字序列切分成有意义的词语，为后续的语义分析和信息处理提供基础。";
        System.out.println(msg);
        FoundDicts02 s = new FoundDicts02();
        System.out.println(s.go(msg, 3));
        System.out.println(s.to(msg, 3));
        System.out.println(s.goTo(msg, 3));
    }

}