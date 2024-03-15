package top.reed.springboottest;

import javax.annotation.Resource;

public class 单元测试实现类 implements 单元测试接口 {

    @Resource
    单元测试接口2 d2;

    @Override
    public String 有返回值接口(String msg) {
       String m=  d2.我是接口2有返回值接口(msg);
        System.out.println("我是有返回值接口实现类" + m);
        return "我是有返回值接口实现类" + msg;
    }

    @Override
    public void 无返回值接口(String msg) {
        d2.我是接口2无返回值接口(msg);
        System.out.println("我是无返回值接口实现类");
    }

    public void 无返回值接口测试有返回值工具类(String msg) {
        String s = 静态工具类.有返回值静态类("有返回值静态入参" + msg);
        System.out.println("我是无返回值接口实现类");
    }

    public void 无返回值接口测试无返回值工具类(String msg) {
        静态工具类.无返回值静态类("有返回值静态入参" + msg);
        静态工具类.无返回值静态Object类(msg,1);
        System.out.println("我是无返回值接口实现类");
    }
}
