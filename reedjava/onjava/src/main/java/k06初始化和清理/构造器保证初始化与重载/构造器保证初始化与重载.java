/*
 * Author:   reedsource
 */
package k06初始化和清理.构造器保证初始化与重载;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/22 16:28
 * reedsource@189.cn
 */
public class 构造器保证初始化与重载 {

    @Test
    public void 构造器保证初始化与重载_Test() {
        //我们在new时 会给对象分配存储空间 然后调用这个类的构造器
        //构造器会保证这个对象在可用前就已经正确的初始化了

        //以下使用了两个相同名称的构造函数,但参数不同 以应对不同的需求 就是重载
        构造对象 g = new 构造对象();
        DealLog.log(g);

        int a = 10;
        构造对象 y = new 构造对象(a);
        //实际是调用方法了
        int v = y.构造对象(5);
        DealLog.log(v);

    }
}
