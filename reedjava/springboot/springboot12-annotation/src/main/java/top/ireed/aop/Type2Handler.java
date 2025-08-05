package top.ireed.aop;

import top.ireed.deal.DealLog;

@TypeHandler(type = "type2")
public class Type2Handler extends AbstractHandler {
    @Override
    public String execute(String msg) {
        DealLog.log("我是任务执行器2 获的信息", msg);
        return "我是任务执行器2" + msg;
    }
}
