/*
 * FileName: BigDecimal对象创建
 * Author:   reedsource
 */
package k03对象.工具类.高精度浮点BigDecimal与整数BigInteger;

import top.ireed.deal.DealLog;

import java.math.BigDecimal;

/**
 * 功能简述:
 * 〈BigDecimal创建〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/8 20:57
 * reedsource@189.cn
 */
public class BigDecimal对象创建 {
    public static void main(String[] args) {
        int a = 1;
        DealLog.log("创建一个具有参数所指定整数值的对象 " + new BigDecimal(a));
        double b = 1.1;
        DealLog.log("创建一个具有参数所指定双精度值的对象 " + new BigDecimal(b));
        long c = 151563513132513L;
        DealLog.log("创建一个具有参数所指定长整数值的对象 " + new BigDecimal(c));
        String d = "1203";
        DealLog.log("创建一个具有参数所指定以字符串表示的数值的对象 " + new BigDecimal(d));

        //注意 构建方法不建议使用double类型 构建结果不可预知 建议使用String方式替代

        //必须使用double类型时 使用
        DealLog.log("double构建建议方法 " + new BigDecimal(Double.toString(b)));
    }
}
