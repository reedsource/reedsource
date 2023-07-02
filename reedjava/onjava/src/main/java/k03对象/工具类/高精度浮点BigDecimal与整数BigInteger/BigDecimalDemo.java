/*
 * Author:   reedsource
 */
package main.java.k03对象.工具类.高精度浮点BigDecimal与整数BigInteger;

import top.ireed.deal.DealLog;

import java.math.BigDecimal;

/**
 * 功能简述:
 * 〈不可变的有符号的任意精度的十进制数（浮点数）
 * 测试BigDecimal类的一些函数 〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/13 23:32
 * reedsource@189.cn
 */
public class BigDecimalDemo {
    public static void main(String[] arguments) {
        DealLog.log("构造两个BigDecimal对象");
        //用char[]数组创建BigDecimal对象,第二个参数为位移offset,
        //第三个参数指定长度 截取字符串一部分
        BigDecimal bd1 = new BigDecimal("3464656776868432998434".toCharArray(), 2, 15);
        DealLog.log("bd1 = " + bd1);
        //bd1 = 646567768684329

        //创建BigDecimal对象
        BigDecimal bd2 = new BigDecimal("134258767575867.0");
        DealLog.log("bd2 = " + bd2);
        //bd2 = 134258765070336

        //加
        DealLog.log("bd1 + bd2 = " + bd1.add(bd2));
        //减
        DealLog.log("bd1 - bd2 = " + bd1.subtract(bd2));
        //乘
        DealLog.log("bd1 * bd2 = " + bd1.multiply(bd2));
        //指数运算
        DealLog.log("bd1的2次方 = " + bd1.pow(2));
        //取商的整数部分
        DealLog.log("bd1/bd2的整数商: " + bd1.divideToIntegralValue(bd2));
        //返回余数计算为:this.subtract(this.divideToIntegralValue(divisor).multiply(divisor))
        //DealLog.log(bd1.subtract(bd1.divideToIntegralValue(bd2).multiply(bd2)));
        DealLog.log("bd1/bd2的余数: " + bd1.remainder(bd2));
        //取商和余,即bd1.divideToIntegralValue(bd2)与bd1.remainder(bd2)
        DealLog.log("bd1 / bd2 = " + bd1.divideAndRemainder(bd2)[0] +
                "--" + bd1.divideAndRemainder(bd2)[1]);
        //比较大小,也可以用max()和min()
        if (bd1.compareTo(bd2) > 0)

            DealLog.log("bd1 大于bd2");

        else if (bd1.compareTo(bd2) == 0)

            DealLog.log("bd1 等于 bd2");

        else if (bd1.compareTo(bd2) < 0)

            DealLog.log("bd1 小于 bd2");
        //末位数据精度
        DealLog.log("bd1的末位数据精度:  " + bd1.ulp());
    }
}
