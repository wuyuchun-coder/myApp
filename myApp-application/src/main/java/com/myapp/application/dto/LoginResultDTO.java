package com.myapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录结果 DTO。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultDTO {

    private String token;
    private UserDTO user;
}
