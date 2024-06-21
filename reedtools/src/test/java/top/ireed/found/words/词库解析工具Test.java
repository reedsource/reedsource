package top.ireed.found.words;

import cn.hutool.core.io.file.FileWriter;
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


public class 词库解析工具Test extends TestCase {

    public void testDict() {

        File goFile = new File(DealFile.getUserTestDataFile() + "\\分词\\语料库\\搜狗标准词库.txt");
        File toFile = new File(DealFile.getUserTestDataFile() + "\\分词\\词库.txt");

        StringBuilder stringBuilder = new StringBuilder();
        //读取文件的内容

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(goFile.toPath()), StandardCharsets.UTF_16LE), 512 * 1024)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] ss = line.split("\t");
                if (ss.length > 0) {
                    stringBuilder.append(ss[0]);
                    stringBuilder.append("\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //文件保存路径
        FileWriter fileWriter = new FileWriter(toFile);
        //追加写到文件
        fileWriter.append(stringBuilder.toString());
    }

    public void testDict1() throws TopException {

        File goFile = new File(DealFile.getUserTestDataFile() + "\\分词\\语料库\\《现代汉语词典》第五版全本.txt");
        File toFile = new File(DealFile.getUserTestDataFile() + "\\分词\\词库.txt");

        StringBuilder stringBuilder = new StringBuilder();
        //读取文件的内容

        String txt = DealIo.getFileIo(goFile, StandardCharsets.UTF_16LE);

        List<String> titles2 = ReUtil.findAll("【(.*?)】", txt, 1);
        for (String s : titles2) {
            if (!s.trim().isEmpty()) {
                stringBuilder.append(s);
                stringBuilder.append("\r\n");
            }
        }

        //文件保存路径
        FileWriter fileWriter = new FileWriter(toFile);
        //追加写到文件
        fileWriter.append(stringBuilder.toString());
    }
}