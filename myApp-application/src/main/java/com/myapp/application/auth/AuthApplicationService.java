package com.myapp.application.auth;

import com.myapp.application.auth.command.LoginCommand;
import com.myapp.application.auth.exception.AuthenticationException;
import com.myapp.application.dto.LoginResultDTO;
import com.myapp.application.user.assembler.UserAppMapper;
import com.myapp.domain.user.model.User;
import com.myapp.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 认证应用服务。
 */
@Service
public class AuthApplicationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserAppMapper userAppMapper;

    public AuthApplicationService(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder,
                                  TokenProvider tokenProvider,
                                  UserAppMapper userAppMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userAppMapper = userAppMapper;
    }

    @Transactional
    public LoginResultDTO login(LoginCommand command) {
        User user = userRepository.findByUsername(command.getUsername())
                .orElseThrow(() -> new AuthenticationException("用户名或密码错误"));

        if (!user.canLogin()) {
            throw new AuthenticationException("账号已禁用或锁定");
        }

        if (!passwordEncoder.matches(command.getPassword(), user.getPasswordHash())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        user.recordLogin();
        userRepository.updateLastLoginTime(user.getId(), user.getLastLoginTime());

        String token = tokenProvider.generateToken(user.getId().getValue(), user.getUsername());
        return new LoginResultDTO(token, userAppMapper.toDTO(user));
    }
}
