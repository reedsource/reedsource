# springboot集成过滤器filter侦听器listener

## 一 过滤器filter

### 1. 注解方式添加

直接在Filter上添加注解

```java
@WebFilter(urlPatterns = "/boot2/*")
```

### 2.config配置类添加

```java
@Configuration
public class MyConfig{
   @Bean
   public FilterRegistrationBean filterRegistrationBean() {
      FilterRegistrationBean frb = new FilterRegistrationBean(new MyFilter());
      //可以设置多个
      frb.addUrlPatterns("/boot/*");
      return frb;
   }
}
```

## 二 侦听器listener

### 1.方法1手动配置

监听器：listener是servlet规范中定义的一种特殊类。用于监听servletContext、HttpSession和servletRequest等域对象的创建和销毁事件。监听域对象的属性发生修改的事件。

用于在事件发生前、发生后做一些必要的处理。其主要可用于以下方面：

1、统计在线人数和在线用户

2、系统启动时加载初始化信息

3、统计网站访问量

4、记录用户访问路径

使用Springboot提供了`RegistrationBean`的子类ServletListenerRegistrationBean 用于注册Filter，完成过滤器的设置首先我们创建一个MyHttpSessionListener类

　　

```java
package com.example.spingbootdemo1.listener;
 
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
public class MyHttpSessionListener implements HttpSessionListener {
    private static int onlineNum = 0;
 
    public void sessionCreated(HttpSessionEvent se) {
        onlineNum++;
    }
 
    public void sessionDestroyed(HttpSessionEvent se) {
        onlineNum--;
    }
}
```

然后我们创建一个控制器UserController

```java
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

public class UserController {
    @GetMapping("/onlineCount")
    public String onlineCount(){
        return"网站当前在线人数为："+ MyHttpSessionListener.onlineNum;
    }

    @GetMapping("/index")
    public String index(HttpSession httpSession){
        //在访问首页的时候触发session的操作
        httpSession.setAttribute("username","xiadewang");return"首页";
    }
}
```

上面我们已经把监听器和控制层的工作完成了，但是我们知道springboot里面的监听器实际上底下就是spring和springmvc相关的东西，再下面就是servlet，在以前的老项目中我们需要在web.xml中多监听器做配置。而在springboot中我们没有了web.xml等相关xml配置文件，取而代之的是使用@Configuration注解在java代码中实现相关配置。于是我们可以创建一个配置类，代码如下：

```java
@Configuration
public class MyMvcConfig {
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb=new ServletListenerRegistrationBean();
        srb.setListener(new MyHttpSessionListener());
        return srb;
    }
}
```

这里我们使用@Configuration注解和@Bean注解实现了一个监听器的注册绑定配置。@Configuration其实就是告诉spring，spring容器要怎么配置（怎么去注册bean，怎么去处理bean之间的关系（装配）=>
@Configuration相当于<beans></beans>便签，作用为配置spring容器(应用上下文)
。那么久很好理解了，@Bean的意思就是，我要获取这个bean的时候，你spring要按照这种方式去帮我获取到这个bean。@Bean注解的方法会实例化、配置并初始化一个新的对象，这个对象会由spring IoC 容器管理。

### 方法2: 使用@WebListener注解完成过滤器的设置

在MyHttpSessionListener上面加上@WebListener注解,

```java
package com.example.spingbootdemo1.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    public static int onlineNum = 0;

    public void sessionCreated(HttpSessionEvent se) {
        onlineNum++;
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        onlineNum--;
    }
}
```

然后在启动类上加@ServletComponentScan注解提供支持扫描webListener

```java
/***
 * 使用@ServletComponentScan配合@WebListener后，务必要删除之前创建的MyMvcConfig这个类。实现过滤器配置二者选其一即可。
 * springboot默认会检索启动类所在包和子包下的所有spring容器相关的注解（比如@Controller、@Component等），但是像@WebFilter和@WebListener之类的不会
 */
@ServletComponentScan
@SpringBootApplication
public class Spingbootdemo1Application {
 public static void main(String[] args) {
        SpringApplication.run(Spingbootdemo1Application.class, args);
    }
}
```