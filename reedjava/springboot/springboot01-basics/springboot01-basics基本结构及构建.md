# springboot01-basics基本结构及构建

## 一 springboot基础构建依赖

### 1.SpringBoot项目的默认依赖

1. Spring Boot 的父级依赖spring-boot-starter-parent配置之后，当前的项目就是Spring Boot项目；

2. spring-boot-starter-parent是一个特殊的starter依赖，它用来提供相关的Maven默认依赖，使用它之后，常用的jar包依赖可以省去version配置；

3. Spring Boot提供了哪些默认jar包的依赖，可查看该父级依赖的pom文件；

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.0.RELEASE</version>
    <relativePath/>
</parent>
```

4. 如果不想使用某个默认的依赖版本，可以通过pom.xml文件的属性配置覆盖各个依赖项，比如覆盖Spring版本：

```xml
<properties>
    <spring.version>5.0.0.RELEASE</spring.version>
</properties>
```

5.springboot默认pom文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!--maven构建路径-->
	<groupId>com.example</groupId>
	<!--项目工程名-->
	<artifactId>demo</artifactId>
	<!--版本号-->
	<version>0.0.1-SNAPSHOT</version>
	<!--模块名称-->
	<name>demo</name>
	<!--模块描述-->
	<description>Demo project for Spring Boot</description>

	<!--版本内容集-->
	<properties>
		<java.version>17</java.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<spring-boot.version>2.3.0.RELEASE</spring-boot.version>
	</properties>

	<!--依赖-->
	<dependencies>
		<!--springboot web-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web-services</artifactId>
		</dependency>

		<!--springboot 测试-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>

	<!--Spring Boot 的父级依赖-->
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-dependencies</artifactId>
				<version>${spring-boot.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<!--Spring Boot 编译依赖-->
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
					<encoding>UTF-8</encoding>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
```

### 2.SpringBoot项目基本文件注解结构

1. @SpringBootApplication 注解是Spring Boot项目的核心注解，主要作用是开启Spring自动配置；
2. main方法是一个标准的Java程序的main方法，主要作用是作为项目启动运行的入口；
3. @Controller 及 @ResponseBody 依然是我们之前的Spring mvc，因为Spring boot的里面依然是使用我们的Spring mvc + Spring + MyBatis 等框架；

