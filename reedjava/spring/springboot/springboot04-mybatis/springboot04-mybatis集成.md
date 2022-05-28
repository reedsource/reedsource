# springboot04-mybatis集成

## 一	集成MyBatis的步骤

### 1.在pom.xml中配置相关jar依赖；

```xml
<!-- 加载mybatis整合springboot -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.0.0</version>
</dependency>

<!-- MySQL的jdbc驱动包 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```
### 2.配置文件中添加数据库数据源信息

- yml格式配置

```yml
spring:
  datasource:
    username:
    password:
    driver-class-name: org.sqlite.JDBC
    url: jdbc:sqlite:D:/top/topcache/data/myBatisData.db?date_string_format=yyyy-MM-dd HH:mm:ss
```
- properties格式配置

```properties
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=?useUnicode=true&characterEncoding=utf8&useSSL=false
```



### 3.<a id="pom3">pom文件编译设置</a>

```xml
<build>
   <plugins>
      <!--编译插件-->
      <plugin>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
      <!--mybatis代码自动生成插件-->
      <plugin>
         <groupId>org.mybatis.generator</groupId>
         <artifactId>mybatis-generator-maven-plugin</artifactId>
         <version>${mybatis.generator.version}</version>
         <configuration>
            <!--配置文件的位置-->
            <configurationFile>src/main/resources/GeneratorMapper.xml</configurationFile>
            <verbose>true</verbose>
            <overwrite>true</overwrite>
         </configuration>
      </plugin>
   </plugins>
   <resources>
      <!--编译xml文件-->
      <resource>
         <directory>src/main/java</directory>
         <includes>
            <include>**/*.xml</include>
         </includes>
      </resource>
      <!--编译resources下全部文件-->
      <resource>
         <directory>src/main/resources</directory>
         <includes>
            <include>**/*.*</include>
         </includes>
      </resource>
      <!--设置文件过滤,即application.yaml编译时,@@会替换为pom文件中对应的参数-->
      <resource>
         <directory>src/main/resources</directory>
         <includes>
            <include>application.yml</include>
         </includes>
         <filtering>true</filtering>
      </resource>
   </resources>
</build>
```

### 4.Mapper接口中添加@Mapper注解

在MyBatis的Mapper接口中添加@Mapper注解

或者在运行的主类上添加 @MapperScan("top.ireed.mapper") 注解包扫描

## 二	Spring Boot集成事务

### 1.集成事务实现

- 1.在入口启动类Application中使用注解 @EnableTransactionManagement 开启事务支持；

- 2.在访问数据库的Service方法上添加注解 @Transactional 即可；

- ```
  @Transactional(rollbackFor = Exception.class)
  ```

### 2.@Transactional注解解析

| 属性                   | 类型                               | 描述                                   |
| ---------------------- | ---------------------------------- | -------------------------------------- |
| value                  | String                             | 可选的限定描述符，指定使用的事务管理器 |
| propagation            | enum: Propagation                  | 可选的事务传播行为设置                 |
| isolation              | enum: Isolation                    | 可选的事务隔离级别设置                 |
| readOnly               | boolean                            | 读写或只读事务，默认读写               |
| timeout                | int (in seconds granularity)       | 事务超时时间设置                       |
| rollbackFor            | Class对象数组，必须继承自Throwable | 导致事务回滚的异常类数组               |
| rollbackForClassName   | 类名数组，必须继承自Throwable      | 导致事务回滚的异常类名字数组           |
| noRollbackFor          | Class对象数组，必须继承自Throwable | 不会导致事务回滚的异常类数组           |
| noRollbackForClassName | 类名数组，必须继承自Throwable      | 不会导致事务回滚的异常类名字数组       |

@Transactional(rollbackFor=Exception.class)，如果类加了这个注解，那么这个类里面的方法抛出异常，就会回滚，数据库里面的数据也会回滚。

在@Transactional注解中如果不配置rollbackFor属性,那么事物只会在遇到RuntimeException的时候才会回滚,加上rollbackFor=Exception.class,可以让事物在遇到非运行时异常时也回滚

## 三	自动生成插件集成

### 1.引入GeneratorMapper.xml 文件

- 修改编译位置 驱动包位置
- 修改表名 实体类名

### 2.pom文件编译设置

- [见 3.pom文件编译设置](#pom3)

### 3.maven编译

maven-plugins-mybatis-generator-generate执行即可



