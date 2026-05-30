package com.myapp.application.auth.command;

import lombok.Value;

/**
 * 用户登录命令。
 */
@Value
public class LoginCommand {

    String username;
    String password;
}
