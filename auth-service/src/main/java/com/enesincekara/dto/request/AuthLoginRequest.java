package com.enesincekara.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
