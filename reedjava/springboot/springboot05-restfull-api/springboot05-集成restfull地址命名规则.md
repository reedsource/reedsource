# springboot05-集成restfull地址命名规则.md

## 一 什么是RESTFull

RESTFull 一种互联网软件架构设计的风格，但它并不是标准， 它只是提出了一组客户端和服务器交互时的架构理念和设计原则，基于这种理念和原则设计的接口可以更简洁，更有层次；

任何的技术都可以实现这种理念；

REST这个词，是Roy Thomas Fielding在他2000年的博士论文中提出的；

如果一个架构符合REST原则，就称它为RESTFull架构；

比如我们要访问一个http接口：http://localhost:80080/api/topOrder?id=1000&status=1

采用RESTFull风格则http地址为：http://localhost:80080/api/topOrder/1000/1

## 二 RESTFull 注解

### 1.@PathVariable

​ 获取url中的数据； ​ 该注解是实现RESTFull最主要的一个注解；

```
	@GetMapping("/boot/test/{id}/{name}")
	public String test(@PathVariable Long id, @PathVariable String name) {
		return "id=" + id + "  name= " + name;
	}
```

### 2.增加 post方法

​ PostMapping； ​ 接收和处理Post方式的请求；

```
@PostMapping("user/{id}/{name}/{age}")
```

### 3.删除 delete方法

​ DeleteMapping； ​ 接收delete方式的请求，可以使用GetMapping代替；

```
@DeleteMapping("user/{id}")
```

### 4.修改 put方法

​ PutMapping； ​ 接收put方式的请求，可以用PostMapping代替；

```
@PutMapping("user/{id}/{name}/{age}")
```

### 5.查询 get方法

​ GetMapping； ​ 接收get方式的请求；

```
@GetMapping("user/{id}/{name}/{age}")
```

