package com.myapp.infrastructure.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JWT 配置属性。
 */
@Data
@ConfigurationProperties(prefix = "myapp.jwt")
public class JwtProperties {

    private String secret = "myApp-dev-secret-key-change-in-production-min-32chars";
    private long expirationMs = 86400000L;
}
