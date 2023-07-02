/*
 * FileName: 控制台输入取空格前ScannerNext
 * Author:   reedsource
 */
package k14流.控制台输入;

import top.ireed.deal.DealLog;

import java.io.IOException;
import java.util.Scanner;

/**
 * 功能简述:
 * 〈Scanner.next()控制台输入〉
 * <p>
 * 1、一定要读取到有效字符后才可以结束输入。
 * 2、对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
 * 3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
 * next() 不能得到带有空格的字符串。
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/9 22:57
 * reedsource@189.cn
 */
public class 控制台输入取空格前ScannerNext {

    public static void main(String[] args) throws IOException {
        控制台输入ScannerNext_Test();
    }

    /**
     * 使用 next 方法：
     */
    public static void 控制台输入ScannerNext_Test() {
        Scanner scan = new Scanner(System.in);
        // 从键盘接收数据

        // next方式接收字符串
        DealLog.log("next方式接收：");
        // 判断是否还有输入
        if (scan.hasNext()) {
            String str1 = scan.next();
            DealLog.log("输入的数据为：" + str1);
        }
        scan.close();

        //next方式接收：
        //111 222
        //输入的数据为：111
    }
}
