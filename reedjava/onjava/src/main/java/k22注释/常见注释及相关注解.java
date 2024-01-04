package main.java.k22注释;

import main.java.j01枚举.枚举实现;

/**
 * 常见的注释及相关注解
 */
public class 常见注释及相关注解 {

    /**
     * 我是类的注释
     * @ see 全路径包名#方法名(参数类型列表)  直接点击进入对应的方法上，如果调用方法被篡改了，注释会报红，起到一个提示作用
     * @see 常见注释及相关注解#类()
     *
     * 可以拼接在其他文本中 { @link 全路径包名#方法名(参数类型列表)}
     * 头{@link 枚举实现}
     */
    public void 类() {
        System.out.println("一个类");
    }
}
