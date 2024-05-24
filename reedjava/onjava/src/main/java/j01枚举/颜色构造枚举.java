package main.java.j01枚举;

import lombok.Getter;

/**
 * 功能简述:
 * 〈〉
 *
 * @author reedbook
 * date 2022/5/21 23:26
 * mail reedsource@189.cn
 */
@Getter
public enum 颜色构造枚举 {

    /**
     * 颜色
     */
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
    /**
     * 成员私有变量
     */
    private final String name;
    private final int index;

    /**
     * 设定构造方法，枚举类型只能为私有
     */
    颜色构造枚举(String name, int index) {
        this.name = name;
        this.index = index;
    }

    /**
     * 普通方法
     */
    public static String getName(int index) {
        for (颜色构造枚举 c : 颜色构造枚举.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    /**
     * 覆盖方法
     */
    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }


}
