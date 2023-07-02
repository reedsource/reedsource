/*
 * FileName: 正则匹配组分割
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
 * date 2022/5/19 13:33
 * reedsource@189.cn
 */
public class 正则匹配组分割 {
    /**
     * 捕获组  多个字符当一个单独单元进行正则处理
     * <p>
     * 索引方法提供了有用的索引值，精确表明输入字符串中在哪能找到匹配：
     * 1	public int start()
     * 返回以前匹配的初始索引。
     * 2	public int start(int group)
     * 返回在以前的匹配操作期间，由给定组所捕获的子序列的初始索引
     * 3	public int end()
     * 返回最后匹配字符之后的偏移量。
     * 4	public int end(int group)
     * 返回在以前的匹配操作期间，由给定组所捕获子序列的最后字符之后的偏移量。
     * <p>
     * 研究方法用来检查输入字符串并返回一个布尔值，表示是否找到该模式：
     * 序号	方法及说明
     * 1	public boolean lookingAt()
     * 尝试将从区域开头开始的输入序列与该模式匹配。
     * 2	public boolean find()
     * 尝试查找与该模式匹配的输入序列的下一个子序列。
     * 3	public boolean find(int start）
     * 重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列。
     * 4	public boolean matches()
     * 尝试将整个区域与模式匹配。
     * <p>
     * 替换方法是替换输入字符串里文本的方法：
     * 1	public Matcher appendReplacement(StringBuffer sb, String replacement)
     * 实现非终端添加和替换步骤。
     * 2	public StringBuffer appendTail(StringBuffer sb)
     * 实现终端添加和替换步骤。
     * 3	public String replaceAll(String replacement)
     * 替换模式与给定替换字符串相匹配的输入序列的每个子序列。
     * 4	public String replaceFirst(String replacement)
     * 替换模式与给定替换字符串匹配的输入序列的第一个子序列。
     * 5	public static String quoteReplacement(String s)
     * 返回指定字符串的字面替换字符串。
     * 这个方法返回一个字符串，就像传递给Matcher类的appendReplacement 方法一个字面字符串一样工作。
     */
    @Test
    public void 正则匹配组分割_Test() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        //非数字-数字-其他 匹配
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        // Matcher 对象是对输入字符串进行解释和匹配操作的引擎。
        // 与Pattern 类一样，Matcher 也没有公共构造方法。
        // 你需要调用 Pattern 对象的 matcher 方法来获得一个 Matcher 对象。
        // 可以通过调用 matcher 对象的 groupCount 方法来查看表达式有多少个分组。
        // groupCount 方法返回一个 int 值，表示matcher对象当前有多个捕获组。

        // 该组不包括在 groupCount 的返回值中
        Matcher m = r.matcher(line);
        if (m.find()) {
            // 还有一个特殊的组（group(0)），它总是代表整个表达式。
            DealLog.log("发现值: " + m.group(0));
            DealLog.log("发现值: " + m.group(1));
            DealLog.log("发现值: " + m.group(2));
            DealLog.log("发现值: " + m.group(3));
        } else {
            DealLog.log("没有找到");
        }
        //发现值: This order was placed for QT3000! OK?
        //发现值: This order was placed for QT
        //发现值: 3000
        //发现值: ! OK?
    }

}
