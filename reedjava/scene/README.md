# scene 场景模块

## 1. 模块定位

`scene` 与 `springboot` 同级,用于承载**业务场景工程**。

| 模块 | 定位 | 产物 |
|---|---|---|
| `reedjava/springboot` | SpringBoot 技术学习与功能验证,按技术点切分 | 每个子模块是一个可运行 demo |
| `reedjava/scene` | 真实业务场景工程,按场景切分 | 每个场景是一个**独立可启动、可打包、可单独部署**的 SpringBoot 项目 |

场景之间不共享代码,只共享父 pom 中的统一依赖版本(由 `reedjava/pom.xml` 的 `reedtools`、`hutool`、`jackson`、`lombok` 等公共依赖继承)。需要跨场景复用的能力,应下沉到 `reedjava/frame` 或 `reedtools`,而不是让场景之间互相依赖。

## 2. 目录结构

```
reedjava/scene/
├── pom.xml                     场景聚合父 pom,登记场景清单
├── README.md                   本文件
├── scene01-template/           场景模板(新增场景的复制源)
│   ├── pom.xml
│   ├── 场景01-基础框架说明.md
│   └── src/
│       ├── main/
│       │   ├── java/top/ireed/
│       │   │   ├── Scene01TemplateApplication.java   启动类
│       │   │   ├── common/SceneResult.java            统一返回结构
│       │   │   ├── controller/SceneHelloController.java 示例控制器
│       │   │   └── exception/SceneExceptionHandler.java 全局异常处理
│       │   └── resources/application.properties      场景配置
│       └── test/java/top/ireed/scene01template/       上下文加载测试
└── scene02-xxx/                (待新增)
```

每个场景与 `springboot` 子模块保持一致的 Maven 布局:`src/main/java`、`src/main/resources`、`src/test/java`。

## 3. 命名规范

| 项 | 规则 | 示例 |
|---|---|---|
| 目录名 | `sceneNN-英文短横线名称`,NN 两位顺延 | `scene01-template`、`scene02-gateway-cert` |
| artifactId / jar 名 | 与目录名一致 | `scene01-template` |
| 启动类 | `SceneNNXxxApplication`,驼峰,放在 `top.ireed` 根包 | `Scene02GatewayCertApplication` |
| 包路径 | `top.ireed.sceneNNxxx`(全小写) | `top.ireed.scene02gatewaycert` |
| `spring.application.name` | 与目录名一致 | `scene02-gateway-cert` |
| 端口 | 从 8081 起顺延,**场景之间不可重复** | 8081、8082、8083… |

命名以**场景要解决的事情**为准,不以技术实现命名。

## 4. 新增一个场景

1. 复制 `scene01-template` 目录为 `sceneNN-xxx`。
2. 修改 `pom.xml` 的 `artifactId`、`name`、`description`。
3. 修改启动类名与文件路径,修改包路径下所有 `package` 与 `import`。
4. 修改 `application.properties` 的 `server.port` 与 `spring.application.name`。
5. 在 `scene/pom.xml` 的 `<modules>` 中登记新场景。
6. 在 `reedjava/pom.xml` 的 `<modules>` 中确认 `scene` 已登记(只需登记一次)。
7. 清理模板遗留的示例类(示例控制器按需保留为探针接口)。
8. 构建与启动验证。

## 5. 构建与启动

```powershell
# 构建整个场景模块(跳过测试)
mvn -f reedjava/scene/pom.xml clean package -DskipTests

# 只构建某个场景
mvn -f reedjava/scene/scene01-template/pom.xml clean package

# 启动
java -jar reedjava/scene/scene01-template/target/scene01-template-1.0.0.jar

# 或在场景目录下
mvn spring-boot:run
```

启动后访问探针接口确认骨架可用:

```
http://localhost:8081/scene/ping
```

## 6. 公共依赖说明

场景模块的依赖版本统一由父 pom 管理:

- `reedjava/pom.xml` —— 所有 `reedjava` 子模块共用:`reedtools`、`hutool`、`jackson`、`guava`、`lombok`、`junit`。
- `scene/pom.xml` —— 场景模块共用:`spring-boot-starter-web`、`spring-boot-starter-test`、`spring-boot-configuration-processor`,并统一 `spring-boot-maven-plugin` 的 `repackage`(保证每个场景产出独立可执行 jar)。

> 场景模块使用 `spring-boot-starter-web`(标准内嵌 Tomcat 栈),与 `springboot` 学习模块使用的 `spring-boot-starter-web-services`(额外含 CXF / JAX-WS SOAP 栈)不同:业务场景以 HTTP/JSON 为主,不引入 SOAP 依赖。
