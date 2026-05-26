package com.myapp.domain.user.model;

import com.myapp.domain.shared.AggregateRoot;
import com.myapp.domain.shared.DomainException;
import com.myapp.domain.user.event.UserCreatedEvent;

/**
 * 用户聚合根（示例）。
 */
public class User extends AggregateRoot<UserId> {

    private String username;
    private String email;

    private User() {
    }

    private User(UserId id, String username, String email) {
        super(id);
        this.username = username;
        this.email = email;
    }

    public static User create(String username, String email) {
        if (username == null || username.isBlank()) {
            throw new DomainException("用户名不能为空");
        }
        if (email == null || email.isBlank()) {
            throw new DomainException("邮箱不能为空");
        }
        UserId id = UserId.of(java.util.UUID.randomUUID().toString());
        User user = new User(id, username.trim(), email.trim());
        user.registerEvent(new UserCreatedEvent(id.getValue(), username, email));
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
