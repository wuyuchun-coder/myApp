package com.myapp.domain.user.event;

import com.myapp.domain.shared.DomainEvent;

/**
 * 用户已创建领域事件（示例）。
 */
public class UserCreatedEvent extends DomainEvent {

    private final String userId;
    private final String username;
    private final String email;

    public UserCreatedEvent(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
