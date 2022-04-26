# reedtools

## 一 更新日志

### 1. v1.0.0

- 2020年9月11日 初始整理完成
- 2021年1月20日 修改打印类 当参数为空时 默认打印换行
- 2022年4月20日 重启
    - 优化重构核心工具框架 FoundSqlite
- 2022年4月24日 抽取重构
    - FoundMarkdown markdown生成工具类

## 二 不可添加注入及原因

### 1. spring-session-data-redis相关

- 添加后,在项目引用工具类后,将会强制要求配置redis

```xml
<!--redis-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis-reactive</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.session</groupId>
	<artifactId>spring-session-data-redis</artifactId>
</dependency>
```

## 三 结构

### 1. deal直接调用工具类

- 使用 DealDate.功能(参数) 即可使用的工具类
- 工具都是static修饰的静态方法

### 2. found NEW实例化工具类

- 需要先NEW 新的实例对象
- 案例 FounSqlite

### 3. general普通常量类型工具

- 保存常量类
- 保存异常类

### 4. found 创建实现工具类

- 用于创建一系列操作的工具类
- 已经实现
    - FoundSqlite 全自研开发专注sqlite数据库的对象关系映射框架 对标hibernate

## 四 代码质量规则

- 因为本工具可能被其它项目使用,按照最严格创建使用
- 严格符合sonar扫描
- 严格符合阿里规约扫描
- 测试案例全覆盖

## 工具使用

在maven中选择install打包,工具类reedtools将会自动编译到maven仓库repository\top\ireed\reedtools\1.0.0目录下

pom中maven引用方式

```xml
<dependency>
    <groupId>top.ireed</groupId>
    <artifactId>reedtools</artifactId>
    <version>1.0.0</version>
</dependency>
```