package top.ireed.aop;

public abstract class AbstractHandler {

    /**
     * 任务执行器
     *
     * @param msg 信息
     * @return 执行结果
     */
    public abstract String execute(String msg);

}
