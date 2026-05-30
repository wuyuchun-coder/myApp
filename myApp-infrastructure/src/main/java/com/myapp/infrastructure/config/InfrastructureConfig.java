package com.myapp.infrastructure.config;

import com.myapp.infrastructure.auth.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 基础设施层 Spring 配置。
 */
@Configuration
@ComponentScan(basePackages = "com.myapp.infrastructure")
@EnableConfigurationProperties(JwtProperties.class)
public class InfrastructureConfig {
}
