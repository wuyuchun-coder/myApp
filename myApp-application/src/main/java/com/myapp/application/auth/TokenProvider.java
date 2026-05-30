package com.myapp.application.auth;

/**
 * 令牌生成端口，由 infrastructure 层实现。
 */
public interface TokenProvider {

    String generateToken(String userId, String username);
}
