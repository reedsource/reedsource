package top.reed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
//添加 @MapperScan 注解，扫描 Mapper 文件夹
@MapperScan("top.reed.Mapper")
public class Springboot13MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot13MybatisPlusApplication.class, args);
    }

}
