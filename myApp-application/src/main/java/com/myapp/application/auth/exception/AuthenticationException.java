package com.myapp.application.auth.exception;

/**
 * 认证失败异常。
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}
