package com.myapp.infrastructure.persistence.user;

import com.myapp.domain.user.model.User;
import com.myapp.domain.user.model.UserId;
import com.myapp.domain.user.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户仓储内存实现（示例，可替换为 JPA/MyBatis 等）。
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> store = new ConcurrentHashMap<>();

    @Override
    public void save(User aggregate) {
        store.put(aggregate.getId().getValue(), aggregate);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.ofNullable(store.get(id.getValue()));
    }

    @Override
    public void remove(User aggregate) {
        store.remove(aggregate.getId().getValue());
    }
}
