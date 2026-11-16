package top.ireed.scene02.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import top.ireed.scene02.common.config.Scene02CommonConfiguration;
import top.ireed.scene02.common.config.Scene02Properties;
import top.ireed.scene02.common.config.Scene02VerifyConfiguration;

import java.util.Collections;

/**
 * 平台方(京东方)应用
 * <p>
 * 独立SpringBoot工程:
 * 1.提供8.1.1获取平台公钥证书接口
 * 2.定时任务每5分钟拉取资方方(中信)的公钥证书并交换加密报文
 * 3.导入公共装配与案例验证装配,暴露案例验证入口
 * 配置文件:application-platform.properties
 */
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(Scene02Properties.class)
@Import({Scene02CommonConfiguration.class, Scene02VerifyConfiguration.class})
public class PlatformApplication {

    /**
     * 本端配置文件名
     */
    public static final String CONFIG_NAME = "application-platform";

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(PlatformApplication.class);
        // 平台方使用独立配置文件
        application.setDefaultProperties(Collections.singletonMap("spring.config.name", CONFIG_NAME));
        application.run(args);
    }
}
