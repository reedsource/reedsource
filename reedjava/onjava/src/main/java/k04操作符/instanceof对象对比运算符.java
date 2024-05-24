/*
 * FileName: 运算符优先级
 * Author:   reedsource
 */
package main.java.k04操作符;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java运算符〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class instanceof对象对比运算符 {

    /**
     * instanceof 运算符
     * <p>
     * 该运算符用于操作对象实例，检查该对象是否是一个特定类型（类类型或接口类型）
     */
    @Test
    public void instanceof_Test() {
        DealLog.log("a" instanceof String);
    }
}
