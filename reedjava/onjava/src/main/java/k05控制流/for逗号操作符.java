/*
 * Author:   reedsource
 */
package k05控制流;

import org.junit.Test;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/22 11:03
 * reedsource@189.cn
 */
public class for逗号操作符 {

    @Test
    public void for逗号操作符_Test() {
        // 是操作符
        //初始化时可以定义多个相同类型的变量
        for (int i = 1, j = i + 10; i < 5; i++, j = i * 2) {
            System.out.println("i = " + i + " j = " + j);
        }
    }
}
