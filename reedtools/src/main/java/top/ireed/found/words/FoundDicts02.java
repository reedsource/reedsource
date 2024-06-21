package top.ireed.found.words;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 分词工具类 游标简单实现
 * 游标实现分词
 */
public class FoundDicts02 {

    Set<String> dict = new HashSet<>();

    /**
     * 构造函数
     */
    public FoundDicts02(File file) {
        initDict(file);
    }

    /**
     * 正反匹配组合
     *
     * @param str        分词字符串
     * @param dictMaxLen 尝试匹配的词的最大长度
     */
    public String goTo(String str, int dictMaxLen) {
        String goMsg = go(str, dictMaxLen);
        String toMsg = to(str, dictMaxLen);
        if (goMsg.equals(toMsg)) {
            return "双向匹配相同，结果为：" + goMsg;
        } else {
            List<String> goMsgList = Arrays.asList(goMsg.trim().split(" "));
            List<String> toMsgList = Arrays.asList(toMsg.trim().split(" "));

            //取最小分组数量为最佳结果
            if (goMsgList.size() >= toMsgList.size()) {
                return "双向匹配不同，最佳结果为 反向：\r\n" + toMsg;
            } else {
                return "双向匹配不同，最佳结果为 正向：\r\n" + goMsg;
            }
        }
    }

    /**
     * 初始化词典
     */
    public void initDict(File file) {
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
    }

    /**
     * 正向最大匹配算法
     *
     * @param str        分词字符串
     * @param dictMaxLen 尝试匹配的词的最大长度
     */
    public String go(String str, int dictMaxLen) {

        StringBuilder goResult = new StringBuilder();

        //匹配开始位置
        int left = 0;

        //尝试分词的词长度
        int dictLen;

        //起始位置小于字符串总长度 继续分词
        while (left < str.length()) {
            //尝试分词长度 初始化
            dictLen = dictMaxLen;

            //分词 词长度 > 0
            while (dictLen > 0) {
                //分词结果
                String curstr;
                //剩余需要分词字符串长度
                int beLeft = str.length() - left;
                //剩余需要分词字符串长度 <= 尝试分词长度   使用 剩余需要分词字符串长度 分词
                if (beLeft <= dictLen) {
                    curstr = str.substring(left, left + beLeft);
                } else {
                    curstr = str.substring(left, left + dictLen);
                }

                //字典中能查到分词
                if (dict.contains(curstr) || dictLen == 1) {
                    goResult.append(curstr).append(" ");
                    left = left + dictLen;
                    break;
                }
                //未匹配到,尝试降低词长度,再次匹配
                dictLen--;
            }
        }

        return goResult.toString();
    }

    /**
     * 反向最大匹配算法
     *
     * @param str        分词字符串
     * @param dictMaxLen 尝试匹配的词的最大长度
     */
    public String to(String str, int dictMaxLen) {
        StringBuilder toResult = new StringBuilder();

        //匹配开始位置
        int right = str.length() - 1;

        //尝试分词的词长度
        int dictLen;

        //反向匹配结束位置大于-1 继续分词
        while (right > -1) {
            //尝试分词长度 初始化
            dictLen = dictMaxLen;

            //分词 词长度 > 0
            while (dictLen > 0) {

                String curstr;
                //根据词长度截取词
                if (right - dictLen + 1 >= 0) {
                    curstr = str.substring(right - dictLen + 1, right + 1);
                } else {
                    curstr = str.substring(0, right + 1);//到达句首
                }

                if (dict.contains(curstr) || dictLen == 1) {
                    toResult.insert(0, curstr + " ");
                    right = right - dictLen;
                    break;
                }

                //未匹配到,尝试降低词长度,再次匹配
                dictLen--;
            }
        }
        return toResult.toString();
    }
}