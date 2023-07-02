/*
 * Author:   reedsource
 */
package main.java.k04操作符;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/18 19:20
 * reedsource@189.cn
 */
public class 科学计数法 {
    @Test
    public void 科学计数法_Test() {
        // e大小写都可以:
        //在java中的含义 1.39*10的43次方
        float expFloat = 1.39e-43f;
        DealLog.log(expFloat);
        double expDouble = 47e47d; //d是可选的
        double expDouble2 = 47e47; //默认就是double类型的

        //这里实际含义不是-,不能在中间加空格 =10的43次方
        float f4 = 1e-43f;
        DealLog.log(expDouble, expDouble2);
    }

}
