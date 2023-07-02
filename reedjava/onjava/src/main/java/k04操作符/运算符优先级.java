/*
 * FileName: 运算符优先级
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
public class 运算符优先级 {

    /**
     * Java运算符优先级
     * <p>
     * 当多个运算符出现在一个表达式中，谁先谁后呢？这就涉及到运算符的优先级别的问题。在一个多运算符的表达式中，运算符优先级不同会导致最后得出的结果差别甚大。
     * <p>
     * 例如，（1+3）＋（3+2）*2，这个表达式如果按加号最优先计算，答案就是 18，如果按照乘号最优先，答案则是 14。
     * 再如，x = 7 + 3 * 2;这里x得到13，而不是20，因为乘法运算符比加法运算符有较高的优先级，所以先计算3 * 2得到6，然后再加7。
     * <p>
     * 后缀	    () [] . (点操作符)	    左到右
     * 一元	    + + - ！〜	            从右到左
     * 乘性 	    * /％	                左到右
     * 加性 	    + -	                    左到右
     * 移位 	    >> >>>  << 	            左到右
     * 关系 	    >> = << = 	            左到右
     * 相等 	    ==  !=	                左到右
     * 按位与	    ＆	                左到右
     * 按位异或	^	                    左到右
     * 按位或	    |	                左到右
     * 逻辑与	    &&	                左到右
     * 逻辑或	    | |	                左到右
     * 条件	    ？：	                    从右到左
     * 赋值	    = + = - = * = / =％= >> = << =＆= ^ = | =	从右到左
     * 逗号	    ，	                    左到右
     */
    @Test
    public void o1() {
        int x = 1, y = 2, z = 3;
        int a = x + y - 2 / 2 + z;           // [1]
        int b = x + (y - 2) / (2 + z);       // [2]
        DealLog.log("a = " + a);
        DealLog.log("b = " + b);
    }
}
