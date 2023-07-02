package main.java.j01枚举;

/**
 * 功能简述:
 * 〈枚举类型〉
 *
 * @author reedbook
 * date 2022/5/21 23:56
 * mail reedsource@189.cn
 */
public enum 枚举类型 {
    // 利用构造函数传参
    RED(1), GREEN(3), YELLOW(2);

    /**
     * 定义私有变量
     */
    private final int nCode;

    /**
     * 构造函数，枚举类型只能为私有
     */
    枚举类型(int nCode) {
        this.nCode = nCode;
    }

    @Override
    public String toString() {
        return String.valueOf(this.nCode);
    }
}
