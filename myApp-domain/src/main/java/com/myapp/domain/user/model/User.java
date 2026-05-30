package com.myapp.domain.user.model;

import com.myapp.domain.shared.AggregateRoot;
import com.myapp.domain.shared.DomainException;
import com.myapp.domain.user.event.UserCreatedEvent;

import java.time.LocalDateTime;

/**
 * 用户聚合根。
 */
public class User extends AggregateRoot<UserId> {

    private String username;
    private String email;
    private String passwordHash;
    private String nickname;
    private UserStatus status;
    private LocalDateTime lastLoginTime;

    private User() {
    }

    private User(UserId id, String username, String email, String passwordHash,
                 String nickname, UserStatus status, LocalDateTime lastLoginTime) {
        super(id);
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.nickname = nickname;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
    }

    public static User register(String username, String email, String passwordHash, String nickname) {
        if (username == null || username.isBlank()) {
            throw new DomainException("用户名不能为空");
        }
        if (email == null || email.isBlank()) {
            throw new DomainException("邮箱不能为空");
        }
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new DomainException("密码不能为空");
        }
        UserId id = UserId.of(java.util.UUID.randomUUID().toString());
        User user = new User(id, username.trim(), email.trim(), passwordHash,
                nickname != null ? nickname.trim() : null, UserStatus.NORMAL, null);
        user.registerEvent(new UserCreatedEvent(id.getValue(), username, email));
        return user;
    }

    public static User reconstitute(UserId id, String username, String email, String passwordHash,
                                    String nickname, UserStatus status, LocalDateTime lastLoginTime) {
        return new User(id, username, email, passwordHash, nickname, status, lastLoginTime);
    }

    public boolean canLogin() {
        return status == UserStatus.NORMAL;
    }

    public void recordLogin() {
        this.lastLoginTime = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getNickname() {
        return nickname;
    }

    public UserStatus getStatus() {
        return status;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }
}
