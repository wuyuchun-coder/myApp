package com.myapp.domain.user.repository;

import com.myapp.domain.shared.Repository;
import com.myapp.domain.user.model.User;
import com.myapp.domain.user.model.UserId;

/**
 * 用户仓储接口（定义在领域层，由 infrastructure 实现）。
 */
public interface UserRepository extends Repository<User, UserId> {
}
