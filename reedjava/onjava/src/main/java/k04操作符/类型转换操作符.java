/*
 * FileName: 类型转换操作符
 * Author:   reedsource
 */
package main.java.k04操作符;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈java强制类型转换〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/7 14:06
 * reedsource@189.cn
 */
public class 类型转换操作符 {

    @Test
    public void 类型转换操作符_Test() {
        int i1 = 123;
        byte b = (byte) i1;//强制类型转换为byte
        DealLog.log("int强制类型转换为byte后的值等于" + b);
    }

    /*
     * 1. 条件是转换的数据类型必须是兼容的
     * 2. 格式：(type)value type是要强制类型转换后的数据类型
     * 整数的默认类型是 int
     * 浮点型不存在这种情况，因为在定义 float 类型时必须在数字后面跟上 F 或者 f
     *
     * 窄化转换 会造成信息丢失 编译器将会提示需要显式转换
     * 宽化转换 不需要显式转换 因为不会丢失信息
     *
     * boolean不允许任何类型的转换
     *
     * 对象可以在他的类型所在的族群中进行类型转换
     *
     * */
}
