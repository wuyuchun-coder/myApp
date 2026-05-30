package com.myapp.infrastructure.persistence.user.mapper;

import com.myapp.domain.user.model.User;
import com.myapp.domain.user.model.UserId;
import com.myapp.domain.user.model.UserStatus;
import com.myapp.infrastructure.persistence.user.po.SysUserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 用户领域对象与持久化对象转换器。
 */
@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    default User toDomain(SysUserPO po) {
        if (po == null) {
            return null;
        }
        return User.reconstitute(
                UserId.of(po.getId()),
                po.getUsername(),
                po.getEmail(),
                po.getPasswordHash(),
                po.getNickname(),
                UserStatus.fromCode(po.getStatus()),
                po.getLastLoginTime()
        );
    }

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "status", source = "status.code")
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    SysUserPO toPO(User user);

    default String map(UserId id) {
        return id == null ? null : id.getValue();
    }

    default Integer map(UserStatus status) {
        return status == null ? null : status.getCode();
    }
}
