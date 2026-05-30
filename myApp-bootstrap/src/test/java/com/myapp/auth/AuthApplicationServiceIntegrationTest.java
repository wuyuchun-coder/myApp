package com.myapp.auth;

import com.myapp.application.auth.AuthApplicationService;
import com.myapp.application.auth.command.LoginCommand;
import com.myapp.application.dto.LoginResultDTO;
import com.myapp.application.user.UserApplicationService;
import com.myapp.application.user.command.CreateUserCommand;
import com.myapp.domain.user.repository.UserRepository;
import com.myapp.infrastructure.persistence.user.mapper.SysUserMapper;
import com.myapp.infrastructure.persistence.user.po.SysUserPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * 登录集成测试，经 SSH 隧道（127.0.0.1:3307）连接远程 MySQL。
 *
 * <p>运行前先执行：{@code ssh -L 3307:127.0.0.1:3306 root@111.228.11.168}</p>
 * <p>并设置环境变量 {@code MYSQL_PASSWORD}。</p>
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AuthApplicationServiceIntegrationTest {

    private static final String PASSWORD = "123456";

    @Autowired
    private AuthApplicationService authApplicationService;

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Commit
    void login_shouldReturnTokenAndUser_whenCredentialsAreValid() {
        String username = uniqueUsername("login_ok");
        userApplicationService.createUser(new CreateUserCommand(
                username, username + "@example.com", PASSWORD, "测试用户"
        ));

        LoginResultDTO result = authApplicationService.login(new LoginCommand(username, PASSWORD));

        assertThat(result.getToken()).isNotBlank();
        assertThat(result.getToken().split("\\.")).hasSize(3);
        assertThat(result.getUser().getUsername()).isEqualTo(username);
        assertThat(result.getUser().getEmail()).isEqualTo(username + "@example.com");
        assertThat(result.getUser().getNickname()).isEqualTo("测试用户");

        assertThat(userRepository.findByUsername(username))
                .isPresent()
                .get()
                .satisfies(user -> assertThat(user.getLastLoginTime()).isNotNull());
    }

    private void insertUser(String username, int status) {
        SysUserPO po = new SysUserPO();
        po.setId(UUID.randomUUID().toString());
        po.setUsername(username);
        po.setEmail(username + "@example.com");
        po.setPasswordHash(passwordEncoder.encode(PASSWORD));
        po.setStatus(status);
        sysUserMapper.insert(po);
    }

    private static String uniqueUsername(String prefix) {
        return "itest_" + prefix + "_" + UUID.randomUUID().toString().substring(0, 8);
    }
}
