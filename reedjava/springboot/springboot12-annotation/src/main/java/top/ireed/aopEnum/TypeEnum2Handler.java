package top.ireed.aopEnum;

import top.ireed.deal.DealLog;

@TypeEnumHandler(TypeEnum.type02)
public class TypeEnum2Handler extends AbstractEnumHandler {
    @Override
    public String execute(String msg) {
        DealLog.log("我是enum任务执行器2 获的信息", msg);
        return "我是enum任务执行器2" + msg;
    }
}
