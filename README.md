# myApp

基于 DDD 的 Java 多模块 Maven 项目（Spring Boot 3.3 + Java 17）。

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

```bash
cd D:\IdeaProject\myApp
mvn clean package
mvn -pl myApp-bootstrap spring-boot:run
```

创建用户示例：

```bash
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d "{\"username\":\"alice\",\"email\":\"alice@example.com\"}"
```

## 新增限界上下文

在 `myApp-domain` 下按 `user` 包的模式新增：`model` / `event` / `repository`，再在 application、infrastructure、interfaces 各层对应扩展。
