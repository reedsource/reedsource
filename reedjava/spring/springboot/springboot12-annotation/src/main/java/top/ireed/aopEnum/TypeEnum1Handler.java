package top.ireed.aopEnum;

import top.ireed.deal.DealLog;

@TypeEnumHandler(TypeEnum.type01)
public class TypeEnum1Handler extends AbstractEnumHandler {
    @Override
    public String execute(String msg) {
        DealLog.log("我是enum任务执行器1 获的信息", msg);
        return "我是enum任务执行器1" + msg;
    }
}
