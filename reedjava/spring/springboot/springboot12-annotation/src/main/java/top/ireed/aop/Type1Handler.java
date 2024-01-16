package top.ireed.aop;

import top.ireed.deal.DealLog;

@TypeHandler(type = "type1")
public class Type1Handler extends AbstractHandler {
    @Override
    public String execute(String msg) {
        DealLog.log("我是任务执行器1 获的信息", msg);
        return "我是任务执行器1" + msg;
    }
}
