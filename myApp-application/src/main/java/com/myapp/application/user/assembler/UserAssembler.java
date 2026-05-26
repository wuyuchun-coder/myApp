package com.myapp.application.user.assembler;

import com.myapp.application.dto.UserDTO;
import com.myapp.domain.user.model.User;

/**
 * 领域对象与 DTO 转换器。
 */
public final class UserAssembler {

    private UserAssembler() {
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId().getValue(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
