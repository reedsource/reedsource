/*
 * FileName: 二维数组
 * Author:   reedsource
 */
package k21数组;

import org.junit.Assert;
import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈二维数组〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/16 23:18
 * reedsource@189.cn
 */
public class 二维数组 {

    /**
     * 二维数组
     */
    @Test
    public void 二维数组_Test() {
        //动态初始化
        //可以理解为行数和列数
        String[][] s = new String[2][];
        // 是为最高维分配引用空间，也就是为最高维限制其能保存数据的最长的长度，
        // 然后再为其每个数组元素单独分配空间 s0=new String("Good") 等操作
        s[0] = new String[2];
        s[1] = new String[3];
        s[0][0] = "00";
        s[0][1] = "01";
        s[1][0] = "10";
        s[1][1] = "11";
        s[1][2] = "12";

        //静态初始化
        //声明二维数组：有两行，列数待定，数组结构 = { { }, { } }  静态初始化可用于不规则二维数组的初始化
        String[][] m = {{"E1", "E2"}, {"E1", "E2", "E3"}};
        DealLog.log(m[1][2]);

        Assert.assertEquals(2, m.length);
        Assert.assertEquals(3, m[1].length);
        Assert.assertEquals("E3", m[1][2]);

        //多维数组的引用
        DealLog.log(s[1][1]);
    }
}
