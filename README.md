# myApp

基于 DDD 的 Java 多模块 Maven 项目（Spring Boot 3.3 + Java 21）。

## 模块结构

| 模块 | 职责 |
|------|------|
| `myApp-domain` | 聚合根、实体、值对象、领域事件、仓储接口 |
| `myApp-application` | 应用服务、Command、DTO、Assembler |
| `myApp-infrastructure` | 仓储实现、持久化、外部系统适配 |
| `myApp-interfaces` | REST API、请求校验、全局异常 |
| `myApp-bootstrap` | 启动入口、配置组装 |

## 依赖方向

```
interfaces → application → domain
infrastructure → domain
bootstrap → interfaces + infrastructure + application
```

## 快速开始

**环境要求：JDK 21**

### 1. 建立 SSH 隧道（连接远程 MySQL）

在项目根目录打开 PowerShell，执行（会提示输入 root 密码，**保持窗口不要关闭**）：

```powershell
.\scripts\start-ssh-tunnel.ps1
```

或手动执行：

```powershell
ssh -N -L 3307:127.0.0.1:3306 root@111.228.11.168
```

应用与测试均通过本机 `127.0.0.1:3307` 访问远程数据库（与 DBeaver SSH 隧道方式一致）。

### 2. 启动应用

```powershell
# Windows PowerShell 示例（按本机 JDK 路径调整）
$env:JAVA_HOME="D:\JAVA\jdk-21.0.3"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
$env:MYSQL_PASSWORD="your_password"

cd D:\IdeaProject\myApp
mvn clean package
mvn -pl myApp-bootstrap spring-boot:run
```

注册用户：

```bash
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d "{\"username\":\"alice\",\"email\":\"alice@example.com\",\"password\":\"123456\"}"
```

用户登录：

```bash
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"username\":\"alice\",\"password\":\"123456\"}"
```

## 集成测试（经 SSH 隧道连 MySQL）

登录集成测试位于 `myApp-bootstrap/src/test/java`，不使用 Mock，会真实读写 `sys_user` 表。每个用例结束后事务自动回滚，不会污染数据。

**前置条件：** 已建立 SSH 隧道（见上文），且已设置 `MYSQL_PASSWORD`。

```powershell
# 终端 1：SSH 隧道
ssh -L 3307:127.0.0.1:3306 root@111.228.11.168

# 终端 2：运行测试
$env:JAVA_HOME="D:\JAVA\jdk-21.0.3"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
$env:MYSQL_PASSWORD="your_password"

cd D:\IdeaProject\myApp
mvn test -pl myApp-bootstrap -am
```

IntelliJ 运行测试时，请在 Run Configuration 中添加环境变量 `MYSQL_PASSWORD`。

## 新增限界上下文

在 `myApp-domain` 下按 `user` 包的模式新增：`model` / `event` / `repository`，再在 application、infrastructure、interfaces 各层对应扩展。
