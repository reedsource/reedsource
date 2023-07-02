/*
 * Author:   reedsource
 */
package main.java.k06初始化和清理;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈构造器调用时的执行顺序〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/7/5 21:11
 * reedsource@189.cn
 */
public class 初始化顺序 {
    @Test
    public void 初始化顺序_Test() {
        House h = new House();
        h.f();
    }
}

class Window {
    Window(String marker) {
        DealLog.log("Window", marker);
    }
}

class House {
    Window w1 = new Window("在构造器前");

    House() {
        DealLog.log("构造器进入");
        w3 = new Window("构造器调用1");
    }

    Window w2 = new Window("构造器调用2");

    void f() {
        DealLog.log("f()");
    }

    Window w3 = new Window("在构造器尾"); // At end
}