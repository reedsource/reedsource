/*
 * FileName: 控制台输入取回车前ScannerNextLine
 * Author:   reedsource
 */
package main.java.k14流.控制台输入;

import top.ireed.deal.DealLog;

import java.util.Scanner;

/**
 * 功能简述:
 * 〈Scanner.nextLine() 控制台输入〉
 * <p>
 * 1、以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符。
 * 2、nextLine()可以获得空白。
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class 控制台输入取回车前ScannerNextLine {
    public static void main(String[] args) {
        控制台输入ScannerNextLine_Test();
    }

    /**
     * 使用 nextLine 方法：
     */
    private static void 控制台输入ScannerNextLine_Test() {
        // 从键盘接收数据
        Scanner scan = new Scanner(System.in);

        // nextLine方式接收字符串
        DealLog.log("nextLine方式接收：");
        // 判断是否还有输入
        if (scan.hasNextLine()) {
            String str2 = scan.nextLine();
            DealLog.log("输入的数据为：" + str2);
        }
        scan.close();

        //nextLine方式接收：
        //ireed com
        //输入的数据为：ireed com
    }
}
