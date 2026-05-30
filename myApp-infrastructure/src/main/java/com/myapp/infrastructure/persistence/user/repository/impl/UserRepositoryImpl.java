package com.myapp.infrastructure.persistence.user.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myapp.domain.user.model.User;
import com.myapp.domain.user.model.UserId;
import com.myapp.domain.user.repository.UserRepository;
import com.myapp.infrastructure.persistence.user.mapper.SysUserMapper;
import com.myapp.infrastructure.persistence.user.mapper.UserPersistenceMapper;
import com.myapp.infrastructure.persistence.user.po.SysUserPO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 用户仓储 MyBatis-Plus 实现。
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SysUserMapper sysUserMapper;
    private final UserPersistenceMapper userPersistenceMapper;

    public UserRepositoryImpl(SysUserMapper sysUserMapper, UserPersistenceMapper userPersistenceMapper) {
        this.sysUserMapper = sysUserMapper;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    @Override
    public void save(User aggregate) {
        SysUserPO po = userPersistenceMapper.toPO(aggregate);
        if (sysUserMapper.selectById(po.getId()) == null) {
            sysUserMapper.insert(po);
        } else {
            sysUserMapper.updateById(po);
        }
    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.ofNullable(sysUserMapper.selectById(id.getValue()))
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        SysUserPO po = sysUserMapper.selectOne(
                Wrappers.<SysUserPO>lambdaQuery()
                        .eq(SysUserPO::getUsername, username)
        );
        return Optional.ofNullable(po).map(userPersistenceMapper::toDomain);
    }

    @Override
    public void updateLastLoginTime(UserId id, LocalDateTime lastLoginTime) {
        sysUserMapper.update(null,
                Wrappers.<SysUserPO>lambdaUpdate()
                        .set(SysUserPO::getLastLoginTime, lastLoginTime)
                        .eq(SysUserPO::getId, id.getValue())
        );
    }

    @Override
    public void remove(User aggregate) {
        sysUserMapper.deleteById(aggregate.getId().getValue());
    }
}
