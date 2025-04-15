package com.enesincekara.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank(message = "Email must not be blank")
        @Email(message = "Email should be valid")
        String email,
        @NotBlank(message = "Password must not be blank")
        String password
) {
}
