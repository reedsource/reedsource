/*
 * Author:   reedsource
 */
package k04操作符;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈窄化转型时需要注意截尾和舍入问题〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/22 9:57
 * reedsource@189.cn
 */
public class 类型转换截尾与舍入 {

    @Test
    public void 类型转换截尾与舍入_Test() {
        //double和float转型为整数类型时,默认总会对数值截尾
        double a = 0.7, b = 0.4;
        float f1 = 0.7f, f2 = 0.4f;
        DealLog.log("(int)a: " + (int) a);
        DealLog.log("(int)b: " + (int) b);
        DealLog.log("(int)f1: " + (int) f1);
        DealLog.log("(int)f2: " + (int) f2);
        //(int)a: 0
        //(int)b: 0
        //(int)f1: 0
        //(int)f2: 0

        //对数据结果进行舍入
        DealLog.log("Math.round(a): " + Math.round(a));
        DealLog.log("Math.round(b): " + Math.round(b));
        DealLog.log("Math.round(f1): " + Math.round(f1));
        DealLog.log("Math.round(f2): " + Math.round(f2));
        //Math.round(a): 1
        //Math.round(b): 0
        //Math.round(f1): 1
        //Math.round(f2): 0

        //注意 Math.round 在负数的时候 -1.5的时是-1  倒数0.9 0.8 0.7 0.6 0.5是第五个
        DealLog.log("Math.round(-0.5): " + Math.round(-1.5));

        //Math.ceil() 向上取整
        DealLog.log("Math.ceil(1.1: " + Math.ceil(1.1));
        DealLog.log("Math.ceil(1.5: " + Math.ceil(1.5));
        DealLog.log("Math.ceil(1.6): " + Math.ceil(1.6));
        DealLog.log("Math.ceil(-1.1): " + Math.ceil(-1.1));
        DealLog.log("Math.ceil(-1.5): " + Math.ceil(-1.5));
        DealLog.log("Math.ceil(-1.6): " + Math.ceil(-1.6));
        //Math.ceil(1.1: 2.0
        //Math.ceil(1.5: 2.0
        //Math.ceil(1.6): 2.0
        //Math.ceil(-1.1): -1.0
        //Math.ceil(-1.5): -1.0
        //Math.ceil(-1.6): -1.0


    }


}
