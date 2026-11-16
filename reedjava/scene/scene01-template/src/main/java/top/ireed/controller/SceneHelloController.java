package top.ireed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ireed.common.SceneResult;

/**
 * 场景示例控制器
 * <p>
 * 用于验证场景骨架是否可正常启动、路由是否生效。
 * 正式场景可删除本控制器,或替换为该场景自己的接口。
 */
@RestController
@RequestMapping("/scene")
public class SceneHelloController {

    /**
     * 场景存活探针
     *
     * @return 场景名称与时间戳
     */
    @GetMapping("/ping")
    public SceneResult<String> ping() {
        return SceneResult.ok("scene01-template alive," + System.currentTimeMillis());
    }

    /**
     * 场景示例接口
     *
     * @return 固定返回内容
     */
    @GetMapping("/hello")
    public SceneResult<String> hello() {
        return SceneResult.ok("Hello Scene!");
    }
}
