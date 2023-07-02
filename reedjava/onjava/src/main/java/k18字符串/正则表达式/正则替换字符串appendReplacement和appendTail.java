/*
 * FileName: 正则替换字符串appendReplacement和appendTail
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
 * date 2022/5/19 13:36
 * reedsource@189.cn
 */
public class 正则替换字符串appendReplacement和appendTail {
    /**
     * appendReplacement 和 appendTail 方法
     * <p>
     * Matcher 类也提供了appendReplacement 和 appendTail 方法用于文本替换：
     */
    @Test
    public void 正则替换字符串appendReplacement和appendTail_Test() {
        String REGEX = "a*b";
        String INPUT = "aabfooaabfooabfoobkkk";
        String REPLACE = "-";

        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, REPLACE);
        }
        m.appendTail(sb);
        DealLog.log(sb.toString());

        //-foo-foo-foo-kkk
    }
}
