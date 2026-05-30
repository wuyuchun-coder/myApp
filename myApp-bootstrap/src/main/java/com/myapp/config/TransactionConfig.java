package com.myapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 事务配置（使用 Spring Boot 自动配置的 DataSourceTransactionManager）。
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {
}
