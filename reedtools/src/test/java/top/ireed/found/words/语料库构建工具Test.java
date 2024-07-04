package top.ireed.found.words;

import cn.hutool.core.util.ReUtil;
import junit.framework.TestCase;
import top.ireed.deal.DealFile;
import top.ireed.deal.DealIo;
import top.ireed.general.TopException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class 语料库构建工具Test extends TestCase {

    public static final String UTF8_BOM = "\uFEFF";

    /**
     * 去除多余空行 另见 空【
     */
    public void testDict0() throws TopException {

        File goFile = new File(DealFile.getUserTestDataFile() + "\\分词\\语料库\\《现代汉语词典》第五版全本.txt");
        File toFile = new File(DealFile.getUserTestDataFile() + "\\分词\\语料库\\现代汉语词典第五版.txt");

        StringBuilder stringBuilder = new StringBuilder();
        //读取文件的内容

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(goFile.toPath()), StandardCharsets.UTF_8), 512 * 1024)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();

                //去除开头 zero-width no-break space (ZWNBSP)"的字符, 以 “\UFEFF”作为字符串的开头
                if (line.startsWith(UTF8_BOM)) {
                    line = line.substring(1);
                }
                if (line.length() > 2) {
                    //只保留 开头是【
                    //开头是 换行 否则 不换行
                    if ("【".equals(line.substring(0, 1))) {
                        line = line.replaceAll(" ", "");
                        line = line.replaceAll("【", "");
                        line = line.replaceAll("】", "|");

                        String[] lines = line.split("|");
                        line = line.replaceAll("～", lines[0]);

                        //去除单字()
                        List<String> titles2 = ReUtil.findAll("（(.*?)）", line, 1);
                        for (String s : titles2) {
                            if (s.length() == 1) {
                                line = line.replaceAll("（" + s + "）", "");
                            }
                        }

                        stringBuilder.append(line);
                        stringBuilder.append("\r\n");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DealIo.toFileIo(toFile, stringBuilder.toString());
    }


}