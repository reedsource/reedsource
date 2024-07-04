package top.ireed.found.words;

import cn.hutool.core.util.ReUtil;
import junit.framework.TestCase;
import top.ireed.deal.DealFile;
import top.ireed.deal.DealIo;
import top.ireed.general.TopException;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class 词库解析工具Test extends TestCase {

    public void testDict1() throws TopException {

        File goFile = new File(DealFile.getUserTestDataFile() + "\\分词\\语料库\\《现代汉语词典》第五版全本.txt");
        File toFile = new File(DealFile.getUserTestDataFile() + "\\分词\\语料库\\现代汉语词典词库.txt");

        StringBuilder stringBuilder = new StringBuilder();
        //读取文件的内容

        String txt = DealIo.getFileIo(goFile);

        List<String> titles2 = ReUtil.findAll("【(.*?)】", txt, 1);
        for (String s : titles2) {
            if (!s.trim().isEmpty()) {
                stringBuilder.append(s);
                stringBuilder.append("\r\n");
            }
        }

        DealIo.toFileIo(toFile, stringBuilder.toString());
    }

    public void testDictAll() throws TopException {

        File goFile = new File(DealFile.getUserTestDataFile() + "\\分词\\语料库\\搜狗标准词库.txt");
        File goFile1 = new File(DealFile.getUserTestDataFile() + "\\分词\\语料库\\现代汉语词典词库.txt");
        File toFile = new File(DealFile.getUserTestDataFile() + "\\分词\\词库.txt");
        StringBuilder stringBuilder = new StringBuilder();
        //读取文件的内容


        String txt1 = DealIo.getFileIo(goFile);

        String[] txt1s = txt1.split("\r\n");
        Set<String> dict = new HashSet<>(Arrays.asList(txt1s));

        String txt2 = DealIo.getFileIo(goFile1);

        String[] txt2s = txt2.split("\r\n");
        dict.addAll(Arrays.asList(txt2s));
        int n;
        for (String s : dict) {
            if (s.length() > 1) {
                n = s.charAt(0);
                //字符不在汉字范围
                if (19968 <= n && n < 40869) {
                    if (!stringBuilder.isEmpty()) {
                        stringBuilder.append("\r\n");
                    }
                    stringBuilder.append(s);
                }
            }
        }
        DealIo.toFileIo(toFile, stringBuilder.toString());
    }


}