package top.reed.springboottest;

public class 静态工具类 {

    public static void 无返回值静态类(String msg){
        System.out.println("我是一个无返回值静态工具类方法");
    }

    public static String 有返回值静态类(String msg){
        System.out.println("我是一个有返回值静态工具类方法");
        return msg;
    }

    public static void  无返回值静态Object类(Object msg, int ss){
        System.out.println("我是一个有返回值静态工具类方法" + msg +ss);
    }
}
