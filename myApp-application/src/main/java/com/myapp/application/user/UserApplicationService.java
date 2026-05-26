package com.myapp.application.user;

import com.myapp.application.dto.UserDTO;
import com.myapp.application.user.assembler.UserAssembler;
import com.myapp.application.user.command.CreateUserCommand;
import com.myapp.domain.user.model.User;
import com.myapp.domain.user.model.UserId;
import com.myapp.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 用户应用服务（编排领域对象，不含业务规则）。
 */
@Service
public class UserApplicationService {

    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO createUser(CreateUserCommand command) {
        User user = User.create(command.getUsername(), command.getEmail());
        userRepository.save(user);
        return UserAssembler.toDTO(user);
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getUser(String userId) {
        return userRepository.findById(UserId.of(userId))
                .map(UserAssembler::toDTO);
    }
}
