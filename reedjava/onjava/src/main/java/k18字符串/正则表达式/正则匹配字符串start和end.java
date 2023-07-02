/*
 * FileName: 正则匹配字符串start和end
 * Author:   reedsource
 */
package main.java.k18字符串.正则表达式;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/19 13:19
 * reedsource@189.cn
 */
public class 正则匹配字符串start和end {
    /**
     * start 和 end 方法
     * <p>
     * 对单词 "cat" 出现在输入字符串中出现次数进行计数的例子
     */
    @Test
    public void 正则匹配字符串start和end_Test() {
        String REGEX = "\\bcat\\b";
        String INPUT = "cat cat cat cattie cat";

        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
        int count = 0;

        while (m.find()) {
            count++;
            DealLog.log("Match number " + count);
            DealLog.log("start(): " + m.start());
            DealLog.log("end(): " + m.end());
        }

        //Match number 1
        //start(): 0
        //end(): 3
        //Match number 2
        //start(): 4
        //end(): 7
        //Match number 3
        //start(): 8
        //end(): 11
        //Match number 4
        //start(): 19
        //end(): 22

        //可以看到这个例子是使用单词边界，以确保字母 "c" "a" "t" 并非仅是一个较长的词的子串。
        //它也提供了一些关于输入字符串中匹配发生位置的有用信息。
        //Start 方法返回在以前的匹配操作期间，由给定组所捕获的子序列的初始索引，end 方法最后一个匹配字符的索引加 1。
    }
}
