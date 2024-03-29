/*
 * FileName: break跳出
 * Author:   reedsource
 */
package main.java.k05控制流;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class break跳出 {
    /**
     * break 跳出关键字
     * <p>
     * break 主要用在循环语句或者 switch 语句中，用来跳出整个语句块。
     * break 跳出最里层的循环，并且继续执行该循环下面的语句。
     */
    @Test
    public void d4() {
        int[] numbers = {10, 20, 30, 40, 50};

        for (int x : numbers) {
            // x 等于 30 时跳出循环
            if (x == 30) {
                break;
            }
            DealLog.log(x);
        }
        DealLog.log();

        //10
        //20

        //无限循环
        int x = 0;
        while (true) {
            DealLog.log(x);
            if (x > 5) {
                break;
            }
            x++;
        }
    }
}
