/*
 * FileName: for循环
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
public class for循环 {
    /**
     * for循环
     * <p>
     * for循环执行的次数是在执行前就确定的。语法格式如下：
     * <p>
     * for(初始化; 布尔表达式; 更新) {
     * //代码语句
     * }
     * 关于 for 循环有以下几点说明：
     * <p>
     * 最先执行初始化步骤。可以声明一种类型，但可初始化一个或多个循环控制变量，也可以是空语句。
     * 然后，检测布尔表达式的值。
     * 如果为 true，循环体被执行。如果为false，循环终止，开始执行循环体后面的语句。
     * 执行一次循环后，更新循环控制变量。
     * 再次检测布尔表达式。循环执行上面的过程
     */
    @Test
    public void d2() {
        for (int x = 10; x < 20; x = x + 1) {
            DealLog.log("value of x : " + x);
        }
        DealLog.log();
        //for的变量部分可以放到方法里面
        for (int x = 10; x < 20; ) {
            DealLog.log("value of x : " + x);
            ++x;
        }

        //value of x : 10
        //value of x : 11
        //value of x : 12
        //value of x : 13
        //value of x : 14
        //value of x : 15
        //value of x : 16
        //value of x : 17
        //value of x : 18
        //value of x : 19
    }
}
