/*
 * Author:   reedsource
 */
package k05控制流;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/22 11:06
 * reedsource@189.cn
 */
public class return返回 {

    @Test
    public void return返回_Test() {
        DealLog.log(返回());
    }


    public String 返回() {
        //标明了返回类型 就必须返回对应的类型
        return "返回字符串";
        //return后面的内容不会执行了
    }
}
