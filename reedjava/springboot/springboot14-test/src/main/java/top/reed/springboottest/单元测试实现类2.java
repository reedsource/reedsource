package top.reed.springboottest;

public class 单元测试实现类2 implements 单元测试接口2 {
    @Override
    public String 我是接口2有返回值接口(String msg) {
        System.out.println("我是接口2有返回值接口实现类");
        return "我是有返回值接口实现类" + msg;
    }

    @Override
    public void 我是接口2无返回值接口(String msg) {
        System.out.println("我是接口2无返回值接口实现类");
    }

}
