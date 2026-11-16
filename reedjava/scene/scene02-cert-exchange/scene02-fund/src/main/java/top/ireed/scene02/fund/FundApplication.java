package top.ireed.scene02.fund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import top.ireed.scene02.common.config.Scene02CommonConfiguration;
import top.ireed.scene02.common.config.Scene02Properties;

import java.util.Collections;

/**
 * 资方方(中信)应用
 * <p>
 * 独立SpringBoot工程:
 * 1.提供8.1.2获取资方公钥证书接口
 * 2.定时任务每5分钟拉取平台方(京东方)的公钥证书并交换加密报文
 * 配置文件:application-fund.properties
 */
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(Scene02Properties.class)
@Import(Scene02CommonConfiguration.class)
public class FundApplication {

    /**
     * 本端配置文件名
     */
    public static final String CONFIG_NAME = "application-fund";

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(FundApplication.class);
        // 资方方使用独立配置文件
        application.setDefaultProperties(Collections.singletonMap("spring.config.name", CONFIG_NAME));
        application.run(args);
    }
}
