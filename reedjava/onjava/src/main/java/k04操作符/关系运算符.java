/*
 * FileName: 关系运算符
 * Author:   reedsource
 */
package main.java.k04操作符;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java运算符〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class 关系运算符 {

    /**
     * 关系运算符
     * <p>
     * ==	检查如果两个操作数的值是否相等，如果相等则条件为真。	（A == B）为假。
     * !=	检查如果两个操作数的值是否相等，如果值不相等则条件为真。	(A != B) 为真。
     * > 	检查左操作数的值是否大于右操作数的值，如果是那么条件为真。	（A> B）为假。
     * < 	检查左操作数的值是否小于右操作数的值，如果是那么条件为真。	（A <B）为真。
     * >=	检查左操作数的值是否大于或等于右操作数的值，如果是那么条件为真。	（A> = B）为假。
     * <=	检查左操作数的值是否小于或等于右操作数的值，如果是那么条件为真。	（A <= B）为真。
     */
    @Test
    public void o3() {
        int a = 10;
        int b = 20;
        DealLog.log("a == b = " + (a == b));
        DealLog.log("a != b = " + (a != b));
        DealLog.log("a > b = " + (a > b));
        DealLog.log("a < b = " + (a < b));
        DealLog.log("b >= a = " + (b >= a));
        DealLog.log("b <= a = " + (b <= a));

        //a == b = false
        //a != b = true
        //a > b = false
        //a < b = true
        //b >= a = true
        //b <= a = false
    }

}
