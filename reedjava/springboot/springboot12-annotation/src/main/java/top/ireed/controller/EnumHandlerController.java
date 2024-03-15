/*
 * FileName: helloController
 * Author:   reedsource
 */
package top.ireed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ireed.aopEnum.AbstractEnumHandler;
import top.ireed.aopEnum.EnumHandlerRegister;
import top.ireed.aopEnum.TypeEnum;
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
public class EnumHandlerController {
    /**
     * @return 处理请求地址映射的注解, 表示类中的所有响应请求的方法都是以该地址作为父路径
     * 将 HTTP 请求正文插入方法中,使浏览器界面可以识别
     */
    @MonRequestMapping("/enumhandler")
    public @ResponseBody
    String hello(String type) {

        DealLog.log("enumhandler默认进入type", type);
        if (EnumHandlerRegister.getTaskHandler(TypeEnum.getNameEnum(type)) == null) {
            throw new RuntimeException("没找到对应的任务执行器 type=" + type);
        }
        AbstractEnumHandler abstractEnumHandler = EnumHandlerRegister.getTaskHandler(TypeEnum.getNameEnum(type));
        DealLog.log(abstractEnumHandler.execute(type));

        DealLog.log("变更type为1");
        type="type01";
        AbstractEnumHandler abstractEnumHandler1 = EnumHandlerRegister.getTaskHandler(TypeEnum.getNameEnum(type));
        DealLog.log(abstractEnumHandler1.execute(type));

        DealLog.log("变更type为2");
        type="type02";
        AbstractEnumHandler abstractEnumHandler2 = EnumHandlerRegister.getTaskHandler(TypeEnum.getNameEnum(type));
        DealLog.log(abstractEnumHandler2.execute(type));

        return "Hello SpringBoot!" + type;
    }

}
