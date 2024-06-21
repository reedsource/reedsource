package top.ireed.found.words;

import top.ireed.deal.DealFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 分词工具类 迭代简单实现
 * 迭代实现分词
 */
public class FoundDicts01 {

    /**
     * 正向匹配结果
     */
    String goResult;
    /**
     * 反向匹配结果
     */
    String toResult;
    /**
     * 需要分词的句子
     */
    String sentence;
    /**
     * 最大分词 词长度
     */
    int maxLen;
    /**
     * 尝试分词的词长度
     */
    int dictLen;
    /**
     * 分词开始位置
     */
    int indexpos;
    Set<String> dict = new HashSet<>();

    /**
     * @param inputstr 需要分词的句子
     * @param maxlen   最大分词 词长度
     */
    public FoundDicts01(String inputstr, int maxlen) {

        initDict();

        sentence = inputstr;
        maxLen = maxlen;
        dictLen = maxLen;
        indexpos = 0;
        goResult = "";
        toResult = "";

    }

    /**
     * @return 正反匹配组合
     */
    public String goTo() {
        String goMsg = go();
        String toMsg = to();
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
    public void initDict() {
        try {
            File file = new File(DealFile.getUserTestDataFile() + "\\词库.txt");
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
     */
    public String go() {
        goResult = "";
        go(sentence, maxLen, 0);
        return goResult;
    }

    /**
     * 正向最大匹配算法
     *
     * @param str     分词字符串
     * @param dictLen 尝试匹配的词的长度
     * @param left    匹配开始位置
     */
    public void go(String str, int dictLen, int left) {
        //匹配开始位置大于分词字符串总长度 无法分词
        if (left + 1 > str.length()) {
            return;
        }
        String curstr;
        //剩余需要分词字符串长度
        int beLeft = str.length() - left;
        //获取词
        if (beLeft <= dictLen) {
            curstr = str.substring(left, left + beLeft);
        } else {
            curstr = str.substring(left, left + dictLen);
        }

        //字典中能查到分词
        if (dict.contains(curstr)) {
            goResult = goResult + curstr + " ";
            this.dictLen = maxLen;
            indexpos = left + dictLen;
            go(str, this.dictLen, indexpos);
        } else {
            if (this.dictLen > 1) {
                this.dictLen = this.dictLen - 1;
            } else {
                goResult = goResult + curstr + " ";
                left = left + 1;
                this.dictLen = maxLen;
            }
            go(str, this.dictLen, left);
        }
    }

    /**
     * 反向最大匹配算法
     */
    public String to() {
        toResult = "";
        to(sentence, maxLen, sentence.length() - 1);
        return toResult;

    }

    /**
     * 反向最大匹配算法
     *
     * @param str     分词字符串
     * @param dictLen 尝试匹配的词的长度
     * @param right   匹配结束位置
     */
    public void to(String str, int dictLen, int right) {
        //反向匹配结束位置小于0 无法分词
        if (right < 0) {
            return;
        }
        String curstr;
        //根据词长度截取词
        if (right - dictLen + 1 >= 0) {
            curstr = str.substring(right - dictLen + 1, right + 1);
        } else {
            curstr = str.substring(0, right + 1);//到达句首
        }

        if (dict.contains(curstr)) {
            toResult = curstr + " " + toResult;
            this.dictLen = maxLen;
            indexpos = right - dictLen;
            to(str, this.dictLen, indexpos);
        } else {
            if (this.dictLen > 1) {
                this.dictLen = this.dictLen - 1;
            } else {
                toResult = curstr + " " + toResult;
                right = right - 1;
                this.dictLen = maxLen;
            }
            to(str, this.dictLen, right);
        }
    }
}