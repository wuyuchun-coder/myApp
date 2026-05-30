package com.myapp.application.user.command;

import lombok.Data;
import lombok.Value;

/**
 * 创建用户命令对象。
 */
@Value
public class CreateUserCommand {

    String username;
    String email;
    String password;
    String nickname;
}
