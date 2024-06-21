package top.ireed.found.words;


import cn.hutool.core.io.file.FileWriter;
import top.ireed.general.TopException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * 功能简述:
 * 〈爬取网页数据 正则获取额定数据〉
 */
public class 词库解析工具 {
    public static void main(String[] args) throws TopException, IOException {

        //文件保存路径
        FileWriter fileWriter = new FileWriter("D:\\cache\\词库.txt");
        File file = new File("D:\\可信语料库\\搜狗标准词库.txt");


        StringBuilder stringBuilder = new StringBuilder();
        //读取文件的内容

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_16LE), 512 * 1024)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] ss = line.split("\t");
                if (ss.length > 0) {
                    stringBuilder.append(ss[0]);
                    stringBuilder.append("\r\n");
                }
            }
        }

        //追加写到文件
        fileWriter.append(stringBuilder.toString());
    }
}
