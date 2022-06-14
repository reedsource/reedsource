/*
 * Author:   reedsource
 */
package k04操作符.对象别名;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈地址引用  产生的 别名现象〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/6/14 23:27
 * reedsource@189.cn
 */
public class 对象别名 {
    static void f(Letter y) {
        y.c = 'z';
    }

    @Test
    public void 对象别名_Test(){
        Letter x = new Letter();
        x.c = 'a';
        DealLog.log("1: x.c: " + x.c);
        f(x);
        DealLog.log("2: x.c: " + x.c);

        //1: x.c: a
        //2: x.c: z
    }
}
class Letter {
    char c;
}
