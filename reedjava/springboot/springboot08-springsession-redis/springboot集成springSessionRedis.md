# springboot集成springSessionRedis

## 一 springboot集成Redis

```xml
<!--redis-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

```java
//注入
@Autowired
private StringRedisTemplate stringRedisTemplate;

//赋值
stringRedisTemplate.opsForValue().set(key, value);
//取值
stringRedisTemplate.opsForValue().get(key)
```

## 二 springboot集成springSession

springSession实质就是把session存放到第三方容器中,一般使用redis

在redis的基础上,添加session依赖

```xml
<!--session-->
<dependency>
   <groupId>org.springframework.session</groupId>
   <artifactId>spring-session-data-redis</artifactId>
</dependency>
```

```java
@RestController
public class SessionController {
   @GetMapping("/boot/session/{key}/{value}")
   public String test(@PathVariable String key, @PathVariable String value ,HttpSession session) {
      session.setAttribute(key,value);
      return "设置session数据成功  key=" + key + "  value= " + session.getAttribute(key);
   }
}
```