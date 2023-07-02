/*
 * FileName: for循环小案例
 * Author:   reedsource
 */
package main.java.k05控制流;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java循环案例〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class for循环小案例 {
    public static void main(String[] args) {
        c0();
        c1();
        c2();
    }

    /**
     * 99乘法表
     */
    private static void c0() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                DealLog.log(j + "*" + i + "=" + i * j + " ");
            }
            DealLog.log("");
        }

        //1*1=1
        //1*2=2 2*2=4
        //1*3=3 2*3=6 3*3=9
        //1*4=4 2*4=8 3*4=12 4*4=16
        //1*5=5 2*5=10 3*5=15 4*5=20 5*5=25
        //1*6=6 2*6=12 3*6=18 4*6=24 5*6=30 6*6=36
        //1*7=7 2*7=14 3*7=21 4*7=28 5*7=35 6*7=42 7*7=49
        //1*8=8 2*8=16 3*8=24 4*8=32 5*8=40 6*8=48 7*8=56 8*8=64
        //1*9=9 2*9=18 3*9=27 4*9=36 5*9=45 6*9=54 7*9=63 8*9=72 9*9=81
    }

    /**
     * for循环打印一个菱形
     * <p>
     * 菱形为上下两个等腰三角形（用两个外层for循环分别代表上下两部分）
     * 上部分为倒直角三角形 + 等腰三角形。
     * 外层for以要嵌套两个for循环分别打印出倒直角三角形（直角三角形打印空格）和等腰三角形
     * 下部分为直角三角形和倒等腰三角形。
     * 外层for同样要嵌套两个for循环打印直角三角形（直角三角形打印空格）和倒的等腰三角形
     */
    private static void c1() {
        //定义变量
        int i, j, q;
        //打印菱形的上半部分，左边打印倒直角三角形的空格，右边打印等腰三角形
        for (i = 1; i < 6; i++) //外循环控制行数，共打印五行
        {
            //左边打印倒直角三角形空格
            for (j = 5; j > i; j--) //与外循环关联，初始值不变，表达式变化，控制打印的列数
            {
                DealLog.log(" ");
            }
            //右边等腰三角形
            for (q = 1; q < i * 2; q++) //与外循环关联，初始值不变，表达式变化，打印奇数列1，3，5，7，9
            {
                DealLog.log("*");
            }
            DealLog.log("\r\n");    //换行
        }
        //打印菱形的下半部分，左边为直角三角形的空格，右边为倒的等腰三角形
        for (i = 1; i < 5; i++) //外循环控制行数，共打印四行
        {
            //左边直角三角形空格
            for (j = 1; j <= i; j++) {    //与外循环关联，初始值不变，表达式变化，共打印五行
                DealLog.log(" ");
            }
            //右边倒直角三角形
            for (q = i * 2; q < 9; q++) {    //与外循环关联，初始值变化，表达式不变，打印奇数列7，5，3，1
                DealLog.log("*");
            }
            DealLog.log();    //换行
        }
    }

    /**
     * 猴子吃桃
     * <p>
     * 一只小猴子一天摘了许多桃子，第一天吃了一半，然后忍不住又吃了一个；
     * 第二天又吃了一半，再加上一个；后面每天都是这样吃。
     * 到第10天的时候，小猴子发现只有一个桃子了。
     * 问小猴子第一天共摘了多少个桃子。
     */
    private static void c2() {
        // 方法一
        int sum1 = 1;
        for (int i = 9; i >= 1; i--) {
            sum1 = (sum1 + 1) * 2;
        }
        DealLog.log("sum1=" + sum1);
        // 方法二
        int sum2 = 1;
        for (int i = 1; i <= 9; i++) {
            sum2 = (sum2 + 1) * 2;
        }
        DealLog.log("sum2=" + sum2);
    }
}
