# springboot02-configmvc集成mvc

## 一 自定义配置属性值注解读取

### 1.@Value

用于逐个读取自定义的配置，比如：

```java
@Value("${user.name}")
private String name;
```

### 2.@ConfigurationProperties

用于将整个配置文件组映射成一个对象，比如将user开头中,在本类中包含的属性封装到实体类中：

```java

@Component
@ConfigurationProperties(prefix = "user")
public class MyConfig {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}    
```

## 二 Spring boot核心配置文件

### 1. .properties文件

键值对的properties属性文件配置方式, 常用配置格式

```properties
#配置服务器端口
server.port=9800
#配置应用访问路径
server.context-path=/file
server:
port:9091
context-path:/file
```

### 2. .yml文件

yml 是一种 yaml 格式的配置文件，主要采用一定的空格、换行等格式排版进行配置； yaml 是一种直观的能够被计算机识别的的数据序列化格式，容易被人类阅读，yaml 类似于 xml，但是语法比 xml 简洁很多；
值与前面的冒号配置项必须要有一个空格； yml 后缀也可以使用 yaml 后缀；

## 三 Spring boot mvc 常用注解

### 1.@Controller

​ 即为Spring mvc的注解，处理http请求；

### 2.@RestController

​ Spring4后新增注解； ​ 是@Controller与@ResponseBody的组合注解； ​ 用于返回字符串或json数据；

​ 如果使用本注解,整个Controller返回的值都是字符串

### 3.@GetMapping

​ RequestMapping 和 Get请求方法的组合；

### 4.@PostMapping

​ RequestMapping 和 Post请求方法的组合；

### 5.@PutMapping

​ RequestMapping 和 Put请求方法的组合；

### 6.@DeleteMapping

​ RequestMapping 和 Delete请求方法的组合；