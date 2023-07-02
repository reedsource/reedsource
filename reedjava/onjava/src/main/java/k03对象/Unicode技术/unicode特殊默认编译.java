/*
 * FileName: unicode特殊默认编译
 * Author:   reedsource
 */
package k03对象.Unicode技术;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java可以被执行的注释,注释相关的技术
 * java会先执行编译Unicode编码
 * 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class unicode特殊默认编译 {
    /**
     * \u000d Unicode换行符
     * jvm编译器会编译Unicode字符
     */
    @Test
    public void unicode特殊默认编译_Test() {
        String a = "我是目标输出";
        //
        a = "我不是目标输出";
        DealLog.log(a);

        //
        a = c(a);
        DealLog.log(a);
    }

    public static String c(String m) {
        return "我是异常的目标输入";
    }
}
