/*
 * Author:   reedsource
 */
package main.java.k06初始化和清理;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/26 22:33
 * reedsource@189.cn
 */
public class 检查对象是否被清理 {

    @Test
    public void 检查对象是否被清理_Test() {
        Book novel = new Book(true);
        // 正确清理
        novel.checkIn();
        // 没有清理就丢掉对对象的引用
        new Book(true);
        // 强制垃圾回收和终结操作
        System.gc();
    }
}

class Book {
    boolean checkedOut = false;

    Book(boolean checkOut) {
        checkedOut = checkOut;
    }

    void checkIn() {
        checkedOut = false;
    }

    //注解 抑制编译器异常
    //注解 继承方法都有的finalize方法,此方法已被废弃,本处只是案例
    //垃圾回收时已经回收了
    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() {
        if (checkedOut) {
            DealLog.log("错误,已签出");
            //通常情况下,你需要这样做
            //调用基类版本
            // super.finalize();
        }
    }
}