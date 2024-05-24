/*
 * FileName: 键盘数值类型ScannerHasNext
 * Author:   reedsource
 */
package main.java.k14流.控制台输入;

import top.ireed.deal.DealLog;

import java.util.Scanner;

/**
 * 功能简述:
 * 〈键盘数值类型数据〉
 * hasNextInt() 整数类型数据
 * hasNextFloat() 小数类型的数据
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class 键盘数值类型ScannerHasNext {

    public static void main(String[] args) {
        键盘数值类型ScannerHasNext_Test();
    }

    /**
     * 输入 int 或 float 类型的数据
     */
    public static void 键盘数值类型ScannerHasNext_Test() {
        Scanner scan = new Scanner(System.in);
        // 从键盘接收数据
        int i = 0;
        float f = 0.0f;
        DealLog.log("输入整数：");
        if (scan.hasNextInt()) {
            // 判断输入的是否是整数
            i = scan.nextInt();
            // 接收整数
            DealLog.log("整数数据：" + i);
        } else {
            // 输入错误的信息
            DealLog.log("输入的不是整数！");
        }
        DealLog.log("输入小数：");
        if (scan.hasNextFloat()) {
            // 判断输入的是否是小数
            f = scan.nextFloat();
            // 接收小数
            DealLog.log("小数数据：" + f);
        } else {
            // 输入错误的信息
            DealLog.log("输入的不是小数！");
        }
        scan.close();
    }

}
