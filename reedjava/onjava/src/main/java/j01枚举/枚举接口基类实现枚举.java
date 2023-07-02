package main.java.j01枚举;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * date 2022/5/21 23:34
 * mail reedsource@189.cn
 */
public enum 枚举接口基类实现枚举 implements 枚举接口基类 {
    /**
     * 颜色
     */
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    /**
     * 成员变量
     */
    private String name;
    private int index;

    /**
     * 构造方法
     */
    枚举接口基类实现枚举(String name, int index) {
        this.name = name;
        this.index = index;
    }

    //

    /**
     * 接口方法
     *
     * @return 属性
     */
    @Override
    public String getInfo() {
        return this.name;
    }

    /**
     * 接口方法
     */
    @Override
    public void print() {
        DealLog.log(this.index + ":" + this.name);
    }
}
