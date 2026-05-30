package com.myapp.application.user.assembler;

import com.myapp.application.dto.UserDTO;
import com.myapp.domain.user.model.User;
import com.myapp.domain.user.model.UserId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 用户领域对象与 DTO 转换器。
 */
@Mapper(componentModel = "spring")
public interface UserAppMapper {

    @Mapping(target = "id", source = "id.value")
    UserDTO toDTO(User user);

    default String map(UserId id) {
        return id == null ? null : id.getValue();
    }
}
