package top.reed.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


/**
 * 自定义配置类, 和直接放在启动类效果一致
 */
@Configuration
@MapperScan("mapper")
public class MybatisPlusConfig {

}
