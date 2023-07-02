/*
 * FileName: String非空判断
 * Author:   reedsource
 */
package k03对象.包装类.String包装类.String组合案例;


import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class String非空判断 {
    public static void main(String[] args) {
        String aaa = null;
        if (aaa != null && aaa != "") {
            DealLog.log(111111);
        }

        if (!"".equals(aaa) && aaa != null) {
            DealLog.log(222222);
        }

    }
}
