package com.myapp.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 基础设施层 Spring 配置。
 */
@Configuration
@ComponentScan(basePackages = "com.myapp.infrastructure")
public class InfrastructureConfig {
}
