package com.myapp.application.user;

import com.myapp.application.user.assembler.UserAppMapper;
import com.myapp.application.user.command.CreateUserCommand;
import com.myapp.application.dto.UserDTO;
import com.myapp.domain.shared.DomainException;
import com.myapp.domain.user.model.User;
import com.myapp.domain.user.model.UserId;
import com.myapp.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * 用户应用服务
 */
@Service
public class UserApplicationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAppMapper userAppMapper;

    public UserApplicationService(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder,
                                  UserAppMapper userAppMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAppMapper = userAppMapper;
    }

    @Transactional
    public UserDTO createUser(CreateUserCommand command) {
        userRepository.findByUsername(command.getUsername())
                .ifPresent(user -> {
                    throw new DomainException("用户名已存在");
                });

        String passwordHash = passwordEncoder.encode(command.getPassword());
        User user = User.register(command.getUsername(), command.getEmail(),
                passwordHash, command.getNickname());
        userRepository.save(user);
        return userAppMapper.toDTO(user);
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getUser(String userId) {
        return userRepository.findById(UserId.of(userId))
                .map(userAppMapper::toDTO);
    }
}
