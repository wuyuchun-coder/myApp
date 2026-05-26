package com.myapp.domain.user.model;

import com.myapp.domain.shared.Identifier;

/**
 * 用户聚合标识（示例限界上下文）。
 */
public class UserId extends Identifier<String> {

    public UserId(String value) {
        super(value);
    }

    public static UserId of(String value) {
        return new UserId(value);
    }
}
