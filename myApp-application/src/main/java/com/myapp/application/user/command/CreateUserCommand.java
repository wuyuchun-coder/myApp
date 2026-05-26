package com.myapp.application.user.command;

/**
 * 创建用户命令对象。
 */
public class CreateUserCommand {

    private final String username;
    private final String email;

    public CreateUserCommand(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
