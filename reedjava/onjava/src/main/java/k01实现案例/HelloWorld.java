/*
 * FileName: HelloWorld
 * Author:   reedsource
 */
package main.java.k01实现案例;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈基本java程序〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/6 22:52
 * reedsource@189.cn
 */
public class HelloWorld {
    /**
     * 它将打印字符串 Hello World
     *
     * @param args 初始数据组
     */
    public static void main(String[] args) {
        // 打印 Hello World
        //本处实际为标准打印 符合sonar规范 修改为调用工具类
        DealLog.log("Hello World");
    }
}
