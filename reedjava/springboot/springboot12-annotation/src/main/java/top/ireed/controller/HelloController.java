/*
 * FileName: helloController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ireed.aop.AbstractHandler;
import top.ireed.aop.TaskHandlerRegister;
import top.ireed.deal.DealLog;
import top.ireed.note.MonRequestMapping;

/**
 * 功能简述:
 * 〈springboot基本controller控制器  返回基本字符串〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/28 9:23
 * reedsource@189.cn
 * <p>
 * 标明控制器注解
 */
@Controller
public class HelloController {
    /**
     * @return 处理请求地址映射的注解, 表示类中的所有响应请求的方法都是以该地址作为父路径
     * 将 HTTP 请求正文插入方法中,使浏览器界面可以识别
     */
    @MonRequestMapping("/boot/hello")
    public @ResponseBody
    String hello(String type) {

        DealLog.log("默认进入type", type);
        if (TaskHandlerRegister.getTaskHandler(type) == null) {
            throw new RuntimeException("没找到对应的任务执行器 type=" + type);
        }
        AbstractHandler abstractHandler = TaskHandlerRegister.getTaskHandler(type);
        DealLog.log(abstractHandler.execute(type));
        DealLog.log("变更type为1");

        type="type1";
        abstractHandler = TaskHandlerRegister.getTaskHandler(type);
        DealLog.log(abstractHandler.execute(type));
        DealLog.log("变更type为2");

        type="type2";
        abstractHandler = TaskHandlerRegister.getTaskHandler(type);
        DealLog.log(abstractHandler.execute(type));

        return "Hello SpringBoot!" + type;
    }

}
