/*
 * FileName: javaStringCase
 * Author:   reedsource
 */
package main.java.k03对象.包装类.String包装类.String组合案例;

import org.junit.Assert;

/**
 * 功能简述:
 * 〈string使用案例〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class javaStringCase {
    public static void main(String[] args) {
        // 1. 截取字符串某字符串后面的字符串
        String oilUrl = "/dzsd_operate_war_exploded/a/oil/order/list";
        oilUrl = oilUrl.substring(oilUrl.indexOf("/a/") + 2, oilUrl.length());
        Assert.assertEquals("/oil/order/list", oilUrl);
    }
}
