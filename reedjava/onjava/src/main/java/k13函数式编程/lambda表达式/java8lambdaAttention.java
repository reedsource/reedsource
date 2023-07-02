/*
 * FileName: java8lambdaAttention
 * Author:   reedsource
 */
package main.java.k13函数式编程.lambda表达式;

import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈lambda特性与注意事项〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/11 22:48
 * reedsource@189.cn
 */
public class java8lambdaAttention {
    private final static String SALUTATION = "你好! ";

    public static void main(String args[]) {
        // lambda 表达式只能引用标记了 final 的外层局部变量，
        // 这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
        GreetingService greetService1 = message -> DealLog.log(SALUTATION + message);
        greetService1.sayMessage("世界");

        DealLog.log("============================================");
        //在 lambda 表达式中访问外层的局部变量
        final int num = 1;
        Converter<Integer, String> s = (param) -> DealLog.log((param + num));
        s.convert(2);  // 输出结果为 3

        DealLog.log("============================================");
        //lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
        int num1 = 1;
        Converter<Integer, String> m = (param) -> DealLog.log((param + num1));
        m.convert(2);
        //本处如果尝试修改  将编译错误
        //报错信息：Local variable num defined in an enclosing scope must be final or effectively
        //num1 = 5;

        DealLog.log("============================================");

        String first = "";
        //编译会出错   在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
        //Comparator<String> comparator = (first, second) -> Integer.compare(first.length(), second.length());

    }

    interface GreetingService {
        void sayMessage(String message);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }
}
