package com.myapp.domain.user.model;

/**
 * 用户账号状态。
 */
public enum UserStatus {

    DISABLED(0),
    NORMAL(1),
    LOCKED(2);

    private final int code;

    UserStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserStatus fromCode(int code) {
        for (UserStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知用户状态: " + code);
    }
}
