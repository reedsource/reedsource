/*
 * FileName: doWhile循环
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
public class doWhile循环 {
    /**
     * do…while 循环
     * <p>
     * 对于 while 语句而言，如果不满足条件，则不能进入循环。
     * 但有时候我们需要即使不满足条件，也至少执行一次。
     * <p>
     * do…while 循环和 while 循环相似，不同的是，do…while 循环至少会执行一次
     * <p>
     * do {
     * //代码语句
     * }while(布尔表达式);
     * <p>
     * 注意：布尔表达式在循环体的后面，所以语句块在检测布尔表达式之前已经执行了。
     * 如果布尔表达式的值为 true，则语句块一直执行，直到布尔表达式的值为 false
     */
    @Test
    public void d1() {
        int x = 10;

        do {
            DealLog.log("value of x : " + x);
            x++;
        } while (x < 20);

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
