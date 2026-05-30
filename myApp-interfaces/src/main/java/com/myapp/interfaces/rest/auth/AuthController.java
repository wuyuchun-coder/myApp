package com.myapp.interfaces.rest.auth;

import com.myapp.application.auth.AuthApplicationService;
import com.myapp.application.auth.command.LoginCommand;
import com.myapp.application.dto.LoginResultDTO;
import com.myapp.interfaces.rest.auth.request.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证 REST 接口。
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthApplicationService authApplicationService;

    public AuthController(AuthApplicationService authApplicationService) {
        this.authApplicationService = authApplicationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResultDTO> login(@Valid @RequestBody LoginRequest request) {
        LoginResultDTO result = authApplicationService.login(
                new LoginCommand(request.getUsername(), request.getPassword())
        );
        return ResponseEntity.ok(result);
    }
}
