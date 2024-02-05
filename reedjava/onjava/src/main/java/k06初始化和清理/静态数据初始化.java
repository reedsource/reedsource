/*
 * Author:   reedsource
 */
package main.java.k06初始化和清理;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/7 23:29
 * reedsource@189.cn
 */
public class 静态数据初始化 {
    static 桌子 桌子 = new 桌子();
    static 柜橱 柜橱 = new 柜橱();

    public static void main(String[] args) {

        //TODO
        DealLog.log("2022年7月23日", "程序的默认加载顺序,重点重复学习");

        DealLog.log("main方法 new 柜橱()");
        new 柜橱();
        DealLog.log("main方法 new 柜橱()");
        new 柜橱();
        桌子.桌子方法("调用桌子方法");
        柜橱.橱柜方法("调用橱柜方法");

        //book125

        //碗(桌子1 默认初始化一个碗)
        //碗(桌子 尾 初始化一个碗)
        //桌子()
        //碗方法(桌子 碗 方法调用)
        //碗(橱柜2 static 默认初始化一个碗)
        //碗(橱柜 尾 初始化一个碗)
        //碗(橱柜1 默认初始化一个碗)
        //柜橱()3 被调用
        //碗方法(橱柜 碗 方法 调用)
        //main方法 new 柜橱()
        //碗(橱柜1 默认初始化一个碗)
        //柜橱()3 被调用
        //碗方法(橱柜 碗 方法 调用)
        //main方法 new 柜橱()
        //碗(橱柜1 默认初始化一个碗)
        //柜橱()3 被调用
        //碗方法(橱柜 碗 方法 调用)
        //桌子方法(调用桌子方法)
        //橱柜方法(调用橱柜方法)
    }
}

class 碗 {
    碗(String marker) {
        DealLog.log("碗(" + marker + ")");
    }

    void 碗方法(String marker) {
        DealLog.log("碗方法(" + marker + ")");
    }
}

class 桌子 {
    static 碗 碗1 = new 碗("桌子1 默认初始化一个碗");

    桌子() {
        DealLog.log("桌子()");
        碗2.碗方法("桌子 碗 方法调用");
    }

    void 桌子方法(String marker) {
        DealLog.log("桌子方法(" + marker + ")");
    }

    static 碗 碗2 = new 碗("桌子 尾 初始化一个碗");
}

class 柜橱 {
    碗 碗3 = new 碗("橱柜1 默认初始化一个碗");
    static 碗 碗4 = new 碗("橱柜2 static 默认初始化一个碗");

    柜橱() {
        DealLog.log("柜橱()3 被调用");
        碗4.碗方法("橱柜 碗 方法 调用");
    }

    void 橱柜方法(String marker) {
        DealLog.log("橱柜方法(" + marker + ")");
    }

    static 碗 碗5 = new 碗("橱柜 尾 初始化一个碗");
}