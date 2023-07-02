/*
 * FileName: 正则匹配字符串matches和lookingAt
 * Author:   reedsource
 */
package k18字符串.正则表达式;

import org.junit.Test;
import top.ireed.deal.DealLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能简述:
 * 〈正则字符串匹配matches和lookingAt〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/19 13:24
 * reedsource@189.cn
 */
public class 正则匹配字符串matches和lookingAt {
    /**
     * matches 和 lookingAt 方法
     * <p>
     * matches 和 lookingAt 方法都用来尝试匹配一个输入序列模式。
     * 它们的不同是 matches 要求整个序列都匹配，而lookingAt 不要求。
     * lookingAt 方法虽然不需要整句都匹配，但是需要从第一个字符开始匹配。
     * 这两个方法经常在输入字符串的开始使用。
     */
    @Test
    public void 正则匹配字符串matches和lookingAt_Test() {
        final String REGEX = "foo";
        final String INPUT = "fooooooooooooooooo";
        final String INPUT2 = "ooooofoooooooooooo";
        Pattern pattern;
        Matcher matcher;
        Matcher matcher2;

        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(INPUT);
        matcher2 = pattern.matcher(INPUT2);

        DealLog.log("Current REGEX is: " + REGEX);
        DealLog.log("Current INPUT is: " + INPUT);
        DealLog.log("Current INPUT2 is: " + INPUT2);


        DealLog.log("lookingAt(): " + matcher.lookingAt());
        DealLog.log("matches(): " + matcher.matches());
        DealLog.log("lookingAt(): " + matcher2.lookingAt());

        //Current REGEX is: foo
        //Current INPUT is: fooooooooooooooooo
        //Current INPUT2 is: ooooofoooooooooooo
        //lookingAt(): true
        //matches(): false
        //lookingAt(): false
    }
}
