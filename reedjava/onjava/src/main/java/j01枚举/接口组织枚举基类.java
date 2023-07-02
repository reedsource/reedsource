package main.java.j01枚举;

/**
 * 功能简述:
 * 〈类似分组〉
 *
 * @author reedbook
 * date 2022/5/21 23:45
 * mail reedsource@189.cn
 */
public interface 接口组织枚举基类 {
    enum 咖啡 implements 接口组织枚举基类 {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }

    enum 甜点 implements 接口组织枚举基类 {
        FRUIT, CAKE, GELATO
    }
}
