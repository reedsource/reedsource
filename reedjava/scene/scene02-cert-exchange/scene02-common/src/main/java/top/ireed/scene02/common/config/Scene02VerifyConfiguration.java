package top.ireed.scene02.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ireed.scene02.common.verify.CaseResult;
import top.ireed.scene02.common.verify.VerifyController;
import top.ireed.scene02.common.verify.VerifyRunner;

/**
 * 场景02案例验证装配
 * <p>
 * 验证器需要同时访问平台方与资方方,由平台方导入本配置后暴露验证入口;
 * 资方方不导入,保持其作为纯对端角色。
 */
@Configuration
public class Scene02VerifyConfiguration {

    /**
     * 案例验证器
     *
     * @param props 场景配置
     * @return 验证器
     */
    @Bean
    public VerifyRunner verifyRunner(Scene02Properties props) {
        return new VerifyRunner(props);
    }

    /**
     * 案例验证接口
     *
     * @param runner 验证器
     * @return 控制器
     */
    @Bean
    public VerifyController verifyController(VerifyRunner runner) {
        return new VerifyController(runner);
    }
}
