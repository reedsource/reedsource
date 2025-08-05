package top.ireed.aopEnum;

import top.ireed.deal.DealLog;

@TypeEnumHandler(TypeEnum.type03)
public class TypeEnum3Handler extends AbstractEnumHandler {
    @Override
    public String execute(String msg) {
        DealLog.log("我是enum任务执行器3 获的信息", msg);
        return "我是enum任务执行器3" + msg;
    }
}
