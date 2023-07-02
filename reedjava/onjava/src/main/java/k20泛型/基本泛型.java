/*
 * FileName: 基本泛型
 * Author:   reedsource
 */
package main.java.k20泛型;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈泛型类〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class 基本泛型<T> {
    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    @Test
    public void 基本泛型_Test() {
        基本泛型<Integer> 基本泛型 = new 基本泛型<>();
        基本泛型<String> 基本泛型1 = new 基本泛型<>();

        基本泛型.add(10);
        基本泛型1.add("菜鸟教程");

        DealLog.log("整型值为 :", 基本泛型.get());
        DealLog.log("字符串为 :", 基本泛型1.get());

        //整型值为 :10
        //字符串为 :菜鸟教程
    }
}
