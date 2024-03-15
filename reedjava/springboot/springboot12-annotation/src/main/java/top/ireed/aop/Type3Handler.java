package top.ireed.aop;

import top.ireed.deal.DealLog;

@TypeHandler(type = "type3")
public class Type3Handler extends AbstractHandler {
    @Override
    public String execute(String msg) {
        DealLog.log("我是任务执行器3 获的信息", msg);
        return "我是任务执行器3" + msg;
    }
}
