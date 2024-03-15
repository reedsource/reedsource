# springboot07-jarwar打包和使用

## 一 jar包

### 1.打包方式

在maven中选择package打包

### 2.包文件位置

- 本机的 repository 仓库, 根据建项目的域名 倒序 在repository中查找以项目名命名的jar包

- 在打包的target中寻找

### 3.后台启动使用

```
nohup java -Xms64m -Xmx64m -D java.compiler=NONE -jar /root/top/ireedtop-0.0.1.jar &
```

### 4. 脚本启动方式

客户在linux下创建 run.sh脚本文件来启动

    创建脚本,注意开头是固定的不可更改
        注意必须使用vim编辑器,否则可能报错
    #!/bin/sh
    java -jar springboot13-jar-0.0.1-SNAPSHOT.jar


    保存后,赋予脚本权限
    777 是最高权限，有读、写、执行权限；和属组用户和其他用户的读、写、执行权限。
    其他权限分别是
    -rw------- (600) -- 只有读写权限。
    -rw-r--r-- (644) -- 只有有读写权限；而属组用户和其他用户只有读权限。
    -rwx------ (700) -- 只有有读、写、执行权限。
    -rwxr-xr-x (755) -- 有读、写、执行权限；而属组用户和其他用户只有读、执行权限。
    -rwx--x--x (711) -- 有读、写、执行权限；而属组用户和其他用户只有执行权限
    
    本处赋予的权限是:
    chmod 777 jar.sh
    
    在目录下输入  ./jar.sh 即可启动项目

## 二 war包

war包项目无法在调试中直接启动调用,需要只install编译后,将编译的war包放到tomcat的webApps中 启动tomcat,将会自动解压缩 需要访问 localhost:8080/项目名/controller的路径

开发时一般使用jar包开发,在将编译为war包的时候,在pom.xml中将jar变更为war包编译即可

### 1.springboot打包为war-pom配置

```
<dependencies>
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
         <!-- 移除嵌入式tomcat插件 -->
         <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
         </exclusion>
      </exclusions>
   </dependency>
</dependencies>

<!--Spring Boot war编译依赖-->
<build>
   <plugins>
      <plugin>
         <groupId>org.apache.maven.plugins</groupId>
         <artifactId>maven-war-plugin</artifactId>
         <version>${war.version}</version>
         <configuration>
            <!--屏蔽web.xml-->
            <failOnMissingWebXml>false</failOnMissingWebXml>
         </configuration>
      </plugin>
   </plugins>
</build>
```

### 2.修改启动类

- 原始启动类

```
@SpringBootApplication
public class Springboot07JarwarApplication {
   public static void main(String[] args) {
      SpringApplication.run(Springboot07JarwarApplication.class, args);
   }
}
```

- 修改为启动类

```
/**
 * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 * @author reedsource
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      // 注意这里要指向原先用main方法执行的Application启动类
      return builder.sources(Application.class);
   }
}
```