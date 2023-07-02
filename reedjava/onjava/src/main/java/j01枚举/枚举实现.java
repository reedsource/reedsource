/*
 * FileName: 枚举实现
 * Author:   reedsource
 */
package j01枚举;

import org.junit.Test;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/21 23:35
 * reedsource@189.cn
 */
public class 枚举实现 {
    private 颜色枚举 颜色枚举1 = 颜色枚举.RED;


    /**
     * 探索枚举类中 默认包含toString方法
     */
    @Test
    public void 颜色枚举() {
        颜色枚举 y = 颜色枚举.RED;
        DealLog.log(y);
        //GREEN

        //能打印本身代表枚举类本身默认的添加了toString方法
    }


    /**
     * 增强可读性
     */
    @Test
    public void 枚举增强可读() {
        switch (颜色枚举1) {
            case RED:
                颜色枚举1 = 颜色枚举.GREEN;
                break;
            case GREEN:
                颜色枚举1 = 颜色枚举.YELLOW;
                break;
            case YELLOW:
            default:
                颜色枚举1 = 颜色枚举.RED;
        }

        DealLog.log(颜色枚举1);
    }


    /**
     * 枚举类实现接口
     */
    @Test
    public void 枚举接口基类实现枚举_Test() {
        枚举接口基类实现枚举 color1 = 枚举接口基类实现枚举.GREEN;
        DealLog.log(color1.getInfo());
        color1.print();
    }

    /**
     * 接口组织枚举
     * 类似分组
     */
    @Test
    public void 食物枚举基类_Test() {
        DealLog.log(接口组织枚举基类.咖啡.BLACK_COFFEE);
        DealLog.log(接口组织枚举基类.甜点.FRUIT);
    }

    /**
     * 自定义枚举方法
     * <p>
     * 枚举类型相当于静态类型, 只能在构建枚举类的时候初始化
     * 不能用 new 颜色构造枚举("红色", 1) 创造
     */
    @Test
    public void 颜色构造枚举_Test() {
        颜色构造枚举 colo = 颜色构造枚举.GREEN;
        DealLog.log(colo.getName());
        DealLog.log(colo.getIndex());
    }

    /**
     * 遍历枚举
     */
    @Test
    public void 遍历枚举_Test() {
        枚举类型[] 定义枚举类型s = 枚举类型.values();
        for (枚举类型 aLight : 定义枚举类型s) {
            DealLog.log("当前枚举名称", aLight.name());
            DealLog.log("当前枚举序号", aLight.ordinal());
            DealLog.log("当前枚举值", aLight);
        }
    }
}
