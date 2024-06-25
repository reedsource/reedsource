package top.ireed.found.words;

import top.ireed.deal.DealFile;
import top.ireed.found.FoundSqlite;
import top.ireed.general.TopException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * 初始化及入库程序
 */
public class FoundDictsInit {

    static Set<String> dict = new HashSet<>();

    public static void initData() throws TopException {
        File file = new File(DealFile.getUserTestDataFile() + "\\分词\\词库.txt");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                dict.add(line.trim());
            }
            br.close();
        } catch (IOException e) {
            System.out.println("初始化词典异常");
        }

        // 1. 约定 连接一个数据库
        FoundSqlite fSqlite = new FoundSqlite("jdbc:sqLite:" + DealFile.getUserTestDataFile() + "\\分词\\DealSqliteWords.db");

        // 2. 约定 根据实体类初始化一个数据表 数据库正常情况 将不会做任何操作
        //约定 主键名称为 id  自增
        boolean m = fSqlite.init(new Word());

        // 3. 约定 表数据插入方式
        //初始化时会执行的操作

        for (String s : dict) {
            fSqlite.insert(new Word(s));
        }
    }
}
