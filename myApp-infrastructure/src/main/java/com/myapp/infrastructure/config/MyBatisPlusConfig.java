package com.myapp.infrastructure.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 配置。
 */
@Configuration
@MapperScan(value = "com.myapp.infrastructure.persistence", annotationClass = Mapper.class)
public class MyBatisPlusConfig {
}
