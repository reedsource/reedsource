/*
 * Author:   reedsource
 */
package k04操作符;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈拼接操作符主要是+  和  +=〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/21 21:56
 * reedsource@189.cn
 */
public class 拼接操作符 {
    @Test
    public void 拼接操作符_Test() {
        int x = 0, y = 1, z = 2;
        String s = "x, y, z ";
        DealLog.log(s + x + y + z);
        // 将x转为字符串:
        DealLog.log(x + " " + s);

        //拼接操作符
        s += "(summed) = ";
        DealLog.log(s + (x + y + z));

        // Integer.toString(): 的简化版
        DealLog.log("" + x);

        //x, y, z 012
        //0 x, y, z
        //x, y, z (summed) = 3
        //0
    }

}
