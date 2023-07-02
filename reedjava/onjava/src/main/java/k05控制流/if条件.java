/*
 * FileName: if条件
 * Author:   reedsource
 */
package main.java.k05控制流;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java条件语句〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class if条件 {

    /**
     * if...else
     * <p>
     * 一个 if 语句包含一个布尔表达式和一条或多条语句。
     * <p>
     * 语法
     * if 语句的语法如下：
     * <p>
     * if(布尔表达式)
     * {
     * //如果布尔表达式为true将执行的语句
     * }
     * 如果布尔表达式的值为 true，则执行 if 语句中的代码块，否则执行 if 语句块后面的代码。
     * <p>
     * if 语句后面可以跟 else 语句，当 if 语句的布尔表达式值为 false 时，else 语句块会被执行。
     * if(布尔表达式){
     * //如果布尔表达式的值为true
     * }else{
     * //如果布尔表达式的值为false
     * }
     */
    @Test
    public void c0() {
        int x = 10;

        if (x < 20) {
            DealLog.log("这是 if 语句");
        } else {
            DealLog.log("这是 else 语句");
        }

        //这是 else 语句
    }
}
