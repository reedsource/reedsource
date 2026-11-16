package top.ireed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 场景启动类
 * <p>
 * 每个场景是一个独立的SpringBoot项目,拥有独立的启动类、包名、端口和可执行jar。
 * 复制本模板新增场景时,需同步修改:
 * 1.类名 Scene01TemplateApplication -> Scene02XxxApplication
 * 2.包路径 top.ireed -> top.ireed.scene02xxx
 * 3.application.properties 中的 server.port 与 spring.application.name
 * 4.pom.xml 中的 artifactId 与 name
 */
@SpringBootApplication
public class Scene01TemplateApplication {

    /**
     * 启动入口
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(Scene01TemplateApplication.class, args);
    }

}
